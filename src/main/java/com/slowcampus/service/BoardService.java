package com.slowcampus.service;

import com.slowcampus.dto.Board;
import com.slowcampus.dto.Category;
import com.slowcampus.dto.Pagination;

import java.util.List;

public interface BoardService {
    public List<Board> getArticleList(int category, Pagination pagination);

    public Board getArticleCotent(Long id);

    public Long getTotalArticleCount(int category);

    public List<Category> getCategoryList();

    public int writeArticle(Board board);

    public int modifyArticle(Board board);

    public int deleteArticle(Long id);

}
