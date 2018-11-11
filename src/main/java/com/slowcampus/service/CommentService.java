package com.slowcampus.service;

import com.slowcampus.dto.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentList(Long boardId, int page);

    public List<Comment> getRecommentList(Long boardId, Long parentCommentId);
    public Long getCountOfRecommentList(Long boardId, Long parentCommentId);

    public Long writeComment(Comment comment);

    public Long writeRecomment(Comment comment);

    public int modifyComment(Comment comment);

    public int deleteComment(Long id);
}
