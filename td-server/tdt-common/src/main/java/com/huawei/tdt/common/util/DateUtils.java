package com.huawei.tdt.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DateUtils.java
 * @author lWX537094
 */
public final class DateUtils
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 日期格式化对象
     */
    private static final SimpleDateFormat FORMATTER1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 日期格式化对象
     */
    private static final SimpleDateFormat FORMATTER2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private DateUtils()
    {

    }

    /**
     * 将日期转化成数字
     * 
     * 例如：2018-03-14 18:00:00,698 转化成20180314180000698的长整型
     * @param date 日期对象
     * @return 数字格式的日期
     */
    public static long convertDateToNumDate(Date date)
    {
        String strDate = FORMATTER1.format(date);
        return Long.parseLong(strDate);
    }

    /**
     * 将日期转化成数字字符串
     * 
     * 例如：2018-03-14 18:00:00,698 转化成"20180314180000698"的长整型
     * @param date 日期对象
     * @return 字符串格式的日期
     */
    public static String convertDateToStringDate(Date date)
    {
        return FORMATTER1.format(date);
    }

    /**
     * 将数字日期转化字符串格式
     * 
     * 例如：长整型20180314180000698 转化成2018-03-14 18:00:00
     * @param digitDate 数字格式的日期
     * @return 字符串形式日期
     */
    public static String convertNumDateToStringDate(long digitDate)
    {
        try
        {
            Date date = FORMATTER1.parse(String.valueOf(digitDate));
            return FORMATTER2.format(date);
        }
        catch (ParseException e)
        {
            LOGGER.error("Convert digit date to string failed.", e);
            return null;
        }
    }
}
