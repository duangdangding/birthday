package com.lsh.birthday.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lsh.birthday.entry.Comment;

public interface CommentService extends IService<Comment> {

    Long addComm(Comment comment);

}
