package com.lsh.birthday.controller;

import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.entry.ResultDtoManager;
import com.lsh.birthday.service.HoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoneyCtr {
    
    @Autowired
    private HoneyService honeyService;
    
    @RequestMapping("/adsasd/asdas/1231a/asda21/124/aasf/243234/sdfsdf342/234234")
    public ResultDto<String> updateHoney(String hName){
        int i = honeyService.update(hName);
        if (i > 0) {
            return ResultDtoManager.success("设置成功~");
        } else {
            return ResultDtoManager.success("设置失败~");
        }
    }
    
}
