package com.slowcampus.controller;

import com.slowcampus.dto.Member;
import com.slowcampus.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupMember() {
        log.info("회원 가입 요청이 수행되었습니다.");
        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupMember(Member member) {
        int successCount = 0;

        log.info("회원 가입 POST 요청을 Member 객체로 받았습니다.");

        // MemberService로 받는다.
        successCount = memberService.signupMember(member);
        if (successCount != 0) {
            log.info("MemberController : 회원 가입에 성공하였습니다.");
            return "redirect:/";
        } else {
            log.info("MemberController : 회원 가입에 실패하였습니다.");
            return "false";
        }
    }

    @GetMapping("/signin")
    public String signinMember() {
        return "user/signin";
    }

    @PostMapping("/signupPost")
    public String signinMember(@ModelAttribute Member member, RedirectAttributes rda, HttpSession session) {

        Member loginMember = memberService.loginMember(member);
        // TODO: 2018-10-31 (yjs) : 세션에 권한정보 넣어줘야함, 권한 정보 조회(member_authority)
        if (loginMember != null) {
            session.setAttribute("login", loginMember);
            System.out.println("세션 시작");
            //session.setAttribute("권한");

            // 권한 추가햐야함
            return "redirect:/";
        } else {
            rda.addFlashAttribute("result", "아이디 혹은 비밀번호가 일치하지 않습니다.");
        }

        return "redirect:/signin";
    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
        if (session.getAttribute("login") != null) {
            System.out.println("세션 종료");
            session.removeAttribute("login");
            session.invalidate();
        }
        return "redirect:/";
    }
}
