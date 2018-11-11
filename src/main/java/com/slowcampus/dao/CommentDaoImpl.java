package com.slowcampus.dao;

import com.slowcampus.dto.Comment;
import lombok.extern.java.Log;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Log
@Repository
public class CommentDaoImpl implements CommentDao {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public CommentDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }


    // is_deleted=0 추가.  값이 1이면 삭제된 코멘트임.
    @Override
    public List<Comment> getCommentList(Long boardId, int page) {
        String sql = "SELECT id, board_id, content, user_nickname, parent_nickname, group_id, depth, ip_addr,is_deleted, regdate, moddate " +
                "FROM comment WHERE board_id = :board_id and is_deleted=0 and depth=0 ORDER BY id DESC";
        try {
            Map<String, Long> params = Collections.singletonMap("board_id", boardId);
            RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

            return jdbc.query(sql, params, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /*
        대댓글 출력하는 sql 문
        select * from comment where board_id=게시판번호 and group_id=부모댓글번호 and depth=1 order by id desc;
     */
    @Override
    public List<Comment> getRecommentList(Long boardId, Long parentCommentId) {
        String sql = "SELECT id, board_id, content, user_nickname, parent_nickname, group_id, depth, ip_addr,is_deleted, regdate, moddate " +
                "FROM comment WHERE board_id = :board_id and is_deleted=0 and depth=1 and group_id=:group_id ORDER BY id DESC";
        Map<String, Long> params = new HashMap<String, Long>();
        try {
            params.put("board_id" , boardId);
            params.put("group_id" , parentCommentId);
            RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

            return jdbc.query(sql, params, rowMapper);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public Long getCountOfRecommentList(Long boardId, Long parentCommentId) {
        // boardId 는 설정 안해줘도 될거 같은데. 일단 알아보기 쉽게 해놨음.
        String sql = "SELECT COUNT(*) FROM comment WHERE depth=1 and board_id=:board_id and group_id=:group_id";
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("board_id" , boardId);
            map.put("group_id" , parentCommentId);
            return jdbc.queryForObject(sql, map, Long.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // regdate 는 어차피 디폴트 값으로 들어가니. insert 문에서 빼줌!
    @Override
    public int writeComment(Comment comment) {
        String sqlParentComment = "INSERT INTO comment(id, board_id, content, user_nickname, parent_nickname, group_id, ip_addr) " +
                "VALUES(null, :boardId, :content, :userNickname, :parentNickname, (SELECT LAST_INSERT_ID() + 1), :ipAddr)";
        String sqlChildComment = "INSERT INTO comment(id, board_id, content, user_nickname, parent_nickname, group_id, depth, ip_addr) " +
                "VALUES(null, :boardId, :content, :userNickname, :parentNickname, :groupId, :depth, :ipAddr)";
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
                log.info("대댓글 입력 쿼리 시작.");
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
            params.put("moddate", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

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
