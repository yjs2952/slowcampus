package com.slowcampus.dao;

import com.slowcampus.dto.Comment;

import java.util.List;

public interface CommentDao {
    public List<Comment> getCommentList(Long boardId, int page);

    public List<Comment> getCommentOfCommentList(Long boardId, Long parentCommentId);

    public int writeComment(Comment comment);

    public int modifyComment(Comment comment);

    public int deleteComment(Long id);
}
