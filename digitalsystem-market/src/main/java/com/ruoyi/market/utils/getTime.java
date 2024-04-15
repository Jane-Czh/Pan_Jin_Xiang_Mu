package com.ruoyi.market.utils;

import java.time.LocalDateTime;
import java.util.Date;

public class getTime {
    public static int getYear(LocalDateTime dateTime) {
        return dateTime.getYear();
    }

    public static int getMonth(LocalDateTime dateTime) {
        return dateTime.getMonthValue();
    }

    public static int getDay(LocalDateTime dateTime) {
        return dateTime.getDayOfMonth();
    }

    //获取当前系统时间并将其转换为Date数据类型
    public static Date getCurrentDate() {
        return new Date();
    }
}
