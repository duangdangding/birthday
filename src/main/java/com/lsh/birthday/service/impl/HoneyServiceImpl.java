package com.lsh.birthday.service.impl;

import com.lsh.birthday.entry.Honey;
import com.lsh.birthday.mapper.HoneyMapper;
import com.lsh.birthday.service.HoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoneyServiceImpl implements HoneyService {

    @Autowired
    private HoneyMapper honeyMapper;
    
    @Override
    public Honey find() {
        return honeyMapper.selectOne(null);
    }

}
