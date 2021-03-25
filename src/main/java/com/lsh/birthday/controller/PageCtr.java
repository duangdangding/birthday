package com.lsh.birthday.controller;

import com.lsh.birthday.entry.Common;
import com.lsh.birthday.entry.Honey;
import com.lsh.birthday.service.CommonService;
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
    
    @Autowired
    private CommonService commonService;
    
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
        Common common = new Common();
        common.setCoEnabled(1);
        common.setCoKey("websocket_ip");
        Common result = commonService.getByEnabled(common);
        session.setAttribute("websocket_ip",result.getCoValue());
        return "index2";
    }
    @RequestMapping("/tocommonts")
    public String tocommonts() {
        
        return "comments";
    }
    
}
