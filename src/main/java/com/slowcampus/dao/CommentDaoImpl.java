package com.slowcampus.dao;

import com.slowcampus.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class CommentDaoImpl implements CommentDao {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public CommentDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Comment> getCommentList(Long boardId, int page) {
        String sql = "SELECT id, board_id, content, user_nickname, parent_nickname, group_id, depth, is_deleted, regdate, moddate " +
                "FROM comment WHERE board_id = :board_id ORDER BY id DESC";
        try {
            Map<String, Long> params = Collections.singletonMap("board_id", boardId);
            RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

            return jdbc.query(sql, params, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int writeComment(Comment comment) {
        String sqlParentComment = "INSERT INTO comment(id, board_id, content, user_nickname, parent_nickname, group_id, ip_addr, regdate) " +
                "VALUES(null, :boardId, :content, :userNickname, :parentNickname, (SELECT LAST_INSERT_ID() + 1), :ipAddr, :regdate)";
        String sqlChildComment = "INSERT INTO comment(id, board_id, content, user_nickname, parent_nickname, group_id, depth, ip_addr, regdate) " +
                "VALUES(null, :boardId, :content, :userNickname, :parentNickname, :groupId, :depth, :ipAddr, :regdate)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        try {
            /**
             *                 참고용 : 주석 내용과 아래 내용은 같다.
             *                 Map<String, Object> params = new HashMap<>();
             *                 params.put("board_id", comment.getBoardId());
             *                 params.put("content", comment.getContent());
             *                 params.put("user_nickname", comment.getUserNickname());
             *                 // 일반 댓글(부모 댓글)의 경우 userNickname과 parentNIckname은 동일합니다.
             *                 params.put("parent_nickname", comment.getUserNickname());
             *                 params.put("ip_addr", comment.getIpAddr());
             *                 params.put("regdate", comment.getRegdate());
             *
             *                 return jdbc.update(sqlParentcomment, params);
             */
            if (comment.getDepth() == 0) {
                // 일반 댓글(부모 댓글) Query 시작합니다.
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbc.update(sqlParentComment, params, keyHolder);

                return keyHolder.getKey().intValue();

            } else {
                // 자식 댓글(대댓글) Query 시작
                KeyHolder keyHolder = new GeneratedKeyHolder();
                jdbc.update(sqlChildComment, params, keyHolder);

                return keyHolder.getKey().intValue();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int modifyComment(Comment comment) {
        String sql = "UPDATE comment SET content = :content, ip_addr = :ip_addr, moddate = :moddate WHERE id = :id";
        Map<String, Object> params = new HashMap<>();

        try {
            params.put("content", comment.getContent());
            params.put("ip_addr", comment.getIpAddr());
            params.put("moddate", comment.getModdate());

            params.put("id", comment.getId());

            return jdbc.update(sql, params);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int deleteComment(Long id) {
        String sql = "UPDATE comment SET is_deleted = 1 WHERE id = :id";
        Map<String, Long> param = Collections.singletonMap("id", id);

        try {
            return jdbc.update(sql, param);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
