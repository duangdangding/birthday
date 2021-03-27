package com.lsh.birthday.controller;

import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.entry.ResultDtoManager;
import com.lsh.birthday.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisStr {
    Logger logger = LoggerFactory.getLogger(RedisStr.class);
    
    @Autowired
    private RedisService redisService;
    
    @RequestMapping("/redis/add")
    public ResultDto<String> addBannadWords(String words){
        String res = "";
        try {
            redisService.addBannadWords(words);
            res = "添加成功~";
        } catch (Exception e) {
            res = "添加失败~";
            logger.error("添加敏感词失败：{}",e.getMessage());
        }
        return ResultDtoManager.success(res);
    }
}
