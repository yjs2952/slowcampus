package com.slowcampus.dao;

import com.slowcampus.dto.Reply;

import java.util.List;

public class ReplyDaoImpl implements ReplyDao {
    @Override
    public List<Reply> getList(Long boardId, int page) {
        return null;
    }

    @Override
    public int writeReply(Reply reply) {
        return 0;
    }

    @Override
    public int modifyReply(Reply reply) {
        return 0;
    }

    @Override
    public int deleteReply(Long id) {
        return 0;
    }
}
