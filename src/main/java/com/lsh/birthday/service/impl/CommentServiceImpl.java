package com.lsh.birthday.service.impl;

import cn.hutool.db.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsh.birthday.entry.Comment;
import com.lsh.birthday.mapper.CommentMapper;
import com.lsh.birthday.service.CommentService;
import com.lsh.birthday.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService {
    
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RedisUtil redisUtil;
    
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

    @Override
    public int findBannadWords(String words) {
        int res = 0;
        if (!words.isEmpty()) {
            String replace = words.replace(" ", "").replace(",", "").replace("、", "")
                    .replace("，", "").replace("。", "").replace("/", "")
                    .replace("\\", "").replace("|", "").replace("*", "")
                    .replace("%", "").replace("&", "").replace("#", "")
                    .replace("^", "").replace("@", "").replace("~", "");
            Set<Object> bannaword = redisUtil.sGet("bannaword");
            Iterator<Object> iterator = bannaword.iterator();
            while (iterator.hasNext()) {
                String next = (String) iterator.next();
                if (replace.contains(next)) {
                    res = 1;
                    break;
                }
            }
        }
        return res;
    }
}
