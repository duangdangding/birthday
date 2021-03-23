package com.lsh.birthday.service;

import com.lsh.birthday.entry.Honey;

import java.util.List;

public interface HoneyService {
    Honey find();
    
    int update(String hName);
}
