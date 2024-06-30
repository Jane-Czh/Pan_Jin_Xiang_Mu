package com.ruoyi.market.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExcelDateUtils {

    // Excel日期基准
    private static final int EXCEL_DATE_BASE_YEAR = 1900;
    private static final int EXCEL_DATE_BASE_MONTH = 1;
    private static final int EXCEL_DATE_BASE_DAY = 1;

    // 将Excel日期数值转换为字符串表示的日期
    public static String convertExcelDateToString(double excelDate) {
        Date javaDate = convertExcelDateToJavaDate(excelDate);
        return formatDate(javaDate);
    }
    // 将Excel日期数值转换为字符串表示的日期
    public static String convertExcelDateTimeToString(double excelDate) {
        Date javaDate = convertExcelDateTimeToJavaDate(excelDate);
        return formatDateTime(javaDate);
    }

    // 将Excel日期数值转换为Java日期对象
    private static Date convertExcelDateToJavaDate(double excelDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(EXCEL_DATE_BASE_YEAR, EXCEL_DATE_BASE_MONTH - 1, EXCEL_DATE_BASE_DAY);
        calendar.add(Calendar.DATE, (int) excelDate - 2); // 减去2天修正 Excel 日期计算错误
        return calendar.getTime();
    }

    // 格式化日期为字符串
    private static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }
    // 将Excel日期数值转换为Java日期对象
    private static Date convertExcelDateTimeToJavaDate(double excelDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(EXCEL_DATE_BASE_YEAR, EXCEL_DATE_BASE_MONTH - 1, EXCEL_DATE_BASE_DAY);
        calendar.add(Calendar.DATE, (int) excelDate - 2); // 注意减去1天

        // 处理时间部分
        double fraction = excelDate - (int) excelDate;
        int hours = (int) (fraction * 24);
        int minutes = (int) ((fraction * 24 - hours) * 60);
        int seconds = (int) (((fraction * 24 - hours) * 60 - minutes) * 60);

        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);

        return calendar.getTime();
    }
    // 格式化日期为字符串
    private static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

}

