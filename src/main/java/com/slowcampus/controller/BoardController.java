package com.slowcampus.controller;

import com.google.gson.Gson;
import com.slowcampus.dto.*;
import com.slowcampus.service.BoardService;
import com.slowcampus.service.CommentService;
import com.slowcampus.service.ImageService;
import com.slowcampus.util.AzureApp;
import com.slowcampus.util.PageUtil;
import lombok.extern.java.Log;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log
public class BoardController {
    private BoardService boardService;
    private ImageService imageService;

    @Autowired
    public BoardController(BoardService boardService, ImageService imageService,CommentService commentService) {
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
        pagination.setTotalRecordCount(boardService.getTotalArticleCount(category).intValue());

        modelMap.addAttribute("boardList", boardList);
        modelMap.addAttribute("pageList",
                PageUtil.getPageNavigation(pagination, "/articles/list", String.valueOf(category)));

        return "board/list";
    }

    @GetMapping("/boards/{category}/articles/delete")
    public String showArticleDelete(@ModelAttribute Board board,
                                    HttpSession httpSession, ModelMap map) {

        Member member = (Member)httpSession.getAttribute("login");
        map.addAttribute("board", board);

        if ((member != null) && (member.getId().equals(boardService.getArticleUserId(board.getId())))) {
            return "board/delete";
        } else {
            return "redirect:/boards/" + board.getCategory()
                    + "/articles/detail?id=" + board.getId();
        }
    }

    @PostMapping("/boards/{category}/articles/delete")
    public String articleDelete(@ModelAttribute Board board) {
        try {
            boardService.deleteArticle(board.getId());
            log.info("게시물이 삭제되었습니다.");
        } catch (Exception ex) {
            log.info("게시물 삭제 도중 오류가 발생하였습니다.");
            throw new RuntimeException(ex);
        }

        return "redirect:/articles/list?category=" + board.getCategory();
    }

    // 게시글 상세보기.
    // /list/article/detail?id=<숫자>   게시글 보기 GET(댓글 보기 포함)
    @GetMapping("/boards/{category}/articles/detail")
    public String articleDetail(@PathVariable(value = "category") int category,
                                @RequestParam(name = "id") Long id, ModelMap modelMap,HttpSession session) {
        Member member = (Member) session.getAttribute("login");
        modelMap.addAttribute("member", member);
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

    @GetMapping("/boards/{category}/articles/write")
    public String articleWriteForm(){
        return "board/writeForm";
    }

    @PostMapping("/boards/{category}/articles/write")
    public String articleWrite(Board board, HttpServletRequest req,
                                MultipartFile[] files, Model model) {
        Member member = (Member) req.getSession().getAttribute("login");
        /*HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();*/
        board.setUserId(member.getId());
        board.setNickname(member.getNickname());
        board.setIpAddr(req.getRemoteAddr());
        Long id = boardService.writeArticle(board);

        // 사진없으면 여기서 끝.
        // 사진이 무조건 한개는 들어가는듯...
        if(files.length == 1 && files[0].getOriginalFilename().equals("")) {
            log.info("garbage picture.");
            return "redirect:/boards/{category}/articles/detail?id="+id;
        }

        //System.out.println("MultipartFile files.length" + files.length);
        /*
            image upload.
         */
        for(MultipartFile file : files) {
            /* 기본 폴더 설정, 날짜 경로 더해주기는 파일들의 공통적인 부분. for 위로?*/

            if(file.getOriginalFilename().equals("")){
                System.out.println("쓰레기.");
                continue;
            }


            File dir = new File(".");
            String path = dir.getAbsolutePath();

            String datePath = AzureApp.calcPath(path);

            // /tmp/2018_11_01
            path = path + datePath;

            // savedName : ~~~~~~~~~~~~~_원래이름.
            UUID uid = UUID.randomUUID();
            String savedName = uid.toString() + "_" + file.getOriginalFilename();

            File sourceFile = new File(path , savedName);


            Image image = new Image();

            image.setOriginalName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setSize(file.getSize());
            image.setSaveName(savedName);
            image.setPath(datePath + File.separator +savedName);
            image.setRegDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            image.setBoardId(id); // 위에 board 만들고 나면 생기는 id

            imageService.uploadImage(image);
            // 이미지 db업로드 완료.

            imageService.uploadImageToAzure(file,sourceFile,datePath,path);

        }


        return "redirect:/boards/{category}/articles/detail?id="+id;
    }

    @GetMapping("/boards/{category}/articles/modify")
    public String articleModifyForm(Long id, HttpSession session, ModelMap modelMap) {
        Member member = (Member) session.getAttribute("login");
        Board board = boardService.getArticleCotent(id);
        if (!member.getId().equals(board.getUserId())) {
            return "redirect:/boards/{category}/articles/detail?id="+id;
        }
        modelMap.addAttribute("board", board);
        return "board/modifyForm";
    }

    @PostMapping("/boards/{category}/articles/modify")
    public String articleModify(Board board, HttpServletRequest req, RedirectAttributes rda) {
        Member member = (Member) req.getSession().getAttribute("login");

        if (!member.getId().equals(board.getUserId())) {
            log.info("잘못된 접근");
        } else {
            board.setNickname(member.getNickname());
            board.setIpAddr(req.getRemoteAddr());
            boardService.modifyArticle(board);
        }

        return "redirect:/boards/{category}/articles/detail?id="+board.getId();
    }

}
