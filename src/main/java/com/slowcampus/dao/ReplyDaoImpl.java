package com.slowcampus.dao;

import com.slowcampus.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ReplyDaoImpl implements ReplyDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ReplyDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("reply")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Reply> getList(Long boardId, int page) {
        String sql = "SELECT id, board_id, content, user_nickname, parent_nickname, group_id, is_deleted, regdate, moddate " +
                "FROM reply WHERE board_id = :board_id ORDER BY id DESC";
        try {
            Map<String, Long> params = Collections.singletonMap("board_id", boardId);
            RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Reply.class);

            return  jdbc.query(sql, params, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int writeReply(Reply reply) {
//        String sql = "INSERT INTO reply(board_id, content, user_nickname, parent_nickname, group_id, regdate)" +
//                " VALUES(:board_id, :content, :user_nickname, :parent_nickname, :group_id, :regdate)";
        try {
//            SqlParameterSource param = new BeanPropertySqlParameterSource(reply);
            SqlParameterSource param = new BeanPropertySqlParameterSource(reply);
//            return jdbc.update(sql, param);
            return simpleJdbcInsert.executeAndReturnKey(param).intValue();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int modifyReply(Reply reply) {
        return 0;
    }

    @Override
    public int deleteReply(Long id) {
        return 0;
    }
}
