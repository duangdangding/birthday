package com.lsh.birthday.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.birthday.entry.Comment;
import com.lsh.birthday.entry.Common;

import java.util.List;

public interface CommonMapper extends BaseMapper<Common> {
    int updateById(Common common);
    
    int updateByKey(Common common);

    Common getByEnabled(Common common);
}
