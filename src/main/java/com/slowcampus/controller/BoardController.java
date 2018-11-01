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

import java.util.List;

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
        /*
            사진이 한개만 있어도 리스트로 출력이 가능.
            select를 이용해 해당 게시물에 있는 사진이 몇개인지 값을 가져오고
            그 값이 1일때는 Image로.
            여러개 일때는 List<Image> 로 출력하게 하면.??
            select 를 이용해야 하기 때문에 시간이 더 오래 걸리나??
         */

        // 한개만.
//        Image image = imageService.getImage(id);
//        System.out.println(image.getPath());
//        modelMap.addAttribute("image", image);

        // 이미지 여러개
        List<Image> imageList = imageService.getImageList(id);
        System.out.println(imageList.size());
        modelMap.addAttribute("images" , imageList);


        return "articleDetail";
    }





}
