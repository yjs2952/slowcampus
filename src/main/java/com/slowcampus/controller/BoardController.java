package com.slowcampus.controller;

import com.slowcampus.dto.Board;
import com.slowcampus.service.BoardService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Log
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    /**
     * @RequestParam으로 구현 된 현재 메소드는 아래와 같이 사용해도 동일한 결과를 얻을 수 있다.
     * public String getArticleList(@ModelAttribute Board board,
     *                                  ModelMap modelMap) {
     *         List<Board> boardList = boardService.getList(board.getCategory());
     * 그러나, Query Parameter의 기본 값 지정을 위하여 여기에서는 RequestParam을 사용하였다.
     */
    public String getArticleList(@RequestParam(name = "category", required = false, defaultValue = "1") int category,
                                 ModelMap modelMap) {
        List<Board> boardList = boardService.getList(category);
        modelMap.addAttribute("boardList", boardList);

        return "board/list";
    }
}
