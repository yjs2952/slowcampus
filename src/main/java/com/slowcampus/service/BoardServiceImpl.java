package com.slowcampus.service;

import com.slowcampus.dao.BoardDao;
import com.slowcampus.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao){
        this.boardDao = boardDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getArticleList(int category) {
        return boardDao.getArticleList(category);
    }

    @Override
    @Transactional
    public Board getArticleCotent(Long id) {
        return boardDao.getArticle(id);
    }

    @Override
    public int writeArticle(Board board) {
        board.setId(boardDao.writeArticle(board));
        boardDao.writeArticleContent(board);
        return 0;
    }

    @Override
    public int modifyArticle(Board board) {
        return 0;
    }

    @Override
    public int deleteArticle(Long id) {
        return 0;
    }
}
