package com.slowcampus.controller;

import com.slowcampus.dto.Board;
import com.slowcampus.dto.Image;
import com.slowcampus.service.BoardService;
import com.slowcampus.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    // 게시글 상세보기.
    // /list/article/detail?id=<숫자>   게시글 보기 GET(댓글 보기 포함)

    @Autowired
    private BoardService boardService;

    @Autowired
    private ImageService imageService;


    @GetMapping("/list/article/detail")
    public String articleDetail(@RequestParam(name="id") Long id, ModelMap modelMap) {
        Board board =boardService.getBoardCotent(id);
        System.out.println(board.getTitle());
        modelMap.addAttribute("board" , board);

        System.out.println("id : " + id);
        Image image = imageService.getImage(id);
        System.out.println(image.getPath());
        modelMap.addAttribute("image", image);


        return "articleDetail";
    }





}
