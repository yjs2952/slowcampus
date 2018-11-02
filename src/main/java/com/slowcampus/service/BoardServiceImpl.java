package com.slowcampus.service;

import com.slowcampus.dao.BoardDao;
import com.slowcampus.dao.BoardDaoImpl;
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
    public List<Board> getList(int category) {
        return boardDao.getList(category);
    }

    @Override
    @Transactional
    public Board getBoardCotent(Long id) {
        return boardDao.getBoard(id);
    }

    @Override
    public int writeBoard(Board board) {
        board.setId(boardDao.writeBoard(board));
        boardDao.writeBoardContent(board);
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
