package com.slowcampus.controller;

import com.slowcampus.dto.Comment;
import com.slowcampus.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class CommentController {
    /*
        대댓글 출력하는 sql 문
        select * from comment where board_id=게시판번호 and group_id=부모댓글번호 and depth=1 order by id desc;

     */

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }

    @PostMapping("/comment/write")
    public String writeComment(@RequestParam(name="commentWriteContent") String commentWriteContent,
                               @RequestParam(name="boardId") Long boardId) {

        // 부모 댓글 입력 테스트.
        Comment comment = new Comment();
        comment.setBoardId(boardId);
        comment.setContent(commentWriteContent);
        comment.setUserNickname("입력테스터1");
        comment.setParentNickname("입력테스터1");
        comment.setIpAddr("192.168.0.136");
        comment.setRegdate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        commentService.writeComment(comment);

        return "redirect:/list/article/detail?id="+boardId;
    }


}
