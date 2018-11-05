package com.slowcampus.service;

import com.slowcampus.dao.BoardDao;
import com.slowcampus.dao.CategoryDao;
import com.slowcampus.dto.Board;
import com.slowcampus.dto.Category;
import com.slowcampus.dto.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;
    private CategoryDao categoryDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao, CategoryDao categoryDao){
        this.boardDao = boardDao;
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getArticleList(int category, Pagination pagination) {
        return boardDao.getArticleList(category, pagination);
    }

    @Override
    public Long getTotalArticleCount(int category) {
        return boardDao.getTotalArticleCount(category);
    }

    @Override
    public String getArticleUserId(Long id) {
        String articleUserId;
        Board board = boardDao.getArticleUserId(id);
        articleUserId = board.getUserId();
        return articleUserId;
    }

    @Override
    @Transactional
    public Board getArticleCotent(Long id) {
        return boardDao.getArticle(id);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
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
        return boardDao.deleteArticle(id);
    }
}
