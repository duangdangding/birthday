package com.lsh.birthday.service.impl;

import com.lsh.birthday.entry.Common;
import com.lsh.birthday.mapper.CommonMapper;
import com.lsh.birthday.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    
    @Autowired
    private CommonMapper commonMapper;
    
    @Override
    public int updateById(Common common) {
        int i = commonMapper.updateById(common);
        return i;
    }

    @Override
    public int updateByKey(Common common) {
        int i = commonMapper.updateByKey(common);
        return i;
    }

    @Override
    public Common getByEnabled(Common common) {
        Common byEnabled = commonMapper.getByEnabled(common);
        return byEnabled;
    }
}
