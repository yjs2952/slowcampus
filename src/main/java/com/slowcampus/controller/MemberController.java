package com.slowcampus.controller;

import com.slowcampus.dto.Authority;
import com.slowcampus.dto.Member;
import com.slowcampus.dto.MemberAuthority;
import com.slowcampus.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @PostMapping("/signin")
    public String signinMember(@ModelAttribute Member member, RedirectAttributes rda, HttpSession session) {

        Member loginMember = memberService.loginMember(member);
        loginMember.setAuthorityList(memberService.getMemberAuthority(member));
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

    @GetMapping("/mypage")
    public String showMemberInfo(HttpSession session, ModelMap modelMap) {
        Member member = (Member)session.getAttribute("login");
        modelMap.addAttribute("memberInfo", member);

        if (member.getAuthorityList() != null) {
            return "user/mypage";
        }
        else {
            return "/";
        }
    }

    @PostMapping("/mypage")
    public String setMemberPermission(@ModelAttribute MemberAuthority memberAuthority) {
        List<Authority> authorityList;
        Member member = new Member();
        Authority authority = new Authority();

        member.setId(memberAuthority.getUserId());
        authority.setAuthorityName(memberAuthority.getAuthorityName());

        authorityList = memberService.getMemberAuthority(member);

        for (Authority authorityCheck : authorityList) {
            if (authority.getAuthorityName().equals(authorityCheck.getAuthorityName())) {
            } else {
                memberService.deleteMemberAuthority(member, authority);
                memberService.setMemberAuthority(member, authority);
            }
        }

        return "redirect:/mypage";
    }
}
