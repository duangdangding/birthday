package com.lsh.birthday.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsh.birthday.entry.BootStrapResult;
import com.lsh.birthday.entry.Comment;
import com.lsh.birthday.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommentCtr {
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping("/commAll")
    public BootStrapResult<Comment> getAll(Integer pageNumber, Integer pageSize, HttpServletRequest request) {
        IPage<Comment> all = commentService.findAll(pageNumber, pageSize);
        return new BootStrapResult<Comment>(all.getRecords(),all.getTotal());
    }
}
