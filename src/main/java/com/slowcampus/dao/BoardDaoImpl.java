package com.slowcampus.dao;

import com.slowcampus.dto.Board;
import com.slowcampus.dto.Pagination;
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
import java.sql.PreparedStatement;
import java.util.*;

/**
 * @author yjs
 * @since 2018.10.29
 */
@Repository
@Log
public class BoardDaoImpl implements BoardDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertBoardAction;
    private SimpleJdbcInsert insertBoardContentAction;

    BoardDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertBoardAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingGeneratedKeyColumns("id");
        this.insertBoardContentAction = new SimpleJdbcInsert(dataSource)
                .withTableName("board_content");

    }

    @Override
    public List<Board> getArticleList(int category, Pagination pagination) {
        String sql = "SELECT id, user_id, title, read_count, nickname, category, root_board_id, parent_board_id, depth, depth_order, ip_addr, regdate, moddate, is_deleted " +
                "FROM board " +
                "WHERE category = :category " +
                "ORDER BY root_board_id DESC, id " +
                "LIMIT :firstRecordIndex, :recordCountPerPage";
        try {
            RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
            Map<String, Integer> map = new HashMap<>();
            map.put("category", category);
            map.put("firstRecordIndex", pagination.getFirstRecordIndex());
            map.put("recordCountPerPage", pagination.getRecordCountPerPage());

            return jdbc.query(sql, map, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board getArticle(Long id) {
        String sql = "SELECT a.id, a.read_count, a.title, a.nickname, a.category, a.user_id, a.root_board_id, a.parent_board_id, a.ip_addr, b.board_content AS content, a.regdate, a.moddate " +
                "FROM board a " +
                "INNER JOIN board_content b " +
                "WHERE a.id = b.board_id " +
                "AND a.id = :id";

        try {
            RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
            Map<String, ?> map = Collections.singletonMap("id", id);
            return jdbc.queryForObject(sql, map, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getTotalArticleCount(int category) {
        String sql = "SELECT COUNT(*) FROM board WHERE category = :category";
        try {
            Map<String, ?> map = Collections.singletonMap("category", category);
            return jdbc.queryForObject(sql, map, Long.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board getParentArticle(Long id) {
        String sql = "SELECT id, nickname, root_board_id, parent_board_id, depth, depth_order " +
                "FROM board " +
                "WHERE id = :id";
        try {
            RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
            Map<String, ?> map = Collections.singletonMap("id", id);
            return jdbc.queryForObject(sql, map, rowMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Board getArticleUserId(Long id) {
        String sql = "SELECT id, user_id FROM board WHERE id = :id";
        RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);
        Map<String, ?> map = Collections.singletonMap("id", id);

        try {
            return jdbc.queryForObject(sql, map, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Long writeArticle(Board board) {

        /*String sql = "insert into board (title, read_count, nickname, category, parent_board_id, user_id, depth, depth_order, ip_addr, regdate)" +
                " values (:title, :readCount, :nickname, :category, :parentBoardId, :userId, :depth, :depthOrder, :ipAddr, now())";*/
        board.setRegDate(new Date());
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        /*KeyHolder keyHolder = new GeneratedKeyHolder();*/
        try {
            return insertBoardAction.executeAndReturnKey(params).longValue();
            /*jdbc.update(sql, params, keyHolder);
            return keyHolder.getKey().longValue();*/
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long writeReply(Board board) {
        String sql = "insert into board (title, read_count, nickname, category, root_board_id, parent_board_id, user_id, depth, depth_order, ip_addr, regdate) " +
                "SELECT :title, :readCount, :nickname, :category, :rootBoardId, :parentBoardId, :userId, :depth, MAX(depth_order) + 1, :ipAddr, now() " +
                "FROM board " +
                "WHERE depth = :depth";
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            /*return insertBoardAction.executeAndReturnKey(params).longValue();*/
            jdbc.update(sql, params, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int writeArticleContent(Board board) {
        Map<String, Object> map = new HashMap<>();
        map.put("board_id", board.getId());
        map.put("board_content", board.getContent());
        try {
            return insertBoardContentAction.execute(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int setRootBoardId(Long id) {
        String sql = "UPDATE board " +
                "SET root_board_id = :id " +
                "WHERE id = :id";
        Map<String, ?> map = Collections.singletonMap("id", id);
        try {
            return jdbc.update(sql, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int modifyArticle(Board board) {
        String sql = "UPDATE board " +
                "SET   title = :title, " +
                "      ip_Addr = :ipAddr, " +
                "      moddate = now() " +
                "WHERE id = :id";

        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        try {
            return jdbc.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int modifyArticleContent(Board board) {
        String sql = "UPDATE board_content SET board_content = :content " +
                "WHERE board_id = :id";

        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        try {
            return jdbc.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteArticle(Long id) {
        String sql = "UPDATE board SET is_deleted = 1 WHERE id = :id";
        Map<String, ?> map = Collections.singletonMap("id", id);
        try {
            return jdbc.update(sql, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
