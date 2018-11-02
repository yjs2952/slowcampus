package com.slowcampus.service;

import com.slowcampus.dto.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getArticleList(int category);

    public Board getArticleCotent(Long id);

    public int writeArticle(Board board);

    public int modifyArticle(Board board);

    public int deleteArticle(Long id);

}
