package com.lsh.birthday.controller;

import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.service.HoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoneyCtr {
    
    @Autowired
    private HoneyService honeyService;
    
    @RequestMapping("/adsasd/asdas/1231a/asda21/124/aasf/243234/sdfsdf342/234234/{hName}")
    public ResultDto updateHoney(@PathVariable("hName") String hName){
        int update = honeyService.update(hName);
        return ResultDto.success(update);
    }
    
}
