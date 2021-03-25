package com.lsh.birthday.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.birthday.entry.Honey;

public interface HoneyMapper extends BaseMapper<Honey> {
    int update(Honey honey);
}
