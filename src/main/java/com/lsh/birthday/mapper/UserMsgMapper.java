package com.lsh.birthday.mapper;

import com.lsh.birthday.entry.UserMsg;

public interface UserMsgMapper {
    
    int updateSum(UserMsg userMsg);
    
    Integer addUser(UserMsg userMsg);
    
    UserMsg findBynameip(UserMsg userMsg);
}
