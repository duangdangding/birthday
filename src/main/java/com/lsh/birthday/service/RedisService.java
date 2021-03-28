package com.lsh.birthday.service;

import java.util.Set;

public interface RedisService {
    
    void addBannadWords(String words);
    
    Set<Object> getAllbanad();
    
    Set<Object> getAllRandName();

    String getRandomName();

    long setRandomName(String username);

    int findBannadWords(String words);
}
