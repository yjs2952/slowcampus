package com.slowcampus.dao;

import com.slowcampus.dto.Comment;

import java.util.List;

public interface CommentDao {
    public List<Comment> getCommentList(Long boardId, int page);

    public List<Comment> getRecommentList(Long boardId, Long parentCommentId);
    public Long getCountOfRecommentList(Long boardId, Long parentCommentId);

    public Long writeComment(Comment comment);
    public int setCommentGroupId(Long id);

    public int modifyComment(Comment comment);

    public int deleteComment(Long id);


}
