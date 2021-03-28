package com.lsh.birthday.service.impl;

import com.lsh.birthday.service.RedisService;
import com.lsh.birthday.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {
    
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addBannadWords(String words) {
        redisUtil.sSet("bannaword",words);
    }

    @Override
    public Set<Object> getAllbanad() {
        Set<Object> bannaword = redisUtil.sGet("bannaword");
        return bannaword;
    }

    @Override
    public Set<Object> getAllRandName() {
        Set<Object> randomName = redisUtil.sGet("randomName");
        return randomName;
    }

    @Override
    public String getRandomName() {
        String randomName = (String) redisUtil.sGetRan("randomName");
        return randomName;
    }

    @Override
    public long setRandomName(String username) {
        long l = redisUtil.sSet("randomName", username);
        return l;
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
