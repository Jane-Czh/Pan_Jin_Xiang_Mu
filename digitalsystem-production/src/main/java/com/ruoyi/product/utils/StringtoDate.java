package com.ruoyi.product.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringtoDate {
    public static void main(String[] args) throws ParseException {
        String normalstartstr = "2024-05-21T11:40:00";  // 假设这是你的字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date startDate = dateFormat.parse(normalstartstr);

        System.out.println(startDate);  // 打印转换后的日期对象
    }
}
