package com.lsh.birthday.service;

import com.lsh.birthday.entry.Common;

public interface CommonService {

    int updateById(Common common);

    int updateByKey(Common common);
    
    Common getByEnabled(Common common);
}
