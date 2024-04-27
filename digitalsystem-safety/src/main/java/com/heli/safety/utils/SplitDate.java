package com.heli.safety.utils;

import java.util.Calendar;
import java.util.Date;

public class SplitDate {
    public static int[] splitDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 月份从 0 开始，所以要加 1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new int[] {year, month, day};
    }
}
