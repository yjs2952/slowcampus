package com.slowcampus.controller;

import com.slowcampus.dto.Board;
import com.slowcampus.dto.Image;
import com.slowcampus.dto.Pagination;
import com.slowcampus.service.BoardService;
import com.slowcampus.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import lombok.extern.java.Log;

import java.util.List;

@Controller
@Log
public class BoardController {
    private BoardService boardService;
    private ImageService imageService;

    @Autowired
    public BoardController(BoardService boardService, ImageService imageService) {
        this.boardService = boardService;
        this.imageService = imageService;
    }

    
    /**
     * @RequestParam으로 구현 된 현재 메소드는 아래와 같이 사용해도 동일한 결과를 얻을 수 있다.
     * public String getArticleList(@ModelAttribute Board board,
     *                                  ModelMap modelMap) {
     *         List<Board> boardList = boardService.getList(board.getCategory());
     *         List<Board> boardList = boardService.getArticleList(board.getCategory());
     * 그러나, Query Parameter의 기본 값 지정을 위하여 여기에서는 RequestParam을 사용하였다.
     */
    @RequestMapping(value = "/articles/list", method = RequestMethod.GET)
    public String getArticleList(@RequestParam(name = "category", required = false, defaultValue = "1") int category,
                                 ModelMap modelMap, Pagination pagination) {
        List<Board> boardList = boardService.getArticleList(category, pagination);
        modelMap.addAttribute("boardList", boardList);
        return "board/list";
    }

    // 게시글 상세보기.
    // /list/article/detail?id=<숫자>   게시글 보기 GET(댓글 보기 포함)
    @GetMapping("/boards/{category}/articles/detail")
    public String articleDetail(@RequestParam(name = "id") Long id, ModelMap modelMap) {
        Board board = boardService.getArticleCotent(id);
        modelMap.addAttribute("board", board);

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
        modelMap.addAttribute("images", imageList);


        return "articleDetail";
    }
}
