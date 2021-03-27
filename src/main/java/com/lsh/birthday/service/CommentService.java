package com.lsh.birthday.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.birthday.entry.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

    public IPage<Comment> findAll(Integer page, Integer size);
    
    Long addComm(Comment comment);

    int findBannadWords(String words);
}
