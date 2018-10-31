package com.slowcampus.service;

import com.slowcampus.dto.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentList(Long boardId, int page);

    public int writeComment(Comment comment);

    public int modifyComment(Comment comment);

    public int deleteComment(Long id);
}
