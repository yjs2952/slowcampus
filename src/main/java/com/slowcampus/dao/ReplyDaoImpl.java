package com.slowcampus.dao;

import com.slowcampus.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ReplyDaoImpl implements ReplyDao {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public ReplyDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Reply> getList(Long boardId, int page) {
        String sql = "SELECT id, board_id, content, user_nickname, parent_nickname, group_id, depth, is_deleted, regdate, moddate " +
                "FROM reply WHERE board_id = :board_id ORDER BY id DESC";
        try {
            Map<String, Long> params = Collections.singletonMap("board_id", boardId);
            RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Reply.class);

            return jdbc.query(sql, params, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int writeReply(Reply reply) {
        String sqlParentReply = "INSERT INTO reply(id, board_id, content, user_nickname, parent_nickname, group_id, ip_addr, regdate) " +
                "VALUES(null, :boardId, :content, :userNickname, :parentNickname, (SELECT LAST_INSERT_ID() + 1), :ipAddr, :regdate)";
        String sqlChildReply = "INSERT INTO reply(id, board_id, content, user_nickname, parent_nickname, group_id, depth, ip_addr, regdate) " +
                "VALUES(null, :boardId, :content, :userNickname, :parentNickname, :groupId, :depth, :ipAddr, :regdate)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(reply);

        try {
            /**
             *                 참고용 : 주석 내용과 아래 내용은 같다.
             *                 Map<String, Object> params = new HashMap<>();
             *                 params.put("board_id", reply.getBoardId());
             *                 params.put("content", reply.getContent());
             *                 params.put("user_nickname", reply.getUserNickname());
             *                 // 일반 댓글(부모 댓글)의 경우 userNickname과 parentNIckname은 동일합니다.
             *                 params.put("parent_nickname", reply.getUserNickname());
             *                 params.put("ip_addr", reply.getIpAddr());
             *                 params.put("regdate", reply.getRegdate());
             *
             *                 return jdbc.update(sqlParentReply, params);
             */
            if (reply.getDepth() == 0) {
                // 일반 댓글(부모 댓글) Query 시작합니다.
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbc.update(sqlParentReply, params, keyHolder);

                return keyHolder.getKey().intValue();

            } else {
                // 자식 댓글(대댓글) Query 시작
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbc.update(sqlChildReply, params, keyHolder);

                return keyHolder.getKey().intValue();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int modifyReply(Reply reply) {
        String sql = "UPDATE reply SET content = :content, ip_addr = :ip_addr, moddate = :moddate WHERE id = :id";
        Map<String, Object> params = new HashMap<>();

        try {
            params.put("content", reply.getContent());
            params.put("ip_addr", reply.getIpAddr());
            params.put("moddate", reply.getModdate());

            params.put("id", reply.getId());

            return jdbc.update(sql, params);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int deleteReply(Long id) {
        String sql = "UPDATE reply SET is_deleted = 1 WHERE id = :id";
        Map<String, Long> param = Collections.singletonMap("id", id);

        try {
            return jdbc.update(sql, param);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
