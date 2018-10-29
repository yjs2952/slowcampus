package com.slowcampus.dao;

import com.slowcampus.dto.Board;

import java.util.List;

public class NoticeBoardDaoImpl implements BoardDao {
    @Override
    public List<Board> getList(int page, String keyword) {
        return null;
    }

    @Override
    public Board getBoard(Long id) {
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
