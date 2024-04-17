package com.ruoyi.market.utils;

import java.time.LocalDateTime;
import java.util.Calendar;
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

    //判断两个Date是否为同一天
    public static boolean isSameDay(Date date1, Date date2) {
        // 创建两个 Calendar 实例
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        // 将 Date 对象设置到 Calendar 实例中
        cal1.setTime(date1);
        cal2.setTime(date2);
        // 比较年、月、日是否相同
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    //判断Date是否是当月
    public static boolean isCurrentMonth(Date date) {
        // 获取当前日期
        Date currentDate = new Date();

        // 创建 Calendar 对象，并设置为当前日期
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        // 获取当前日期的年份和月份
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1; // 月份从 0 开始，所以需要加 1

        // 创建一个 Calendar 对象，用于比较的日期
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);

        // 获取要判断日期的年份和月份
        int targetYear = targetCalendar.get(Calendar.YEAR);
        int targetMonth = targetCalendar.get(Calendar.MONTH) + 1;

        // 判断是否为当月
        return currentYear == targetYear && currentMonth == targetMonth;
    }

    //判断Date是否为本周
    public static boolean isCurrentWeek(Date date) {
        // 获取当前日期
        Date currentDate = new Date();

        // 创建 Calendar 对象，并设置为当前日期
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        // 获取当前日期的年份和周数
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentWeek = currentCalendar.get(Calendar.WEEK_OF_YEAR);

        // 创建一个 Calendar 对象，用于比较的日期
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);

        // 获取要判断日期的年份和周数
        int targetYear = targetCalendar.get(Calendar.YEAR);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);

        // 判断是否为本周
        return currentYear == targetYear && currentWeek == targetWeek;
    }
}
