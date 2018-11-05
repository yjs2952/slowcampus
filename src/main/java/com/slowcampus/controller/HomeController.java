package com.slowcampus.controller;

import com.slowcampus.dto.Board;
import com.slowcampus.dto.Pagination;
import com.slowcampus.service.BoardService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Log
@Controller
@RequestMapping("/")
public class HomeController {

    private BoardService boardService;

    public HomeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        List<List<Board>> list = new ArrayList<>();
        Pagination pagination = new Pagination(5, 1);

        for (int i = 1; i <= 3; i++) {
            list.add(boardService.getArticleList(i, pagination));
        }

        modelMap.addAttribute("list", list);
        modelMap.addAttribute("categoryList", boardService.getCategoryList());
        return "index";
    }
}