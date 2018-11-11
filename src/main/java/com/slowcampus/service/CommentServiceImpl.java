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

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    @Transactional
    public List<Comment> getCommentList(Long boardId, int page) {
        return commentDao.getCommentList(boardId, page);
    }

    @Override
    @Transactional
    public List<Comment> getRecommentList(Long boardId, Long parentCommentId) {
        return commentDao.getRecommentList(boardId, parentCommentId);
    }

    @Override
    @Transactional
    public Long getCountOfRecommentList(Long boardId, Long parentCommentId) {
        return commentDao.getCountOfRecommentList(boardId,parentCommentId);

    }



    @Override
    @Transactional
    public int writeComment(Comment comment) {
        return commentDao.writeComment(comment);
    }

    @Override
    @Transactional
    public int modifyComment(Comment comment) {
        return commentDao.modifyComment(comment);
    }

    @Override
    @Transactional
    public int deleteComment(Long id) {
        return commentDao.deleteComment(id);
    }
}
