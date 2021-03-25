package com.lsh.birthday.service.impl;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
//        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("createtime");
        IPage<Comment> commentIPage = commentMapper.selectByPage1(pageData);
//        pageData.setRecords(commentMapper.selectPage(pageData, wrapper).getRecords());
        return commentIPage;
    }

    @Override
    public Long addComm(Comment comment) {
        return commentMapper.addComm(comment);
    }
}
