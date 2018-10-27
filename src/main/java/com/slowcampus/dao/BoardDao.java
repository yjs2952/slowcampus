package com.slowcampus.dao;

import com.slowcampus.dto.Board;

import java.util.List;

public interface BoardDao {
    public List<Board> getList(int page, String keyword);

    public Board getBoard(Long id);

    public int writeBoard(Board board);

    public int modifyBoard(Board board);

    public int deleteBoard(Long id);
}
