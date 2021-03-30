package com.lsh.birthday.service;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    /**
     * 计算今天还剩多少秒
     * @return
     */
    public static long subTodayDate() {
        Date end = DateTimeCalculatorUtil.endTimeOfToday();
        Date now = new Date();
        long time = DateTimeCalculatorUtil.betweenTotalSeconds(now, end);
        return time;
    }

}
