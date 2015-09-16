package com.common.base;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前时间的long格式
     * 
     * @return
     */
    public static Long getCurrentTime() {
        Long ctime = new Date().getTime();
        return ctime;
    }
    /**
     * 根据 long类型获取时间类型
     * @param time long类型数字
     * @return
     */
    public static Date getDateByLongTime(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        Date date = cal.getTime();
        return date;
    }
}
