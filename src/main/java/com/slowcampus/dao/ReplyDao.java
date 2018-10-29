package com.slowcampus.dao;

import com.slowcampus.dto.Reply;

import java.util.List;

public interface ReplyDao {
    public List<Reply> getList(Long boardId, int page);

    public int writeReply(Reply reply);

    public int modifyReply(Reply reply);

    public int deleteReply(Long id);
}
