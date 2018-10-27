package com.slowcampus.service;

import com.slowcampus.dto.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getList(int page, String keyword);

    public Board getBoardCotent(Long id);

    public int writeBoard(Board board);

    public int modifyBoard(Board board);

    public int deleteBoard(Long id);

}
