package com.ruoyi.product.utils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * 判断是否是节假日，工具类
 *
 * 返回值    ：   0 上班 1周末 2节假日
 *
 * @Author wzk
 * @Date 2024/2/23 16:05
 */
@Slf4j
public class HolidayUtil {

    static Map<String,List<String>> holiday =new HashMap<>();       //假期
    static Map<String,List<String>> extraWorkDay =new HashMap<>();  //调休日

    //判断是否为节假日

    /**
     *
     * @param time 日期参数 格式‘yyyy-MM-dd’，不传参则默认当前日期
     * @return
     */
    public static String isWorkingDay(String time) throws ParseException {
        Date parse = null;
        //为空则返回当前时间
        if (StringUtils.isNotBlank(time)){
            SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy-MM-dd");
            parse = getYearFormat.parse(time);
        }else {
            parse = new Date();
        }
        String newDate  = new SimpleDateFormat("yyyy").format(parse);

        //判断key是否有参数年份
        if(!holiday.containsKey(newDate)){
            String holiday = getYearHoliday(newDate);
            if ("No!".equals(holiday)){
                return "该年份未分配日期安排！";
            }
        }
        //得到日期是星期几
        Date date = formatStringToDate(time, false);
        // Calendar.getInstance()获取一个Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 设置日期
        calendar.setTime(date);

        // 获取星期
        int weekday = getDayOfWeek(calendar);



        // 是否是节假日
        if(holiday.get(newDate).contains(time)){
            return "2";
        }else if(extraWorkDay.get(newDate).contains(time)){     //是否为调休
            return "0";
        }else if(weekday == Calendar.SATURDAY || weekday == Calendar.FRIDAY){   //是否为周末
            return "1";
        }else{
            return "0";
        }
    }

    /**
     *
     * @param date 日期参数 格式‘yyyy’，不传参则默认当前日期
     * @return
     */
    public static String getYearHoliday(String date) throws ParseException {

        //获取免费api地址
        String httpUrl="https://timor.tech/api/holiday/year/"+date;
        BufferedReader reader = null;
        String result = null;
        // 用于存储返回的年份节假日数据
        StringBuffer sbf = new StringBuffer();

        try {
            // 创建url对象
            URL url = new URL(httpUrl);
            // 使用openConnection方法创建了一个URLConnection对象
            URLConnection connection = url.openConnection();
            // 设置请求头
            connection.setRequestProperty("User-Agent", "Mozilla/4.76");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            /**
             * 使用getInputStream方法获取输入流，这个输入流可以用来读取从URL地址返回的数据，
             * 可能是HTML内容、JSON数据或其他类型的信息。
             * 具体取决于URL地址所指向的资源类型。
             */
            InputStream is = connection.getInputStream();
            // BufferedReader创建字节流对象，InputStreamReader将字节流转换为字符流，编码为utf8
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead); // 将每行内容追加到sbf字符串变量中
                sbf.append("\r\n");  // 手动添加换行符
            }
            reader.close();  // 关闭流

            //字符串转json
            JSONObject json = JSON.parseObject(sbf.toString());
            //根据holiday字段获取jsonObject内容
            JSONObject holiday = json.getJSONObject("holiday");
            if (holiday.size() == 0){
                return "No!";
            }
            List hoList = new ArrayList<>();
            List extraList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : holiday.entrySet()) {
                // 读取字符的值，并转换成字符串
                String value = entry.getValue().toString();
                // 将字符串改为json格式
                JSONObject jsonObject = JSONObject.parseObject(value);
                // 获取是否为假日（是为true，不是为false）
                String hoBool = jsonObject.getString("holiday");
                // 获取时间
                String extra = jsonObject.getString("date");
                //判断不是假期后调休的班
                if(hoBool.equals("true")){
                    hoList.add(extra);
                    // 添加假期
                    HolidayUtil.holiday.put(date,hoList);
                }else {
                    extraList.add(extra);
                    // 添加调休日
                    HolidayUtil.extraWorkDay.put(date,extraList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 功能：获取String日期对应的时间
     * isTotal = false ;yyyy-MM-dd格式限定
     * isTotal = true ;yyyy-MM-dd hh-mm-ss格式限定
     */
    public static Date formatStringToDate(String base, Boolean isTotal) {
        // 是否包含时间部分
        String format = isTotal ? "yyyy-MM-dd HH-mm-ss" : "yyyy-MM-dd";
        // 格式化字符串
        DateFormat df = new SimpleDateFormat(format);
        Date baseTime;
        try {
            // 设定日期
            baseTime = df.parse(base);
        } catch (Exception e) {
            return null;
        }
        return baseTime;
    }


    /**
     * 获得本日星期数,星期一:1,星期日:7 如果传入null则默认为本日
     *
     * @return
     */
    public static int getDayOfWeek(Calendar calendar) {
        int today;
        if (calendar != null) {
            today = calendar.get(Calendar.DAY_OF_WEEK);
        } else {
            today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        }
        if (today == 1)
            return 7;
        else
            return today - 1;
    }
}
