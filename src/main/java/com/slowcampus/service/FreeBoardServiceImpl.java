package com.slowcampus.service;

import com.slowcampus.dto.Board;

import java.util.List;

public class FreeBoardServiceImpl implements BoardService {
    @Override
    public List<Board> getList(int page, String keyword) {
        return null;
    }

    @Override
    public Board getBoardCotent(Long id) {
        return null;
    }

    @Override
    public int writeBoard(Board board) {
        return 0;
    }

    @Override
    public int modifyBoard(Board board) {
        return 0;
    }

    @Override
    public int deleteBoard(Long id) {
        return 0;
    }
}
