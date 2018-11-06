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
    @Transactional(readOnly = true)
    public Long getTotalArticleCount(int category) {
        return boardDao.getTotalArticleCount(category);
    }

    @Override
    @Transactional(readOnly = true)
    public String getArticleUserId(Long id) {
        String articleUserId;
        Board board = boardDao.getArticleUserId(id);
        articleUserId = board.getUserId();
        return articleUserId;
    }

    @Override
    @Transactional(readOnly = true)
    public Board getArticleCotent(Long id) {
        return boardDao.getArticle(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    @Override
    @Transactional
    public Long writeArticle(Board board) {
        Long id = boardDao.writeArticle(board);
        board.setId(id);
        boardDao.setRootBoardId(id);
        boardDao.writeArticleContent(board);
        return id;
    }

    @Override
    @Transactional
    public int modifyArticle(Board board) {
        boardDao.modifyArticle(board);
        boardDao.modifyArticleContent(board);
        return 0;
    }

    @Override
    @Transactional
    public int deleteArticle(Long id) {
        return boardDao.deleteArticle(id);
    }
}
