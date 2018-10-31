package com.slowcampus.service;

import com.slowcampus.dao.CommentDao;
import com.slowcampus.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public List<Comment> getCommentList(Long boardId, int page) {
        return null;
    }

    @Override
    @Transactional
    public int writeComment(Comment comment) {
        return 0;
    }

    @Override
    @Transactional
    public int modifyComment(Comment comment) {
        return 0;
    }

    @Override
    @Transactional
    public int deleteComment(Long id) {
        return 0;
    }
}
