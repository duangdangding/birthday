package com.lsh.birthday.controller;

import com.lsh.birthday.entry.Common;
import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.entry.ResultDtoManager;
import com.lsh.birthday.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonCtr {

    @Autowired
    private CommonService commonService;

    /**
     * 根据key批量设置是否可用
     * @param common
     * @return
     */
    @RequestMapping("/3/a/h/c/o/m/m/o/n/1")
    public ResultDto<String> update1(Common common) {
        int i = commonService.updateByKey(common);
        if (i > 0) {
            return ResultDtoManager.success("设置成功~");
        } else {
            return ResultDtoManager.success("设置失败~");
        }
    }

    /**
     * 根据id设置是否可用 
     * 先全部设置 0 不可以用 再根据id精确设置可用
     * @param common
     * @return
     */
    @RequestMapping("/3/a/h/c/o/m/m/o/n/2")
    public ResultDto<String> update2(Common common) {
        int i = commonService.updateById(common);
        if (i > 0) {
            return ResultDtoManager.success("设置成功~");
        } else {
            return ResultDtoManager.success("设置失败~");
        }
    }
}
