package com.lsh.birthday.service.impl;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.birthday.entry.Comment;
import com.lsh.birthday.mapper.CommentMapper;
import com.lsh.birthday.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService {
    
    @Autowired
    private CommentMapper commentMapper;
    
    @Override
    public IPage<Comment> findAll(Integer page, Integer size) {
        Page<Comment> pageData = new Page<>(page,size);
        pageData.setRecords(commentMapper.selectPage(pageData, null).getRecords());
        return pageData;
    }

    @Override
    public Long addComm(Comment comment) {
        return commentMapper.addComm(comment);
    }
}
