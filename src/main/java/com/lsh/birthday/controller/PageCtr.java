package com.lsh.birthday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageCtr {
    
    @RequestMapping("/tologin")
    public String toLogin(HttpSession session) {
        return "login";
    }
    @RequestMapping("/")
    public String toLogin2(HttpSession session) {
        return "login";
    }
    @RequestMapping("/toindex")
    public String toIndex(HttpSession session) {
        return "index2";
    }
    
}
