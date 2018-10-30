package com.slowcampus.controller;

import com.slowcampus.dto.Member;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log
public class UserController {

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupMember() {
        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signupMember(Member member) {
        System.out.println(member.getId());
        System.out.println(member.getPassword());
        System.out.println(member.getNickname());
        System.out.println(member.getEmail());
    }
}
