package com.slowcampus.controller;

import com.slowcampus.dto.Member;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    //@GetMapping("/")

    @RequestMapping(method=GET)
    public String home() {
        return "index";
    }
}
