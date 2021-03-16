package com.lsh.birthday.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginCtr {
    @RequestMapping("/login")
    public String toLogin( String username, HttpSession session) {
        if (!StrUtil.hasEmpty(username)) {
            int length = username.length();
            if (length > 5) {
                username = username.substring(0,5);
            }
        } else {
            username = "无名氏";
        }
        session.setAttribute("username",username);
        return username;
    }
}
