package com.lsh.birthday.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.birthday.entry.Comment;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    public List<Comment> findAll();
    
    Long addComm(Comment comment);
}
