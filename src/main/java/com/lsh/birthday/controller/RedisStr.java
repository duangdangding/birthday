package com.lsh.birthday.controller;

import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.entry.ResultDtoManager;
import com.lsh.birthday.service.CommentService;
import com.lsh.birthday.service.RedisService;
import com.lsh.birthday.service.UserMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class RedisStr {
    Logger logger = LoggerFactory.getLogger(RedisStr.class);
    
    @Autowired
    private RedisService redisService;

    /**
     * 添加随机名字
     * @param words
     * @return
     */
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

    /**
     * 查询是不是有敏感词
     * @param words
     * @return
     */
    @RequestMapping("/findBannad")
    public ResultDto<Map<String,Object>> findBannadWords(String words) {
        Map<String,Object> map = new HashMap<>();
        int bannadWords = redisService.findBannadWords(words);
        map.put("bannad",bannadWords);
        return ResultDtoManager.success(map);
    }

    /**
     * 获得随机名字
     * @return
     */
    @RequestMapping("/getRandName")
    public ResultDto<String> getRandomeName() {
        String randomName = redisService.getRandomName();
        return ResultDtoManager.success(randomName);
    }

    @RequestMapping("/setRandName")
    public ResultDto<String> setRandomeName(String username) {
        long randomName = redisService.setRandomName(username);
        String res = "";
        if (randomName > 0) {
            res = "设置成功~";
        } else {
            res = "设置失败~";
        }
        return ResultDtoManager.success(res);
    }
    
    @RequestMapping("/getAllRanname")
    public ResultDto<Set<Object>> getAllRanname() {
        Set<Object> allRandName = redisService.getAllRandName();
        return ResultDtoManager.success(allRandName);
    }
    
    @RequestMapping("/getAllBannd")
    public ResultDto<Set<Object>> getAllBannd() {
        Set<Object> allbanad = redisService.getAllbanad();
        return ResultDtoManager.success(allbanad);
    }
}
