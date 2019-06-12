package com.tianye.hello.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String SIMPLE_DATE_PATTERN = "yyyyMMdd";

    /**
     * 时间格式
     */
    public static final String ACCURATE_MINUTES_PATTERN = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULY_FULL_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 带星期信息的时间格式
     */
    public static final String DATE_FORMAT_WEEKDAY = "EE yyyy-MM-dd HH:mm:ss";

    public static final int SEXAGESIMAL = 60;
    public static final int DAYHOURS = 24;
    public static final int SECONDMILLISECOND = 1000;

    public static final String TIMEZONE_UTC = "UTC";
    /**
     * 
     * 功能描述: 获取当前日期 yyyy-MM-dd<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getNowShortStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * 根据格式转换字符串为日期对象
     * 
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return 日期对象
     */
    public static Date getDateByStr(String dateStr, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("parse find wrong.", e);
        }
        return new Date();
    }

    /**
     * 
     * 功能描述: 获取当前日期 yyyy-MM-dd<br>
     * 〈功能详细描述〉
     * 
     * @param delay 正数date时间后移，负数则前移
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getNextShortStr(int delay) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(getNextDay(new Date(), delay));
    }

    /**
     * 修改时间的格式
     *
     * @param pattern 转换后的格式
     * @param date 转换前的时间对象 如果为null，则默认当前时间
     * @return 返回新时间格式的时间对象
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getNowDateShort(String pattern, Date date) {
        if (CommonUtils.isEmpty(pattern)) {
            pattern = DEFAULT_DATE_PATTERN;
        }

        // 如果为null，则默认当前时间
        if (null == date) {
            date = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            LOGGER.error("pattern transfer find wrong.", e);
        }
        return date;
    }

    /**
     * 修改相对当前时间的日期格式
     * 
     * @param pattern 转换后的格式
     * @param delay 相对当前时间的天数间隔，负数代表过去日期间隔
     * @return 返回相对当前时间 （转换后的时间格式）
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getNowDateShort(String pattern, int delay) {
        return getNowDateShort(pattern, getNextDay(new Date(), delay));
    }

    /**
     * 查询两个日期的绝对相隔天数 如果前面比后面日期早,则返回负数
     * 
     * @param date1 非NUll 不含时分秒的时间
     * @param date2 非NUll 不含时分秒的时间
     * @return 两个日期的绝对相隔天数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int intervalOfDate(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (DAYHOURS * SEXAGESIMAL * SEXAGESIMAL * SECONDMILLISECOND));
    }

    /**
     * 查询两个日期的绝对相隔天数 如果前面比后面日期早,则返回负数
     *
     * @param date1 非NUll 不含时分秒的时间
     * @param date2 非NUll 不含时分秒的时间
     * @return 两个日期的绝对相隔天数
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int intervalOfSeconds(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / (SECONDMILLISECOND));
    }

    /**
     * 
     * 功能描述: 返回当前时间 时间格式 yyyy-MM-dd HH:mm:ss<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getNowLongStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * 
     * 功能描述: 精确到分钟<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getNowAccurateMinutesStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(ACCURATE_MINUTES_PATTERN);
        return sdf.format(new Date());
    }

    /**
     * 
     * 功能描述: 返回当前时间 时间格式 yyyy-MM-dd HH:mm:ss.SSS<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getNowFullStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULY_FULL_TIME_PATTERN);
        return sdf.format(new Date());
    }


    /**
     * 
     * 功能描述: 转换Date->String 时间格式 yyyy-MM-dd HH:mm:ss<br>
     * 〈功能详细描述〉
     *
     * @param date
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getLongStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        return sdf.format(date);
    }

    /**
     * 根据格式，转换时间对象成固定格式的字符串
     * 
     * @param date 默认的时间对象，如果为null，则默认当前时间
     * @param format 时间格式，如果为空或者不合法，默认格式为 yyyy-MM-dd HH:mm:ss
     * @return 据格式，转换时间对象成固定格式的字符串
     */
    public static String getDateStrByFormat(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_TIME_PATTERN;
        }
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(format);
        } catch (IllegalArgumentException e) {
            LOGGER.error("the given pattern is invalid.", e);
            sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
        }
        if (null == date) {
            date = new Date();
        }
        return sdf.format(date);
    }

    /**
     *
     * 功能描述: 获得某一个时间+偏移量（单位天）的时间<br>
     * 〈功能详细描述〉
     *
     * @param date
     * @param delay 正数date时间后移，负数则前移
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getNextDay(Date date, int delay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, delay);
        return c.getTime();
    }

    /**
     *
     * 功能描述: 获得某一个时间+偏移量（单位天）的时间<br>
     * 〈功能详细描述〉
     *
     * @param date
     * @param delay 正数date时间后移，负数则前移
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getDeviationTimeOfHour(Date date, int delay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, delay);
        return c.getTime();
    }

    /**
     *
     * 功能描述: 获得某一个时间+偏移量（单位天）的时间<br>
     * 〈功能详细描述〉
     *
     * @param date
     * @param delay 正数date时间后移，负数则前移
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getDeviationTimeOfMinute(Date date, int delay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, delay);
        return c.getTime();
    }

    /**
     * 
     * 功能描述: 获得当天的零点时间<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getNowZeroPoint() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 将日期yyyyMMdd，转换成为对应Integer类型
     * @param date
     * @return
     */
    public static Integer date2Integer(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year*10000 + month*100 + day;
    }

    public static Date getZeroPoint(Date date) {
        Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * 将长整型的日期字符串转换成日期对象
     * 
     * @param timeMillisStr 长整型的日期字符串 如果非长整型，返回当前时间
     * @return 转换后日期对象
     */
    public static Date getDateFromMillisStr(String timeMillisStr) {
        try {
            Long valueOf = Long.valueOf(timeMillisStr);
            return new Date(valueOf);
        } catch (NumberFormatException e) {
            LOGGER.error("getDateFromMillisStr find wrong.", e);
            return new Date();
        }
    }

    /**
     * 将长整型的日期字符串转换成日期对象
     *
     * @param timeSecenesStr 长整型的日期字符串 如果非长整型，返回当前时间
     * @return 转换后日期对象
     */
    public static Date getDateFromSecenesStr(String timeSecenesStr) {
        try {
            Long valueOf = Long.valueOf(timeSecenesStr) * 1000;
            return new Date(valueOf);
        } catch (NumberFormatException e) {
            LOGGER.error("getDateFromMillisStr find wrong.", e);
            return new Date();
        }
    }

    /**
     * 当前剩余秒数 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int remainingSeconds() {
        Date now = new Date();
        long seconds = getZeroPoint(getNextDay(now, 1)).getTime() - now.getTime();
        int remainingSeconds = (int) (seconds / SECONDMILLISECOND);
        if (remainingSeconds > 0) {
            return remainingSeconds;
        }
        return 0;
    }

    /**
     * 
     * 功能描述: 校验日期格式<br>
     * 
     * @param someTime
     * @return
     */
    public static boolean isValidDate(String someTime, String format) {
        if (CommonUtils.isEmpty(someTime)) {
            throw new IllegalArgumentException("校验日期不能为空");
        }
        if (CommonUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date date = dateFormat.parse(someTime);
            return StringUtils.equals(someTime, dateFormat.format(date));
        } catch (Exception e) {
            LOGGER.error("格式不符合:", e);
            return false;
        }
    }

    /**
     * 
     * 功能描述: 格式化date<br>
     * 
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDate(Date date, String format) {
        if (null == date) {
            return "";
        }
        if (StringUtils.isEmpty(format)) {
            format = ACCURATE_MINUTES_PATTERN;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 
     * 功能描述: h获得传入日期往前或者往后滚的那一天的23:59:59<br>
     * 
     * @param date
     * @param rollDate
     * @return
     */
    public static Date rollDate(Date date, int rollDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, rollDate);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date2 = cal.get(Calendar.DATE);
        cal.set(year, month, date2, 23, 59, 59);

        return cal.getTime();
    }

    /**
     * 
     * 功能描述: 获取UTC时间
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Date getCurrentUTCTime() {
        Calendar calendar = Calendar.getInstance();
        int offset = calendar.get(Calendar.ZONE_OFFSET); 
        calendar.add(Calendar.MILLISECOND, -offset);
        return calendar.getTime();
    }
    
    /**
     * 
     * 功能描述: 获取java.sql.Timestamp類型的UTC时间
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Timestamp getDBCurrentUTCTime() {
        return new Timestamp(getCurrentUTCTime().getTime());
    }

    /**
     * 
     * 功能描述: 数据库中的utc时间转换为指定格式本地时间
     *
     * @param utc
     * @param format
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String utcToLocal(Date utc, String format) {
        if (null == utc) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String utcStr = sdf.format(utc);
        return utcToLocal(utcStr, format, format);
    }

    /**
     * 
     * 功能描述: 将指定格式的UTC时间转为指定格式的本地时间字符串
     *
     * @param utcStr
     * @param utcFormat
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String utcToLocal(String utcStr, String utcFormat, String localFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(utcFormat);
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));
        Date utc = null;
        try {
            utc = sdf.parse(utcStr);
        } catch (ParseException e) {
            LOGGER.error("parse find wrong.", e);
        }
        sdf = new SimpleDateFormat(localFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(utc);
    }
    
    /*public static String localToUTC(String localStr, String localFormat, String utcFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(localFormat);
        Date local = null;
        try {
            local = sdf.parse(localStr);
        } catch (ParseException e) {
            LOGGER.error("parse find wrong.", e);
        }
        sdf = new SimpleDateFormat(utcFormat);
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));
        return sdf.format(local);
    }*/

    public static Date localToUtc(String localStr, String localFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(localFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        Date local = null;
        try {
            local = sdf.parse(localStr);
            sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));
            String utc = sdf.format(local);
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.parse(utc);
        } catch (ParseException e) {
            LOGGER.error("parse find wrong.", e);
        }
        return null;
    }

    /**
     *
     * 功能描述: 返回当前时间 字符串格式 yyyy-MM-ddHHmmss<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getNowStampStr() {

        Calendar c = Calendar.getInstance();
        String year =String.valueOf(c.get(Calendar.YEAR));
        String month = c.get(Calendar.MONTH) + 1<10? "0"+(c.get(Calendar.MONTH) + 1) : String.valueOf(c.get(Calendar.MONTH) + 1);
        String day = c.get(Calendar.DAY_OF_MONTH)<10? "0"+c.get(Calendar.DAY_OF_MONTH) : String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String hour = c.get(Calendar.HOUR_OF_DAY)<10? "0"+c.get(Calendar.HOUR_OF_DAY) : String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minute = c.get(Calendar.MINUTE)<10? "0"+c.get(Calendar.MINUTE) : String.valueOf(c.get(Calendar.MINUTE));
        String second = c.get(Calendar.SECOND)<10? "0"+c.get(Calendar.SECOND) : String.valueOf(c.get(Calendar.SECOND));

        StringBuilder builder = new StringBuilder();
        return builder.append(year).append(month).append(day).append(hour).append(minute).append(second).toString();
    }

    public static Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
       /* int offset = calendar.get(Calendar.ZONE_OFFSET);
        calendar.add(Calendar.MILLISECOND, -offset);*/
        return calendar.getTime();
    }
}
