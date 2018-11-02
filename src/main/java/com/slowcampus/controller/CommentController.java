package com.slowcampus.controller;

import com.slowcampus.dto.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    // 댓글 작성
    @PostMapping("/comments/write")
    public void writeComment(Comment comment){

    }

    // 댓글 수정
    @PostMapping("/comments/modify")
    public void modifyComment(Comment comment) {

    }

    // 댓글 삭제
    @GetMapping("/comments/delete")
    public void deleteComment(Long id){
        
    }
}
