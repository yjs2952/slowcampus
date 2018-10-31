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

import javax.servlet.http.HttpServletRequest;

@Controller
@Log
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupMember() {
        log.info("회원 가입 요청이 수행되었습니다.");
        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signupMember(Member member) {
        log.info("회원 가입 POST 요청을 Member 객체로 받았습니다.");

        // MemberService로 받는다.
        memberService.signupMember(member);
    }

    @GetMapping("/signin")
    public String signinMember(){
        return "user/signin";
    }

    @PostMapping("/signin")
    public String signinMember(@ModelAttribute Member member, Model model) {
        System.out.println(member.toString());

        Member loginMember = memberService.loginMember(member);
        System.out.println("alalkasdlksadasdasd");
        // TODO: 2018-10-31 (yjs) : 세션에 권한정보 넣어줘야함, 권한 정보 조회(member_authority)
        if (loginMember != null) {
            Session session = new MapSession();
            session.setAttribute("id", member.getId());
            session.setAttribute("nickname", member.getNickname());
            session.setAttribute("email", member.getEmail());

            // 권한 추가햐야함
            return "redirect:/";
        } else {
            model.addAttribute("result", "아이디 혹은 비밀번호가 일치하지 않습니다.");
        }

        return "user/signin";
    }
}
