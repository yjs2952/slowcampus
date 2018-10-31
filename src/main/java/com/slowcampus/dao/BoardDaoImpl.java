package com.slowcampus.dao;

import com.slowcampus.dto.Board;
import lombok.extern.java.Log;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yjs
 * @since 2018.10.29
 */
@Repository
@Log
public class BoardDaoImpl implements BoardDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;

    BoardDaoImpl(DataSource dataSource) {
        log.info("BoardDao()");
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board_content");
                //.usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Board> getList(int category) {
        String sql = "SELECT id, title, read_count, nickname, category, root_board_id, parent_board_id, depth, depth_order, ip_addr, regdate, moddate " +
                     "FROM board " +
                     "WHERE category = :category "+
                     "ORDER BY root_board_id, depth, depth_order";

        // TODO: 2018-10-29 (yjs) :  추후 페이징 처리 해야됨 (start, limit)
        try {
            RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
            Map<String, Integer> map = new HashMap<>();
            map.put("category", category);

            return jdbc.query(sql, map, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Board getBoard(Long id) {
        String sql = "SELECT a.id, a.read_count, a.title, a.nickname, a.category, a.root_board_id, a.parent_board_id, a.ip_addr, b.board_content as content, a.regdate, a.moddate "+
                     "FROM board a "+
                     "INNER JOIN board_content b "+
                     "WHERE a.id = b.board_id "+
                     "AND a.id = :id";

        try {
            RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
            Map<String, ?> map = Collections.singletonMap("id", id);
            return jdbc.queryForObject(sql, map, rowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long writeBoard(Board board) {
        String sql = "insert into board (title, read_count, nickname, category, root_board_id, parent_board_id, user_id, depth, depth_order, ip_addr, regdate)" +
                     " values (:title, :readCount, :nickname, :category, (select last_insert_id() + 1), :parentBoardId, :userId, :depth, :depthOrder, :ipAddr, now())";

        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int writeBoardContent(Board board) {
        Map<String, Object> map = new HashMap<>();
        map.put("board_id", board.getId());
        map.put("board_content", board.getContent());
        return insertAction.execute(map);
    }

    @Override
    public int modifyBoard(Board board) {
        String sql = "UPDATE board " +
                     "SET   title = :title, " +
                     "      ip_Addr = :ipAddr, " +
                     "      moddate = now() " +
                     "WHERE id = :id";

        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        return jdbc.update(sql, params);
    }

    public int modifyBoardContent(Board board) {
        String sql = "UPDATE board_content SET board_content = :content " +
                     "WHERE board_id = :id";

        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        return jdbc.update(sql, params);
    }

    @Override
    public int deleteBoard(Long id) {
        String sql = "UPDATE board SET is_deleted = 1 WHERE id = :id";
        Map<String, ?> map = Collections.singletonMap("id", id);
        return jdbc.update(sql, map);
    }
}
