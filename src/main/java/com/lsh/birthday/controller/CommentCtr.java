package com.lsh.birthday.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsh.birthday.entry.Comment;
import com.lsh.birthday.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentCtr {
    
    @Autowired
    private CommentService commentService;
    
    @RequestMapping("/commAll/{page}/{size}")
    public Object getAll(@PathVariable Integer page,@PathVariable Integer size) {
        IPage<Comment> all = commentService.findAll(page, size);
        return all.getRecords();
    }
}
