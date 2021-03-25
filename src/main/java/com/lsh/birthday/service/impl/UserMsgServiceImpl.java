package com.lsh.birthday.service.impl;

import com.lsh.birthday.entry.UserMsg;
import com.lsh.birthday.mapper.UserMsgMapper;
import com.lsh.birthday.service.UserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMsgServiceImpl implements UserMsgService {
    
    @Autowired
    private UserMsgMapper userMsgMapper;
    
    @Override
    public int updateSum(UserMsg userMsg) {
        int i = userMsgMapper.updateSum(userMsg);
        return i;
    }

    @Override
    public Integer addUser(UserMsg userMsg) {
        Integer i = userMsgMapper.addUser(userMsg);
        return i;
    }

    @Override
    public UserMsg findBynameip(UserMsg userMsg) {
        UserMsg msg = userMsgMapper.findBynameip(userMsg);
        return msg;
    }
}
