package com.lsh.birthday.service.impl;

import com.lsh.birthday.service.RedisService;
import com.lsh.birthday.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addBannadWords(String words) {
        redisUtil.sSet("bannaword",words);
    }
}
