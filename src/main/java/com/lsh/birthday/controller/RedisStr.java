package com.lsh.birthday.controller;

import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.entry.ResultDtoManager;
import com.lsh.birthday.entry.UserMsg;
import com.lsh.birthday.service.CommentService;
import com.lsh.birthday.service.RedisService;
import com.lsh.birthday.service.UserMsgService;
import com.lsh.birthday.utils.PinYinUtils;
import com.lsh.birthday.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class RedisStr {
    Logger logger = LoggerFactory.getLogger(RedisStr.class);

    @Autowired
    private RedisService redisService;

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

    @RequestMapping("/getXzys")
    public String getXZYS(HttpSession session) {
        UserMsg user = (UserMsg) session.getAttribute("user");
        String userXz = user.getUserXz();
        String xzys = redisService.getXzys(userXz);
        return xzys;
    }
}
