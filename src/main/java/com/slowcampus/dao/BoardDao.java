package com.slowcampus.dao;

import com.slowcampus.dto.Board;

import java.util.List;

public interface BoardDao {
    public List<Board> getArticleList(int category);

    public Board getArticle(Long id);

    public Long writeArticle(Board board);

    public int writeArticleContent(Board board);

    public int modifyArticle(Board board);

    public int modifyArticleContent(Board board);

    public int deleteArticle(Long id);
}
