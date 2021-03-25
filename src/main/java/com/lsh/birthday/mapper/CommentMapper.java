package com.lsh.birthday.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lsh.birthday.entry.Comment;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
//    public List<Comment> findAll();
    IPage<Comment> selectByPage1(IPage<Comment> iPage);
    
    Long addComm(Comment comment);
}
