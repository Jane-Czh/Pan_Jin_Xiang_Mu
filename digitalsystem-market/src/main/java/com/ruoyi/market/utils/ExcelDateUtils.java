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
}

