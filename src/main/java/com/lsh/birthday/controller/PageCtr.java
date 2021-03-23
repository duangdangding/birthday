package com.lsh.birthday.controller;

import com.lsh.birthday.entry.Honey;
import com.lsh.birthday.service.HoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageCtr {
    
    @Autowired
    private HoneyService honeyService;
    
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
        Honey honey = honeyService.find();
        session.setAttribute("honey",honey.gethName());
        return "index2";
    }
    
}
