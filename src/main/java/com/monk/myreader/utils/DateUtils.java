package com.monk.myreader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2020/3/28 16:56
 */
public class DateUtils{
    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static Date getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        return calendar.getTime();
    }

    private static String DEFAULT_PATTERN="yyyy-MM-dd";
    /**
     * 获取当前时间
     * @param pattern 时间格式
     */
    public static String now(String pattern){
        if(pattern == null || pattern.equals("")){
            pattern = DEFAULT_PATTERN;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now().format(formatter);
    }

    public static String now(){
        return now("");
    }

    public static Date string2date(String date){

        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            return formatter.parse(date);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String date2string(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);

        return formatter.format(date);
    }
}
