
package org.cs.dolphin.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    /**
     * 年月日 时分秒
     */
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 年。
     */
    public static final int YEAR = 0;

    /**
     * 月。
     */
    public static final int MONTH = 1;

    /**
     * 周。
     */
    public static final int WEEK = 2;

    /**
     * 天。
     */
    public static final int DAY = 3;

    /**
     * 小时。
     */
    public static final int HOUR = 4;

    /**
     * 分钟。
     */
    public static final int MINUTE = 5;

    /**
     * 秒。
     */
    public static final int SECOND = 6;

    /**
     * 毫秒。
     */
    public static final int MILLISECOND = 7;

    /**
     * minute of day。
     */
    public static final int MINUTEOFDAY = 8;

    /**
     * 一天的毫秒数
     */
    public static final long DAY_LONG = 1 * 24 * 60 * 60 * 1000;

    /**
     * 10
     */
    public static final int TEN = 10;

    /**
     * 7
     */
    public static final int SEVEN = 7;

    /**
     * 8
     */
    public static final int EIGHT = 8;

    /**
     * 60
     */
    public static final int SIXTY = 60;

    /**
     * 1000
     */
    public static final int ONETHOUSAND = 1000;

    /**
     * 2
     */
    public static final int TWO = 2;

    /**
     * 按要求转化时间的显示格式 参数：oldpattern，旧日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK
     * simpleDateFormat类 newpattern，新日期格式
     *
     * @param time       时间
     * @param oldpattern 旧的正则表达式
     * @param newpattern 新的正则表达式
     * @return String
     */
    public static String timeTransform(String time, String oldpattern,
                                       String newpattern) {
        // 打印调试信息
        String oldtime;
        if (oldpattern == null) {
            throw new IllegalArgumentException("the old pattern is null");
        }
        if (newpattern == null) {
            throw new IllegalArgumentException("the new pattern is null");
        }
        SimpleDateFormat olddatepattern = new SimpleDateFormat(oldpattern);
        Date now;
        try {
            now = olddatepattern.parse(time);
            // 用原来的pattern解析日期，再和原来的比较，由此来检查时间是否合法
            oldtime = olddatepattern.format(now);
            if (!oldtime.equals(time)) {
                throw new IllegalArgumentException("using Illegal time");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("using [" + oldpattern
                    + "] parse [" + time + "] failed");
        }
        SimpleDateFormat newdatepattern = new SimpleDateFormat(newpattern);

        return newdatepattern.format(now);
    }

    /**
     * 获取指定格式的当前日期 参数：pattern，日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK
     * simpleDateFormat类
     *
     * @param pattern 正则表达式
     * @return String
     */
    public static String getCurrentDate(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("input string parameter is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date now = new Date();
        return sdf.format(now);
    }

    /**
     * 将日期长整型转换成字符串 参数：time，long，从格林威治时间：1970年1月1日0点起的毫秒数 pattern, String,
     * 转换的目标格式
     *
     * @param time    时间
     * @param pattern 正则表达式
     * @return String
     */
    public static String long2TimeStr(long time, String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException(
                    "pattern parameter can not be null");
        }
        Date dt = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(dt);
    }

    /**
     * 将日期型转换成字符串 参数：time，Date pattern, String, 转换的目标格式
     *
     * @param time    时间
     * @param pattern 正则表达式
     * @return String
     */
    public static String date2TimeStr(Date time, String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException(
                    "pattern parameter can not be null");
        }
        if (time == null) {
            throw new IllegalArgumentException("time parameter can not be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

    /**
     * 将日期增加一个增量，目前只能是，年，月，周，日，时、分、秒、毫秒 参数：date, long，原始时间 delta，int，增量的大小 unit,
     * int, 增量的单位，YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLISECOND
     * 返回：long，从格林威治时间：1970年1月1日0点起的毫秒数
     *
     * @param date  时间
     * @param delta delta
     * @param unit  unit
     * @return long
     */
    public static long addDate(long date, int delta, int unit) {
        if ((unit < YEAR) || (unit > MILLISECOND)) {
            throw new IllegalArgumentException(
                    "time unit must in [YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND], others not support");
        }
        Date dt = new Date(date);
        Calendar calendar = getLocalCalendar(dt);
        // 增加增量
        switch (unit) {
            case YEAR:
                calendar.add(Calendar.YEAR, delta);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, delta);
                break;
            case WEEK:
                calendar.add(Calendar.DAY_OF_WEEK, delta);
                break;
            case DAY:
                calendar.add(Calendar.DAY_OF_MONTH, delta);
                break;
            case HOUR:
                calendar.add(Calendar.HOUR, delta);
                break;
            case MINUTE:
                calendar.add(Calendar.MINUTE, delta);
                break;
            case SECOND:
                calendar.add(Calendar.SECOND, delta);
                break;
            case MILLISECOND:
                calendar.add(Calendar.MILLISECOND, delta);
        }
        // 获取新的时间，并转换成长整形
        Date ndt = calendar.getTime();
        return ndt.getTime();
    }

    /**
     * 将日期增加一个增量，目前只能是，年，月，周，日，时，分，秒，毫秒 参数：date, long，原始时间 delta，int，增量的大小 unit,
     * int, 增量的单位，YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND
     * pattern, String, 转换的目标格式 返回：String，指定格式的日期字符串
     *
     * @param date    时间
     * @param delta   delta
     * @param unit    unit
     * @param pattern 政则表达式
     * @return String
     */
    public static String addDate(long date, int delta, int unit, String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException(
                    "pattern parameter can not be null");
        }
        return long2TimeStr(addDate(date, delta, unit), pattern);
    }

    /**
     *
     */

    /**
     * 将字符串转换成日期长整形 参数：time，String，日期字符串 pattern, String, 解析的格式 返回：long，日期长整形
     *
     * @param time    时间
     * @param pattern 政则表达式
     * @return long
     */
    public static long timeStr2Long(String time, String pattern) {
        return timeStr2Date(time, pattern).getTime();
    }

    /**
     * 将字符串转换成日期形 参数：time，String，日期字符串 pattern, String, 解析的格式 返回：Date，日期形
     *
     * @param time    时间
     * @param pattern 政则表达式
     * @return Date
     */
    public static Date timeStr2Date(String time, String pattern) {
        if (time == null) {
            throw new IllegalArgumentException("time parameter can not be null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException(
                    "pattern parameter can not be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            throw new IllegalArgumentException("using [" + pattern
                    + "] parse [" + time + "] failed");
        }
    }

    /**
     * 取得short date的string类型
     *
     * @param date 时间
     * @return String
     */
    public static String getShotDateString(String date) {
        date = date.trim();
        if (date.length() > TEN) {
            date = date.substring(0, TEN);
        }
        String str[] = date.split("[-]");
        if (str[1].length() == 1) {
            str[1] = "0".concat(str[1]);
        }
        if (str[TWO].length() == 1) {
            str[TWO] = "0".concat(str[TWO]);
        }
        date = str[0] + "-" + str[1] + "-" + str[TWO];

        return date;
    }

    /**
     * 获取日期字符串的某一部分 参数：date，有效的日期字符串 pattern，日期格式字符串
     * part，时间部分的指示符，只能是：YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND，MILLISECOND
     *
     * @param date    时间
     * @param pattern 正则表达式
     * @param part    part
     * @return int
     */
    public static int getDatePart(String date, String pattern, int part) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("pattern parameter is null");
        }
        if ((part < YEAR) || (part > MINUTEOFDAY)) {
            throw new IllegalArgumentException(
                    "the part parameter must be in [YEAR,MONTH, DAY, HOUR, MINUTE, SECOND]");
        }
        Date dt = timeStr2Date(date, pattern);
        return getDatePart(dt, part);
    }

    /**
     * 获取日期的某一部分 参数：date，有效的日期类型
     * part，时间部分的指示符，只能是：YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND，MILLISECOND
     *
     * @param date 时间
     * @param part part
     * @return int
     */
    public static int getDatePart(Date date, int part) {
        if (date == null) {
            throw new IllegalArgumentException("date parameter is null");
        }
        if ((part < YEAR) || (part > MINUTEOFDAY)) {
            throw new IllegalArgumentException(
                    "the part parameter must be in [YEAR,MONTH, DAY, HOUR, MINUTE, SECOND]");
        }
        Calendar calendar = getLocalCalendar(date);
        int result = 0;
        switch (part) {
            case YEAR:
                result = calendar.get(Calendar.YEAR);
                break;
            case MONTH:
                result = calendar.get(Calendar.MONTH);
                break;
            case WEEK:
                result = calendar.get(Calendar.DAY_OF_WEEK);
                break;
            case DAY:
                result = calendar.get(Calendar.DAY_OF_MONTH);
                break;
            case HOUR:
                result = calendar.get(Calendar.HOUR_OF_DAY);
                break;
            case MINUTE:
                result = calendar.get(Calendar.MINUTE);
                break;
            case SECOND:
                result = calendar.get(Calendar.SECOND);
                break;
            case MILLISECOND:
                result = calendar.get(Calendar.MILLISECOND);
                break;
            case MINUTEOFDAY:
                result = calendar.get(Calendar.HOUR_OF_DAY) * SIXTY
                        + calendar.get(Calendar.MINUTE);
        }
        return result;
    }

    /**
     * 获取下一个周期的开始时间 参数：date，String类型，有效的时间 pattern，String类型，时间格式字符串
     * part，int类型，周期类型，可以是年、月、日、周
     *
     * @param galeday 时间
     * @param pattern 正则表达式
     * @param part    part
     * @return String
     */
    public static String getNextPeriodTime(Date galeday, String pattern,
                                           int part) {

        if (galeday == null) {
            throw new IllegalArgumentException("date parameter is null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("pattern parameter is null");
        }
        if ((part < YEAR) || (part > DAY)) {
            throw new IllegalArgumentException(
                    "the part parameter must be in [YEAR,MONTH, WEEK, DAY]");
        }
        String result;
        Calendar caldeduct = getLocalCalendar(galeday);
        Calendar calnow = getLocalCalendar(new Date());
        switch (part) {
            case DAY: // 扣费周期为每天
                return recursiveGet(caldeduct, calnow, pattern,
                        Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.HOUR);
            case WEEK: // 周期为每周
                return recursiveGetWeek(caldeduct, calnow, pattern,
                        Calendar.DAY_OF_WEEK, Calendar.DAY_OF_MONTH, 0,
                        Calendar.DAY_OF_WEEK);
            case YEAR: // 周期为每年
                return recursiveGet(caldeduct, calnow, pattern, Calendar.YEAR,
                        Calendar.MONTH, Calendar.MONTH);
            case MONTH: // 周期为每月
                return recursiveGet(caldeduct, calnow, pattern, Calendar.MONTH,
                        Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH);
            default:
                result = "unsupport period : " + String.valueOf(part);
        }
        return result;
    }

    private static String recursiveGetWeek(Calendar caldeduct, Calendar calnow,
                                           String pattern, int largepart, int part, int gap, int step) {
        int deduct = caldeduct.get(step);
        int now = calnow.get(step);
        if (step == Calendar.DAY_OF_WEEK) {
            gap = deduct - now;
        }
        if (deduct > now) {
            calnow.add(step, gap);
            return DateUtil.date2TimeStr(calnow.getTime(), pattern);
        } else if (deduct < now) {
            calnow.add(step, SEVEN + gap);
            return DateUtil.date2TimeStr(calnow.getTime(), pattern);
        } else {
            switch (step) {
                case Calendar.DAY_OF_WEEK:
                    step = Calendar.HOUR;
                    break;
                case Calendar.HOUR:
                    step = Calendar.MINUTE;
                    break;
                case Calendar.MINUTE:
                    step = Calendar.SECOND;
                    break;
                case Calendar.SECOND:
                    step = Calendar.MILLISECOND;
                    break;
                case Calendar.MILLISECOND:
                    return date2TimeStr(calnow.getTime(), pattern);
            }
            return recursiveGetWeek(caldeduct, calnow, pattern, largepart,
                    part, gap, step);
        }
    }

    private static String recursiveGet(Calendar caldeduct, Calendar calnow,
                                       String pattern, int largepart, int part, int step) {

        int deduct = caldeduct.get(step);
        int now = calnow.get(step);
        if (deduct > now) {
            calnow.set(part, caldeduct.get(part));
            if (largepart == Calendar.YEAR) {
                calnow.set(Calendar.DAY_OF_MONTH, caldeduct
                        .get(Calendar.DAY_OF_MONTH));
            }
            return DateUtil.date2TimeStr(calnow.getTime(), pattern);
        } else if (deduct < now) {
            calnow.add(largepart, 1);
            calnow.set(part, caldeduct.get(part));
            if (largepart == Calendar.YEAR) {
                calnow.set(Calendar.DAY_OF_MONTH, caldeduct
                        .get(Calendar.DAY_OF_MONTH));
            }
            return DateUtil.date2TimeStr(calnow.getTime(), pattern);
        } else {
            switch (step) {
                case Calendar.YEAR:
                    step = Calendar.MONTH;
                    break;
                case Calendar.MONTH:
                    step = Calendar.DATE;
                    break;
                case Calendar.DAY_OF_MONTH:
                    step = Calendar.HOUR;
                    break;
                case Calendar.HOUR:
                    step = Calendar.MINUTE;
                    break;
                case Calendar.MINUTE:
                    step = Calendar.SECOND;
                    break;
                case Calendar.SECOND:
                    step = Calendar.MILLISECOND;
                    break;
                case Calendar.MILLISECOND:
                    return date2TimeStr(calnow.getTime(), pattern);
            }
            return recursiveGet(caldeduct, calnow, pattern, largepart, part,
                    step);
        }
    }

    /**
     * 获得东八时区的日历，并设置日历的当前日期 参数：date，Date，日期型
     *
     * @param date Date
     * @return Calendar
     */
    public static Calendar getLocalCalendar(Date date) {
        // 设置为GMT+08:00时区
        String[] ids = TimeZone.getAvailableIDs(EIGHT * SIXTY * SIXTY
                * ONETHOUSAND);
        if (ids.length == 0) {
            throw new IllegalArgumentException(
                    "get id of GMT+08:00 time zone failed");
        }
        // SimpleTimeZone stz = new SimpleTimeZone(8 * 60 * 60 * 1000, ids[0]);
        // 创建Calendar对象，并设置为指定时间
        // Calendar calendar = new GregorianCalendar(stz);
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        // 设置成宽容方式
        if (!calendar.isLenient()) {
            calendar.setLenient(true);
        }
        // 设置SUNDAY为每周的第一天
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        // 设置日历的当前时间
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 根据日期格式获得对应的日期字符串
     *
     * @param time    日期格式字符串 该参数必须满足"yyyyMMddHHmmss"格式
     * @param pattern 要获得对应的日期字符串格式
     * @return 返回日期字符串
     */
    public static String getDateByPattern(String time, String pattern) {
        if (time == null) {
            throw new IllegalArgumentException("time parameter can not be null");
        }

        if (pattern == null) {
            throw new IllegalArgumentException(
                    "pattern parameter can not be null");
        }

        Date date = timeStr2Date(time, "yyyyMMddHHmmss");
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String resultTime = sdf.format(date);

        return resultTime;
    }

    /**
     * 根据日期增加或减少一天
     *
     * @param time    日期字符串
     * @param pattern 解析的格式和返回的格式一致
     * @param falg    true 增加一天; false 减少一天
     * @return String 返回日期字符串
     */
    public static String addOrDescOneDay(String time, String pattern,
                                         boolean falg) {
        if (time == null) {
            throw new IllegalArgumentException("time parameter can not be null");
        }

        if (pattern == null) {
            throw new IllegalArgumentException(
                    "pattern parameter can not be null");
        }
        long newDate;
        if (falg) {
            newDate = timeStr2Long(time, pattern) + DAY_LONG;
        } else {
            newDate = timeStr2Long(time, pattern) - DAY_LONG;
        }

        Date date = new Date(newDate);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String resultTime = sdf.format(date);

        return resultTime;
    }

    /**
     * 将指定的毫秒数按指定格式转换为日期的字符串表示
     *
     * @param lMilliSec 指定的毫秒数
     * @param strFormat 指定的转换格式
     * @return 指定毫秒数日期的字符串表示
     */
    public static String dateToString(long lMilliSec, String strFormat) {
        if (null == strFormat)// 如果入参为null
        {
            return null;
        }
        Date date = new Date(lMilliSec);

        DateFormat df = null;
        try {
            df = new SimpleDateFormat(strFormat);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("get formated date failed");
        }
        return df.format(date);
    }

    /**
     * 获取当月<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getMonthOfDate(Date dt) {
        String[] months =
                {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
        Calendar cal = Calendar.getInstance();
        int m = dt.getMonth();
        return months[m];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays =
                {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekNumOfDate(Date dt) {
        String[] weekDays =
                {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 字符串转换到时间格式
     *
     * @param dateStr   需要转换的字符串
     * @param formatStr 需要格式的目标字符串 举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据某天来获取 周一
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String getMondayOfWeek(String dateStr, String formatStr) {
        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String mondy = sdf.format(cal.getTime());

        return mondy;
    }

    /**
     * 根据某天来获取 周日
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String getSundayOfWeek(String dateStr, String formatStr) {
        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

        cal.add(Calendar.DATE, 6);
        String sunday = sdf.format(cal.getTime());
        return sunday;
    }

    /**
     * 根据某天来获取 下周一
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String getNextMonday(String dateStr, String formatStr) {
        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 7);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String mondy = sdf.format(cal.getTime());

        return mondy;
    }

    /**
     * 根据某天来获取 下周日
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String getNextSunday(String dateStr, String formatStr) {
        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

        cal.add(Calendar.DATE, 6 + 7);
        String sunday = sdf.format(cal.getTime());
        return sunday;
    }

    /**
     * 根据某天来获取 上周一
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String getPreviousMonday(String dateStr, String formatStr) {
        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day - 7);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String mondy = sdf.format(cal.getTime());

        return mondy;
    }

    /**
     * 根据某天来获取 上周日
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String getPreviousSunday(String dateStr, String formatStr) {
        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

        cal.add(Calendar.DATE, 6 - 7);
        String sunday = sdf.format(cal.getTime());
        return sunday;
    }

    /**
     * 根据某天来获取 本周的日期
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String[] getWeekdays(String dateStr, String formatStr) {
        String[] arr = new String[7];

        Date dt = StringToDate(dateStr, formatStr);

        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String monday = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String tuesday = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String wednesday = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String thursday = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String friday = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String saturday = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String sunday = sdf.format(cal.getTime());

        arr[0] = monday;
        arr[1] = tuesday;
        arr[2] = wednesday;
        arr[3] = thursday;
        arr[4] = friday;
        arr[5] = saturday;
        arr[6] = sunday;

        return arr;
    }

    /**
     * 将 AM,PM 格式的时间转化为24小时制式的
     *
     * @param time
     * @return
     */
    public static String convertPlanTime(String time) {
        String[] t = time.split(" ");
        String start[] = t[0].split(":");
        if (t[1].equals("PM")) {
            start[0] = String.valueOf(Integer.valueOf(start[0]) + 12);
            return start[0] + ":" + start[1] + ":00";
        } else if (t[1].equals("AM")) {
            return start[0] + ":" + start[1] + ":00";
        } else {
            return time;
        }

    }


    /**
     * 将 HH:mm:ss 格式的时长转化为毫秒
     *
     * @param duration
     * @return
     */
    public static String durationConvert(String duration) {

        Scanner input = new Scanner(duration);
        String s = input.next();
        int index1 = s.indexOf(":");//查找字符串中的字符 ":" 从开始处开始查找,返回所在字符的索引
        int index2 = s.indexOf(":", index1 + 1);//查找字符串中的字符 ":" 从第 index+1 个开始查找,返回所在字符的索引
        //int index3=s.indexOf(".",index2+1);
        int hh = Integer.parseInt(s.substring(0, index1));
        int mi = Integer.parseInt(s.substring(index1 + 1, index2));
        int ss = Integer.parseInt(s.substring(index2 + 1));
        //int ss=Integer.parseInt(s.substring(index2+1,index3));
        //int ms=Integer.parseInt(s.substring(index3+1));

        return String.valueOf((hh * 60 * 60 + mi * 60 + ss) * 1000);


    }

    public static void main(String[] args) {

//		System.out.println(durationConvert("01:00:00"));
        String[] sourceStrArray = "11112.22".split("\\.");
        System.out.println(sourceStrArray[0]);
//		for (int i = 0; i < sourceStrArray.length; i++) {
//			System.out.println(sourceStrArray[i]);
//		}

    }


}
