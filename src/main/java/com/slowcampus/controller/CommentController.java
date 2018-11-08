package com.slowcampus.controller;

import com.slowcampus.dto.Comment;
import com.slowcampus.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


/*
-ResponseEntity 타입은 개발자가 직접 결과 데이터 + HTTP의 상태코드를 직접 제어할 수 있는 클래스다.
-RestController는 기존의 특정한 jsp와 같은 뷰를 만들어 내는 것이 아닌 REST방식의 데이터 처리를 위해서
사용하는 애노테이션.
-RequestBody는 전송된 JSON 데이터를 객체로 변환해주는 애노테이션. ModelAttribute와
    유사한 역할을 하지만 JSON에서 사용된다는 점의 차이.
-
 */

@RestController
public class CommentController {
    /*
        대댓글 출력하는 sql 문
        select * from comment where board_id=게시판번호 and group_id=부모댓글번호 and depth=1 order by id desc;

     */

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }


    @RequestMapping("/comment/list/{boardid}")
    public ResponseEntity<List<Comment>> listComment(@PathVariable("boardid") Long boardid) {

        ResponseEntity<List<Comment>> entity = null;

        try {
            entity = new ResponseEntity<>(commentService.getCommentList(boardid,0),HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @RequestMapping(value="/comment/write" , method=RequestMethod.POST)
    public ResponseEntity<String> writeComment(@RequestBody Comment comment) {

        ResponseEntity<String> entity=null;
        try {
            commentService.writeComment(comment);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 대댓글 쓰기.

    @RequestMapping(value="/comment/write/recomment")
    public ResponseEntity<String> writeReComment(@RequestBody Comment comment) {
        ResponseEntity<String> entity=null;
        try {
            commentService.writeComment(comment);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }



    @RequestMapping(value="/comment/update/{commentid}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public ResponseEntity<String> updateComment(
            @PathVariable("commentid") Long commentid,
            @RequestBody Comment comment) {

        ResponseEntity<String> entity = null;

        try {
            comment.setId(commentid);
            commentService.modifyComment(comment);

            entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return entity;
    }


    // 실제 DB의 내용은 안지워지고 is_deleted만 1로 바뀐다.
    @RequestMapping(value="/comment/delete/{commentid}", method=RequestMethod.POST)
    public ResponseEntity<String> deleteComment(@PathVariable("commentid") Long commentid) {

        ResponseEntity<String> entity = null;
        try {
            commentService.deleteComment(commentid);
            entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return entity;
    }




}
