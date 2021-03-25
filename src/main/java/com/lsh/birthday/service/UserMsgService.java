package com.lsh.birthday.service;

import com.lsh.birthday.entry.UserMsg;

public interface UserMsgService {

    int updateSum(UserMsg userMsg);

    Integer addUser(UserMsg userMsg);

    UserMsg findBynameip(UserMsg userMsg);
}
