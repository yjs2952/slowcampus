package com.slowcampus.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log
public class UserController {

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public void signupMember() {

    }

}
