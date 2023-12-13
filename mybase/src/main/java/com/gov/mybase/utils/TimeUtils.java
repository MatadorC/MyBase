package com.gov.mybase.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


/**
 * <pre>
 *     userModel: lixiang
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : 时间相关工具类
 * </pre>
 */
public final class TimeUtils {

    /**
     * "HH:mm:ss"是24小时制的，"hh:mm:ss"是12小时制。
     * <p>
     * 在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类， 所以日志格式主要使用 SimpleDateFormat 的定义格式.
     * </p>
     * 格式的意义如下： 日期和时间模式 <br>
     * <p>
     * 日期和时间格式由日期和时间模式字符串指定。 在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
     * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
     * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
     * </p>
     * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
     * <table border="1" cellspacing="1" cellpadding="1" * summary="Chart shows format letters, date/time component, presentation, and examples.">
     * <tr>
     * <th align="left">字母</th>
     * <th align="left">日期或时间元素</th>
     * <th align="left">表示</th>
     * <th align="left">示例</th>
     * </tr>
     * <tr>
     * <td><code>G</code></td>
     * <td>Era 标志符</td>
     * <td>ArraySort</td>
     * <td><code>AD</code></td>
     * </tr>
     * <tr>
     * <td><code>y</code></td>
     * <td>年</td>
     * <td>Year</td>
     * <td><code>1996</code>; <code>96</code></td>
     * </tr>
     * <tr>
     * <td><code>M</code></td>
     * <td>年中的月份</td>
     * <td>Month</td>
     * <td><code>July</code>; <code>Jul</code>; <code>07</code></td>
     * </tr>
     * <tr>
     * <td><code>w</code></td>
     * <td>年中的周数</td>
     * <td>Number</td>
     * <td><code>27</code></td>
     * </tr>
     * <tr>
     * <td><code>W</code></td>
     * <td>月份中的周数</td>
     * <td>Number</td>
     * <td><code>2</code></td>
     * </tr>
     * <tr>
     * <td><code>D</code></td>
     * <td>年中的天数</td>
     * <td>Number</td>
     * <td><code>189</code></td>
     * </tr>
     * <tr>
     * <td><code>d</code></td>
     * <td>月份中的天数</td>
     * <td>Number</td>
     * <td><code>10</code></td>
     * </tr>
     * <tr>
     * <td><code>F</code></td>
     * <td>月份中的星期</td>
     * <td>Number</td>
     * <td><code>2</code></td>
     * </tr>
     * <tr>
     * <td><code>E</code></td>
     * <td>星期中的天数</td>
     * <td>ArraySort</td>
     * <td><code>Tuesday</code>; <code>Tue</code></td>
     * </tr>
     * <tr>
     * <td><code>a</code></td>
     * <td>Am/pm 标记</td>
     * <td>ArraySort</td>
     * <td><code>PM</code></td>
     * </tr>
     * <tr>
     * <td><code>H</code></td>
     * <td>一天中的小时数（0-23）</td>
     * <td>Number</td>
     * <td><code>0</code></td>
     * </tr>
     * <tr>
     * <td><code>k</code></td>
     * <td>一天中的小时数（1-24）</td>
     * <td>Number</td>
     * <td><code>24</code></td>
     * </tr>
     * <tr>
     * <td><code>K</code></td>
     * <td>am/pm 中的小时数（0-11）</td>
     * <td>Number</td>
     * <td><code>0</code></td>
     * </tr>
     * <tr>
     * <td><code>h</code></td>
     * <td>am/pm 中的小时数（1-12）</td>
     * <td>Number</td>
     * <td><code>12</code></td>
     * </tr>
     * <tr>
     * <td><code>m</code></td>
     * <td>小时中的分钟数</td>
     * <td>Number</td>
     * <td><code>30</code></td>
     * </tr>
     * <tr>
     * <td><code>s</code></td>
     * <td>分钟中的秒数</td>
     * <td>Number</td>
     * <td><code>55</code></td>
     * </tr>
     * <tr>
     * <td><code>S</code></td>
     * <td>毫秒数</td>
     * <td>Number</td>
     * <td><code>978</code></td>
     * </tr>
     * <tr>
     * <td><code>z</code></td>
     * <td>时区</td>
     * <td>General time zone</td>
     * <td><code>Pacific Standard Time</code>; <code>PST</code>;
     * <code>GMT-08:00</code></td>
     * </tr>
     * <tr>
     * <td><code>Z</code></td>
     * <td>时区</td>
     * <td>RFC 822 time zone</td>
     * <td><code>-0800</code></td>
     * </tr>
     * </table>
     * <p>
     * <pre>
     *                                        HH:mm    15:44
     *                                       h:mm a    3:44 下午
     *                                      HH:mm z    15:44 CST
     *                                      HH:mm Z    15:44 +0800
     *                                   HH:mm zzzz    15:44 中国标准时间
     *                                     HH:mm:ss    15:44:40
     *                                   yyyy-MM-dd    2016-08-12
     *                             yyyy-MM-dd HH:mm    2016-08-12 15:44
     *                          yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     *                     yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     *                EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     *                     yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     *                   yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     *                 yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     *                                       K:mm a    3:44 下午
     *                             EEE, MMM d, ''yy    星期五, 八月 12, '16
     *                        hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     *                 yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     *                   EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     *                                yyMMddHHmmssZ    160812154440+0800
     *                   yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     * </pre>
     * <p>
     * 注意：SimpleDateFormat 不是线程安全的，线程安全需用{@code ThreadLocal<SimpleDateFormat>}
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> simSingleDataMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    @SuppressLint("SimpleDateFormat")
    public static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS");

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 将时间戳转为时间字符串
     * <p>
     * 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, getDefaultSimDataFormat());
    }

    /**
     * 将时间戳转为时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String millis2String(final long millis,
                                       final DateFormat format) {
        return format.format(new Date(millis));
    }

    public static String millis2String(final Date date,
                                       final DateFormat format) {
        return format.format(date);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(final String time) {
        return string2Millis(time, getDefaultSimDataFormat());
    }

    /**
     * 将时间字符串转为时间戳
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(final String time, final DateFormat format) {
        try {
            if (TextUtils.isEmpty(time)) {
                return -1;
            }
            if ("null".equals(time) || time.contains("更新")) {
                return -1;
            }
            return format.parse(time).getTime();
        } catch (ParseException e) {
            LogUtils.e("string2Millis 异常 : " + e);
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为 Date 类型
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return Date 类型
     */
    public static Date string2Date(final String time) {
        return string2Date(time, getDefaultSimDataFormat());
    }

    /**
     * 将时间字符串转为 Date 类型
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date 类型
     */
    public static Date string2Date(final String time, final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 Date 类型转为时间字符串
     * <p>
     * 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param date Date 类型时间
     * @return 时间字符串
     */
    public static String date2String(final Date date) {
        return date2String(date, getDefaultSimDataFormat());
    }

    /**
     * 将 Date 类型转为时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param date   Date 类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(final Date date, final DateFormat format) {
        return format.format(date);
    }

    /**
     * 将 Date 类型转为时间戳
     *
     * @param date Date 类型时间
     * @return 毫秒时间戳
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转为 Date 类型
     *
     * @param millis 毫秒时间戳
     * @return Date 类型时间
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>
     * time0 和 time1 格式都为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time0 时间字符串 0
     * @param time1 时间字符串 1
     * @param unit  单位类型
     *              <ul>
     *              <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *              <li>{@link TimeConstants#SEC }: 秒</li>
     *              <li>{@link TimeConstants#MIN }: 分</li>
     *              <li>{@link TimeConstants#HOUR}: 小时</li>
     *              <li>{@link TimeConstants#DAY }: 天</li>
     *              </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpan(final String time0, final String time1,
                                   @TimeConstants.Unit final int unit) {
        return getTimeSpan(time0, time1, getDefaultSimDataFormat(), unit);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>
     * time0 和 time1 格式都为 format
     * </p>
     *
     * @param time0  时间字符串 0
     * @param time1  时间字符串 1
     * @param format 时间格式
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *               <li>{@link TimeConstants#SEC }: 秒</li>
     *               <li>{@link TimeConstants#MIN }: 分</li>
     *               <li>{@link TimeConstants#HOUR}: 小时</li>
     *               <li>{@link TimeConstants#DAY }: 天</li>
     *               </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpan(final String time0, final String time1,
                                   final DateFormat format, @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(
                Math.abs(string2Millis(time0, format)
                        - string2Millis(time1, format)), unit);
    }

    /**
     * 获取两个时间差（单位：unit）
     *
     * @param date0 Date 类型时间 0
     * @param date1 Date 类型时间 1
     * @param unit  单位类型
     *              <ul>
     *              <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *              <li>{@link TimeConstants#SEC }: 秒</li>
     *              <li>{@link TimeConstants#MIN }: 分</li>
     *              <li>{@link TimeConstants#HOUR}: 小时</li>
     *              <li>{@link TimeConstants#DAY }: 天</li>
     *              </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpan(final Date date0, final Date date1,
                                   @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(
                Math.abs(date2Millis(date0) - date2Millis(date1)), unit);
    }

    /**
     * 获取两个时间差（单位：unit）
     *
     * @param millis0 毫秒时间戳 0
     * @param millis1 毫秒时间戳 1
     * @param unit    单位类型
     *                <ul>
     *                <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                <li>{@link TimeConstants#SEC }: 秒</li>
     *                <li>{@link TimeConstants#MIN }: 分</li>
     *                <li>{@link TimeConstants#HOUR}: 小时</li>
     *                <li>{@link TimeConstants#DAY }: 天</li>
     *                </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpan(final long millis0, final long millis1,
                                   @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(Math.abs(millis0 - millis1), unit);
    }

    /**
     * 获取合适型两个时间差
     * <p>
     * time0 和 time1 格式都为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time0     时间字符串 0
     * @param time1     时间字符串 1
     * @param precision 精度
     *                  <p>
     *                  precision = 0，返回 null
     *                  </p>
     *                  <p>
     *                  precision = 1，返回天
     *                  </p>
     *                  <p>
     *                  precision = 2，返回天和小时
     *                  </p>
     *                  <p>
     *                  precision = 3，返回天、小时和分钟
     *                  </p>
     *                  <p>
     *                  precision = 4，返回天、小时、分钟和秒
     *                  </p>
     *                  <p>
     *                  precision &gt;= 5，返回天、小时、分钟、秒和毫秒
     *                  </p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(final String time0, final String time1,
                                        final int precision) {
        long delta = string2Millis(time0, getDefaultSimDataFormat())
                - string2Millis(time1, getDefaultSimDataFormat());
        return millis2FitTimeSpan(Math.abs(delta), precision);
    }

    /**
     * 获取合适型两个时间差
     * <p>
     * time0 和 time1 格式都为 format
     * </p>
     *
     * @param time0     时间字符串 0
     * @param time1     时间字符串 1
     * @param format    时间格式
     * @param precision 精度
     *                  <p>
     *                  precision = 0，返回 null
     *                  </p>
     *                  <p>
     *                  precision = 1，返回天
     *                  </p>
     *                  <p>
     *                  precision = 2，返回天和小时
     *                  </p>
     *                  <p>
     *                  precision = 3，返回天、小时和分钟
     *                  </p>
     *                  <p>
     *                  precision = 4，返回天、小时、分钟和秒
     *                  </p>
     *                  <p>
     *                  precision &gt;= 5，返回天、小时、分钟、秒和毫秒
     *                  </p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(final String time0, final String time1,
                                        final DateFormat format, final int precision) {
        long delta = string2Millis(time0, format)
                - string2Millis(time1, format);
        return millis2FitTimeSpan(Math.abs(delta), precision);
    }

    /**
     * 获取合适型两个时间差
     *
     * @param date0     Date 类型时间 0
     * @param date1     Date 类型时间 1
     * @param precision 精度
     *                  <p>
     *                  precision = 0，返回 null
     *                  </p>
     *                  <p>
     *                  precision = 1，返回天
     *                  </p>
     *                  <p>
     *                  precision = 2，返回天和小时
     *                  </p>
     *                  <p>
     *                  precision = 3，返回天、小时和分钟
     *                  </p>
     *                  <p>
     *                  precision = 4，返回天、小时、分钟和秒
     *                  </p>
     *                  <p>
     *                  precision &gt;= 5，返回天、小时、分钟、秒和毫秒
     *                  </p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(final Date date0, final Date date1,
                                        final int precision) {
        return millis2FitTimeSpan(
                Math.abs(date2Millis(date0) - date2Millis(date1)), precision);
    }

    /**
     * 获取合适型两个时间差
     *
     * @param millis0   毫秒时间戳 1
     * @param millis1   毫秒时间戳 2
     * @param precision 精度
     *                  <p>
     *                  precision = 0，返回 null
     *                  </p>
     *                  <p>
     *                  precision = 1，返回天
     *                  </p>
     *                  <p>
     *                  precision = 2，返回天和小时
     *                  </p>
     *                  <p>
     *                  precision = 3，返回天、小时和分钟
     *                  </p>
     *                  <p>
     *                  precision = 4，返回天、小时、分钟和秒
     *                  </p>
     *                  <p>
     *                  precision &gt;= 5，返回天、小时、分钟、秒和毫秒
     *                  </p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(final long millis0, final long millis1,
                                        final int precision) {
        return millis2FitTimeSpan(Math.abs(millis0 - millis1), precision);
    }

    /**
     * 获取当前毫秒时间戳
     *
     * @return 毫秒时间戳
     */
    public static long getNowMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     * <p>
     * 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @return 时间字符串
     */
    public static String getNowString() {
        return millis2String(System.currentTimeMillis(), getDefaultSimDataFormat());
    }

    /**
     * 获取当前时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getNowString(final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }

    public static String getNowString(long data) {
        return millis2String(data, getDefaultSimDataFormat());
    }

    public static String getNowString(Date date, String pattern) {
        return millis2String(date, getDefaultSimDataFormat(pattern));
    }

    /**
     * 获取当前 Date
     *
     * @return Date 类型时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @param unit 单位类型
     *             <ul>
     *             <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *             <li>{@link TimeConstants#SEC }: 秒</li>
     *             <li>{@link TimeConstants#MIN }: 分</li>
     *             <li>{@link TimeConstants#HOUR}: 小时</li>
     *             <li>{@link TimeConstants#DAY }: 天</li>
     *             </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpanByNow(final String time,
                                        @TimeConstants.Unit final int unit) {
        return getTimeSpan(getNowString(), time, getDefaultSimDataFormat(), unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *               <li>{@link TimeConstants#SEC }: 秒</li>
     *               <li>{@link TimeConstants#MIN }: 分</li>
     *               <li>{@link TimeConstants#HOUR}: 小时</li>
     *               <li>{@link TimeConstants#DAY }: 天</li>
     *               </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpanByNow(final String time,
                                        final DateFormat format, @TimeConstants.Unit final int unit) {
        return getTimeSpan(getNowString(format), time, format, unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     *
     * @param date Date 类型时间
     * @param unit 单位类型
     *             <ul>
     *             <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *             <li>{@link TimeConstants#SEC }: 秒</li>
     *             <li>{@link TimeConstants#MIN }: 分</li>
     *             <li>{@link TimeConstants#HOUR}: 小时</li>
     *             <li>{@link TimeConstants#DAY }: 天</li>
     *             </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpanByNow(final Date date,
                                        @TimeConstants.Unit final int unit) {
        return getTimeSpan(new Date(), date, unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     *
     * @param millis 毫秒时间戳
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *               <li>{@link TimeConstants#SEC }: 秒</li>
     *               <li>{@link TimeConstants#MIN }: 分</li>
     *               <li>{@link TimeConstants#HOUR}: 小时</li>
     *               <li>{@link TimeConstants#DAY }: 天</li>
     *               </ul>
     * @return unit 时间戳
     */
    public static long getTimeSpanByNow(final long millis,
                                        @TimeConstants.Unit final int unit) {
        return getTimeSpan(System.currentTimeMillis(), millis, unit);
    }

    /**
     * 获取合适型与当前时间的差
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time      时间字符串
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(final String time,
                                             final int precision) {
        return getFitTimeSpan(getNowString(), time, getDefaultSimDataFormat(), precision);
    }

    /**
     * 获取合适型与当前时间的差
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time      时间字符串
     * @param format    时间格式
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(final String time,
                                             final DateFormat format, final int precision) {
        return getFitTimeSpan(getNowString(format), time, format, precision);
    }

    /**
     * 获取合适型与当前时间的差
     *
     * @param date      Date 类型时间
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(final Date date,
                                             final int precision) {
        return getFitTimeSpan(getNowDate(), date, precision);
    }

    /**
     * 获取合适型与当前时间的差
     *
     * @param millis    毫秒时间戳
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回 null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(final long millis,
                                             final int precision) {
        return getFitTimeSpan(System.currentTimeMillis(), millis, precision);
    }

    /**
     * 获取友好型与当前时间的差
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 友好型与当前时间的差
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    public static String getFriendlyTimeSpanByNow(final String time) {
        return getFriendlyTimeSpanByNow(time, getDefaultSimDataFormat2());
    }

    /**
     * 获取友好型与当前时间的差
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 友好型与当前时间的差
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    public static String getFriendlyTimeSpanByNow(final String time,
                                                  final DateFormat format) {
        return getFriendlyTimeSpanByNow(string2Millis(time, format));
    }

    /**
     * 获取友好型与当前时间的差
     *
     * @param date Date 类型时间
     * @return 友好型与当前时间的差
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    public static String getFriendlyTimeSpanByNow(final Date date) {
        return getFriendlyTimeSpanByNow(date.getTime());
    }

    /**
     * 获取友好型与当前时间的差
     *
     * @param millis 毫秒时间戳
     * @return 友好型与当前时间的差
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    public static String getFriendlyTimeSpanByNow(final long millis) {
        long now = System.currentTimeMillis();
        long span = now - millis;
        if (span < 0)
            // U can read http://www.apihome.cn/api/java/Formatter.html to
            // understand it.
            return String.format("%tc", millis);
        if (span < 1000) {
            return "刚刚";
        } else if (span < TimeConstants.MIN) {
            return String.format(Locale.getDefault(), "%d秒前", span
                    / TimeConstants.SEC);
        } else if (span < TimeConstants.HOUR) {
            return String.format(Locale.getDefault(), "%d分钟前", span
                    / TimeConstants.MIN);
        }
        // 获取当天 00:00
        long wee = getWeeOfToday();
        if (millis >= wee) {
            return String.format("今天%tR", millis);
        } else if (millis >= wee - TimeConstants.DAY) {
            return String.format("昨天%tR", millis);
        } else {
            return String.format("%tF", millis);
        }
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取与给定时间等于时间差的时间戳
     *
     * @param millis   给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间戳
     */
    public static long getMillis(final long millis, final long timeSpan,
                                 @TimeConstants.Unit final int unit) {
        return millis + timeSpan2Millis(timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间戳
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time     给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间戳
     */
    public static long getMillis(final String time, final long timeSpan,
                                 @TimeConstants.Unit final int unit) {
        return getMillis(time, getDefaultSimDataFormat(), timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间戳
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time     给定时间
     * @param format   时间格式
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间戳
     */
    public static long getMillis(final String time, final DateFormat format,
                                 final long timeSpan, @TimeConstants.Unit final int unit) {
        return string2Millis(time, format) + timeSpan2Millis(timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间戳
     *
     * @param date     给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间戳
     */
    public static long getMillis(final Date date, final long timeSpan,
                                 @TimeConstants.Unit final int unit) {
        return date2Millis(date) + timeSpan2Millis(timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间字符串
     * <p>
     * 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param millis   给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间字符串
     */
    public static String getString(final long millis, final long timeSpan,
                                   @TimeConstants.Unit final int unit) {
        return getString(millis, getDefaultSimDataFormat(), timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param millis   给定时间
     * @param format   时间格式
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间字符串
     */
    public static String getString(final long millis, final DateFormat format,
                                   final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2String(millis + timeSpan2Millis(timeSpan, unit), format);
    }

    /**
     * 获取与给定时间等于时间差的时间字符串
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time     给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间字符串
     */
    public static String getString(final String time, final long timeSpan,
                                   @TimeConstants.Unit final int unit) {
        return getString(time, getDefaultSimDataFormat(), timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param time     给定时间
     * @param format   时间格式
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间字符串
     */
    public static String getString(final String time, final DateFormat format,
                                   final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2String(
                string2Millis(time, format) + timeSpan2Millis(timeSpan, unit),
                format);
    }

    /**
     * 获取与给定时间等于时间差的时间字符串
     * <p>
     * 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param date     给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间字符串
     */
    public static String getString(final Date date, final long timeSpan,
                                   @TimeConstants.Unit final int unit) {
        return getString(date, getDefaultSimDataFormat(), timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param date     给定时间
     * @param format   时间格式
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的时间字符串
     */
    public static String getString(final Date date, final DateFormat format,
                                   final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2String(date2Millis(date)
                + timeSpan2Millis(timeSpan, unit), format);
    }

    /**
     * 获取与给定时间等于时间差的 Date
     *
     * @param millis   给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的 Date
     */
    public static Date getDate(final long millis, final long timeSpan,
                               @TimeConstants.Unit final int unit) {
        return millis2Date(millis + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * 获取与给定时间等于时间差的 Date
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time     给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的 Date
     */
    public static Date getDate(final String time, final long timeSpan,
                               @TimeConstants.Unit final int unit) {
        return getDate(time, getDefaultSimDataFormat(), timeSpan, unit);
    }

    /**
     * 获取与给定时间等于时间差的 Date
     * <p>
     * 格式为 format
     * </p>
     *
     * @param time     给定时间
     * @param format   时间格式
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的 Date
     */
    public static Date getDate(final String time, final DateFormat format,
                               final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2Date(string2Millis(time, format)
                + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * 获取与给定时间等于时间差的 Date
     *
     * @param date     给定时间
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与给定时间等于时间差的 Date
     */
    public static Date getDate(final Date date, final long timeSpan,
                               @TimeConstants.Unit final int unit) {
        return millis2Date(date2Millis(date) + timeSpan2Millis(timeSpan, unit));
    }

    /**
     * 获取与当前时间等于时间差的时间戳
     *
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与当前时间等于时间差的时间戳
     */
    public static long getMillisByNow(final long timeSpan,
                                      @TimeConstants.Unit final int unit) {
        return getMillis(getNowMills(), timeSpan, unit);
    }

    /**
     * 获取与当前时间等于时间差的时间字符串
     * <p>
     * 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与当前时间等于时间差的时间字符串
     */
    public static String getStringByNow(final long timeSpan,
                                        @TimeConstants.Unit final int unit) {
        return getStringByNow(timeSpan, getDefaultSimDataFormat(), unit);
    }

    /**
     * 获取与当前时间等于时间差的时间字符串
     * <p>
     * 格式为 format
     * </p>
     *
     * @param timeSpan 时间差的毫秒时间戳
     * @param format   时间格式
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与当前时间等于时间差的时间字符串
     */
    public static String getStringByNow(final long timeSpan,
                                        final DateFormat format, @TimeConstants.Unit final int unit) {
        return getString(getNowMills(), format, timeSpan, unit);
    }

    /**
     * 获取与当前时间等于时间差的 Date
     *
     * @param timeSpan 时间差的毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link TimeConstants#MSEC}: 毫秒</li>
     *                 <li>{@link TimeConstants#SEC }: 秒</li>
     *                 <li>{@link TimeConstants#MIN }: 分</li>
     *                 <li>{@link TimeConstants#HOUR}: 小时</li>
     *                 <li>{@link TimeConstants#DAY }: 天</li>
     *                 </ul>
     * @return 与当前时间等于时间差的 Date
     */
    public static Date getDateByNow(final long timeSpan,
                                    @TimeConstants.Unit final int unit) {
        return getDate(getNowMills(), timeSpan, unit);
    }

    /**
     * 判断是否今天
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return {@code true}: 是<br>
     * {@code false}: 否
     */
    public static boolean isToday(final String time) {
        return isToday(string2Millis(time, getDefaultSimDataFormat()));
    }

    /**
     * 判断是否今天
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return {@code true}: 是<br>
     * {@code false}: 否
     */
    public static boolean isToday(final String time, final DateFormat format) {
        return isToday(string2Millis(time, format));
    }

    /**
     * 判断是否今天
     *
     * @param date Date 类型时间
     * @return {@code true}: 是<br>
     * {@code false}: 否
     */
    public static boolean isToday(final Date date) {
        return isToday(date.getTime());
    }

    /**
     * 判断是否今天
     *
     * @param millis 毫秒时间戳
     * @return {@code true}: 是<br>
     * {@code false}: 否
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    /**
     * 判断是否闰年
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return {@code true}: 闰年<br>
     * {@code false}: 平年
     */
    public static boolean isLeapYear(final String time) {
        return isLeapYear(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 判断是否闰年
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return {@code true}: 闰年<br>
     * {@code false}: 平年
     */
    public static boolean isLeapYear(final String time, final DateFormat format) {
        return isLeapYear(string2Date(time, format));
    }

    /**
     * 判断是否闰年
     *
     * @param date Date 类型时间
     * @return {@code true}: 闰年<br>
     * {@code false}: 平年
     */
    public static boolean isLeapYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 判断是否闰年
     *
     * @param millis 毫秒时间戳
     * @return {@code true}: 闰年<br>
     * {@code false}: 平年
     */
    public static boolean isLeapYear(final long millis) {
        return isLeapYear(millis2Date(millis));
    }

    /**
     * 判断是否闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>
     * {@code false}: 平年
     */
    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 获取中式星期
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 中式星期
     */
    public static String getChineseWeek(final String time) {
        return getChineseWeek(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取中式星期
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 中式星期
     */
    public static String getChineseWeek(final String time,
                                        final DateFormat format) {
        return getChineseWeek(string2Date(time, format));
    }

    /**
     * 获取中式星期
     *
     * @param date Date 类型时间
     * @return 中式星期
     */
    public static String getChineseWeek(final Date date) {
        return new SimpleDateFormat("E", Locale.CHINA).format(date);
    }

    /**
     * 获取中式星期
     *
     * @param millis 毫秒时间戳
     * @return 中式星期
     */
    public static String getChineseWeek(final long millis) {
        return getChineseWeek(new Date(millis));
    }

    /**
     * 获取美式星期
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 美式星期
     */
    public static String getUSWeek(final String time) {
        return getUSWeek(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取美式星期
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 美式星期
     */
    public static String getUSWeek(final String time, final DateFormat format) {
        return getUSWeek(string2Date(time, format));
    }

    /**
     * 获取美式星期
     *
     * @param date Date 类型时间
     * @return 美式星期
     */
    public static String getUSWeek(final Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    /**
     * 获取美式星期
     *
     * @param millis 毫秒时间戳
     * @return 美式星期
     */
    public static String getUSWeek(final long millis) {
        return getUSWeek(new Date(millis));
    }

    /**
     * 获取星期索引
     * <p>
     * 注意：周日的 Index 才是 1，周六为 7
     * </p>
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final String time) {
        return getWeekIndex(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取星期索引
     * <p>
     * 注意：周日的 Index 才是 1，周六为 7
     * </p>
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final String time, final DateFormat format) {
        return getWeekIndex(string2Date(time, format));
    }

    /**
     * 获取星期索引
     * <p>
     * 注意：周日的 Index 才是 1，周六为 7
     * </p>
     *
     * @param date Date 类型时间
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取星期索引
     * <p>
     * 注意：周日的 Index 才是 1，周六为 7
     * </p>
     *
     * @param millis 毫秒时间戳
     * @return 1...7
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekIndex(final long millis) {
        return getWeekIndex(millis2Date(millis));
    }

    /**
     * 获取月份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 1...5
     */
    public static int getWeekOfMonth(final String time) {
        return getWeekOfMonth(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取月份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 1...5
     */
    public static int getWeekOfMonth(final String time, final DateFormat format) {
        return getWeekOfMonth(string2Date(time, format));
    }

    /**
     * 获取月份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     *
     * @param date Date 类型时间
     * @return 1...5
     */
    public static int getWeekOfMonth(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取月份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     *
     * @param millis 毫秒时间戳
     * @return 1...5
     */
    public static int getWeekOfMonth(final long millis) {
        return getWeekOfMonth(millis2Date(millis));
    }

    /**
     * 获取年份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 1...54
     */
    public static int getWeekOfYear(final String time) {
        return getWeekOfYear(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取年份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 1...54
     */
    public static int getWeekOfYear(final String time, final DateFormat format) {
        return getWeekOfYear(string2Date(time, format));
    }

    /**
     * 获取年份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     *
     * @param date Date 类型时间
     * @return 1...54
     */
    public static int getWeekOfYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取年份中的第几周
     * <p>
     * 注意：国外周日才是新的一周的开始
     * </p>
     *
     * @param millis 毫秒时间戳
     * @return 1...54
     */
    public static int getWeekOfYear(final long millis) {
        return getWeekOfYear(millis2Date(millis));
    }

    private static final String[] CHINESE_ZODIAC = {"猴", "鸡", "狗", "猪", "鼠",
            "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    /**
     * 获取生肖
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 生肖
     */
    public static String getChineseZodiac(final String time) {
        return getChineseZodiac(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取生肖
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 生肖
     */
    public static String getChineseZodiac(final String time,
                                          final DateFormat format) {
        return getChineseZodiac(string2Date(time, format));
    }

    /**
     * 获取生肖
     *
     * @param date Date 类型时间
     * @return 生肖
     */
    public static String getChineseZodiac(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return CHINESE_ZODIAC[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * 获取生肖
     *
     * @param millis 毫秒时间戳
     * @return 生肖
     */
    public static String getChineseZodiac(final long millis) {
        return getChineseZodiac(millis2Date(millis));
    }

    /**
     * 获取生肖
     *
     * @param year 年
     * @return 生肖
     */
    public static String getChineseZodiac(final int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23,
            23, 24, 23, 22};
    private static final String[] ZODIAC = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座",
            "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    /**
     * 获取星座
     * <p>
     * time 格式为 yyyy-MM-dd HH:mm:ss
     * </p>
     *
     * @param time 时间字符串
     * @return 生肖
     */
    public static String getZodiac(final String time) {
        return getZodiac(string2Date(time, getDefaultSimDataFormat()));
    }

    /**
     * 获取星座
     * <p>
     * time 格式为 format
     * </p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 生肖
     */
    public static String getZodiac(final String time, final DateFormat format) {
        return getZodiac(string2Date(time, format));
    }

    /**
     * 获取星座
     *
     * @param date Date 类型时间
     * @return 星座
     */
    public static String getZodiac(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return getZodiac(month, day);
    }

    /**
     * 获取星座
     *
     * @param millis 毫秒时间戳
     * @return 星座
     */
    public static String getZodiac(final long millis) {
        return getZodiac(millis2Date(millis));
    }

    /**
     * 获取星座
     *
     * @param month 月
     * @param day   日
     * @return 星座
     */
    public static String getZodiac(final int month, final int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1] ? month - 1
                : (month + 10) % 12];
    }

    private static long timeSpan2Millis(final long timeSpan,
                                        @TimeConstants.Unit final int unit) {
        return timeSpan * unit;
    }

    private static long millis2TimeSpan(final long millis,
                                        @TimeConstants.Unit final int unit) {
        return millis / unit;
    }

    private static String millis2FitTimeSpan(long millis, int precision) {
        if (millis < 0 || precision <= 0)
            return null;
        precision = Math.min(precision, 5);
        String[] units = {"天", "小时", "分钟", "秒", "毫秒"};
        if (millis == 0)
            return 0 + units[precision - 1];
        StringBuilder sb = new StringBuilder();
        int[] unitLen = {86400000, 3600000, 60000, 1000, 1};
        for (int i = 0; i < precision; i++) {
            if (millis >= unitLen[i]) {
                long mode = millis / unitLen[i];
                millis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 从时间(毫秒)中提取出日期
     *
     * @param millisecond
     * @return
     */

    public static SimpleDateFormat format_ymd = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat format_md = new SimpleDateFormat("MM-dd");

    public static String getDateFromMillisecond(Long millisecond) {

        Date date = null;
        try {
            date = new Date(millisecond);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();

        // //今天
        Calendar today = Calendar.getInstance();

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));

        // Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        // 昨天
        Calendar yesterday = Calendar.getInstance();

        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH,
                current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        // 今年
        Calendar thisYear = Calendar.getInstance();

        thisYear.set(Calendar.YEAR, current.get(Calendar.YEAR));
        thisYear.set(Calendar.MONTH, 0);
        thisYear.set(Calendar.DAY_OF_MONTH, 0);
        thisYear.set(Calendar.HOUR_OF_DAY, 0);
        thisYear.set(Calendar.MINUTE, 0);
        thisYear.set(Calendar.SECOND, 0);

        current.setTime(date);

        // 今年以前
        if (current.before(thisYear)) {
            if (format_ymd == null) {
                format_ymd = new SimpleDateFormat("yyyy-MM-dd");
            }
            String dateStr = format_ymd.format(date);
            return dateStr;
        } else if (current.after(today)) {
            return "今天";
        } else if (current.before(today) && current.after(yesterday)) {
            return "昨天";
        } else {
            if (format_md == null) {
                format_md = new SimpleDateFormat("MM-dd");
            }
            String dateStr = format_md.format(date);
            return dateStr;
        }
    }

    /**
     * 从时间(毫秒)中提取出时间(时:分) 时间格式: 时:分
     *
     * @param millisecond
     * @return
     */
    public static SimpleDateFormat simpleDateFormat_hm = new SimpleDateFormat("HH:mm");

    public static String getTimeFromMillisecond(Long millisecond) {
        if (simpleDateFormat_hm == null) {
            simpleDateFormat_hm = new SimpleDateFormat("HH:mm");
        }
        Date date = new Date(millisecond);
        String timeStr = simpleDateFormat_hm.format(date);
        return timeStr;
    }

    /**
     * 将毫秒转化成固定格式的时间 时间格式: yyyy-MM-dd HH:mm:ss
     *
     * @param millisecond
     * @return
     */
    public static String getDateTimeFromMillisecond(Long millisecond,
                                                    SimpleDateFormat format) {
        Date date = new Date(millisecond);
        String dateStr = format.format(date);
        return dateStr;
    }

    /**
     * 将毫秒转化成固定格式的时间
     *
     * @param millisecond
     * @return
     */
    public static String getDateTimeFromMillisecond(Long millisecond) {
        Date date = new Date(millisecond);
        String dateStr = getDefaultSimDataFormat().format(date);
        return dateStr;
    }

    /**
     * 将时间转化成毫秒
     *
     * @param time
     * @return
     */
    public static Long timeStrToSecond(String time) {
        try {
            Long second = getDefaultSimDataFormat().parse(time).getTime();
            return second;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1l;
    }

    /**
     * 将时间转化成毫秒
     *
     * @param time
     * @param format
     * @return
     */
    public static Long timeStrToSecond(String time, SimpleDateFormat format) {
        try {
            Long second = format.parse(time).getTime();
            return second;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1l;
    }

    /**
     * 获取时间间隔
     *
     * @param millisecond
     * @return
     */
    public static String getSpaceTime(Long millisecond) {
        Calendar calendar = Calendar.getInstance();
        Long currentMillisecond = calendar.getTimeInMillis();

        // 间隔秒
        Long spaceSecond = (currentMillisecond - millisecond) / 1000;

        // 一分钟之内
        if (spaceSecond >= 0 && spaceSecond < 60) {
            return "片刻之前";
        }
        // 一小时之内
        else if (spaceSecond / 60 > 0 && spaceSecond / 60 < 60) {
            return spaceSecond / 60 + "分钟之前";
        }
        // 一天之内
        else if (spaceSecond / (60 * 60) > 0 && spaceSecond / (60 * 60) < 24) {
            return spaceSecond / (60 * 60) + "小时之前";
        }
        // 3天之内
        else if (spaceSecond / (60 * 60 * 24) > 0
                && spaceSecond / (60 * 60 * 24) < 3) {
            return spaceSecond / (60 * 60 * 24) + "天之前";
        } else {
            return getDateTimeFromMillisecond(millisecond);
        }
    }


    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @return str1 >= str2 true
     * str1 <  str2 false
     */
    public static boolean isDate2Bigger(String oldTime, String newTime) {
        boolean isBigger = false;
        if (TextUtils.isEmpty(oldTime) || TextUtils.isEmpty(newTime)) {
            return isBigger;
        }
        Date dt1 = null;
        Date dt2 = null;
        try {
            SimpleDateFormat timeFormat1 = getTimeFormat(oldTime);
            dt1 = timeFormat1.parse(oldTime);
            SimpleDateFormat timeFormat2 = getTimeFormat(newTime);
            dt2 = timeFormat2.parse(newTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1 == null || dt2 == null) {
            return isBigger;
        }

        if (dt1.getTime() > dt2.getTime()) {
            // LogUtils.e("time1 = " + dt1.getTime(), "time2 = " + dt2.getTime(), ">= return true");
            isBigger = true;
        } else {
            // LogUtils.e("time1 = " + dt1.getTime(), "time2 = " + dt2.getTime(), ">= return false");
            isBigger = false;
        }
        return isBigger;
    }


    public static SimpleDateFormat getTimeFormat(String time) {
        SimpleDateFormat formatter = null;
        if (time.contains(".")) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        } else if (time.contains(":")) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        return formatter;
    }


    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @return true str1 >= str2 <br/>false str1 < str2
     */
    public static boolean isDate2Bigger(String time1, String time2, SimpleDateFormat format) {
        boolean isBigger = false;

        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = format.parse(time1);
            dt2 = format.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }

    /**
     * yyyy年 MM月dd日
     *
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年 MM月dd日");
        return simpleDateFormat.format(date);
    }
    /**
     * yyyy-MM-dd
     *
     * @return
     */
    public static String formatDate2(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * yyyy年 MM月
     *
     * @return
     */
    public static String formatDate3(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年 MM月");
        return simpleDateFormat.format(date);
    }

    /**
     * yyyy年
     *
     * @return
     */
    public static String formatDate4(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年");
        return simpleDateFormat.format(date);
    }

    private static DateFormat getDefaultSimDataFormat() {
//        if (null == simpleDateFormatThreadLocal.get()) {
//            simpleDateFormatThreadLocal.set(new SimpleDateFormat(
//                    "yyyy-MM-dd HH:mm:ss.SSS") );
//        }
//        return simpleDateFormatThreadLocal.get();
        return getSingleDefSimDataFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    private static DateFormat getDefaultSimDataFormat2() {
        return getSingleDefSimDataFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static DateFormat getDefaultSimDataFormat(String pattern) {
//        if (null == simpleDateFormatThreadLocal.get()) {
//            simpleDateFormatThreadLocal.set(new SimpleDateFormat(
//                    "yyyy-MM-dd HH:mm:ss.SSS") );
//        }
//        return simpleDateFormatThreadLocal.get();
        if (TextUtils.isEmpty(pattern))
            return getSingleDefSimDataFormat("yyyy-MM-dd HH:mm:ss.SSS");
        else return getSingleDefSimDataFormat(pattern);
    }

    private static SimpleDateFormat getSingleDefSimDataFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = simSingleDataMap.get(pattern);
        if (tl == null) {
            synchronized (TimeUtils.class) {
                tl = simSingleDataMap.get(pattern);
                if (tl == null) {
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    simSingleDataMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

//    public static String getGroupId() {
//        String groupId = getNowString().replaceAll("-", "").
//                replaceAll(" ", "").replaceAll(":", "");
//        return StringUtils.isTrimEmpty(groupId) ? "" : groupId;
//    }

    //--------------------------------------------------------------

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        if (TextUtils.isEmpty(day)) {
            day = getNowString();
        }

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) throws ParseException {

        if (TextUtils.isEmpty(day)) {
            day = getNowString();
        }

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * 录音的起止时间格式化,相对于上课开始的时间,如：00:20:32,单位,秒
     *
     * @param time
     * @return
     */
    public static String getRecordTime(int time) {
        String hour = "00";
        String minute = "00";
        String second = "00";
        if (time < 60) {
            //小于60秒
            second = fillUpZero(time);
        } else if (time >= 60 && time < 3600) {
            //大于等于1分钟，小于一个小时
            int min = time / 60;
            int sec = time % 60;
            minute = fillUpZero(min);
            second = fillUpZero(sec);
        } else {
            //大于一个小时
            int h = time / 3600;
            int min_remain = time % 3600;
            int min = min_remain / 60;
            int sec = min_remain % 60;
            //hour = fillUpZero(h);
            minute = fillUpZero(min);
            second = fillUpZero(sec);
        }
        return minute + ":" + second;
    }

    /**
     * 给一个单位的时间数，通过补0，转化为字符串
     *
     * @param time ,单位可能是 时,分,秒
     * @return
     */
    public static String fillUpZero(int time) {
        String str = "00";
        if (time >= 10) {
            str = String.valueOf(time);
        } else {
            str = "0" + time;
        }
        return str;
    }

    /**
     * 获取毫秒级的时间格式
     *
     * @return
     */
    public static String getTimeSSS() {
        // 所需要的时间格式，注意：SSS就是本次所要的毫秒值
        String patten = "yyyy-MM-dd HH:mm:ss.SSS";
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateFormatStr = format.format(new Date());
        return dateFormatStr;
    }

    public static SimpleDateFormat format_md2 = new SimpleDateFormat("MM.dd");
    public static SimpleDateFormat format_md4 = new SimpleDateFormat("HH:mm");

    /**
     * 获取“月.日”格式
     *
     * @return
     */
    public static String getMday() {
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return format_md2.format(date);
    }

    /**
     * 获取“时:分”格式
     *
     * @return
     */
    public static String getHHmm() {
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return format_md4.format(date);
    }

    /**
     * 获取“年-月-日”格式
     *
     * @return
     */
    public static String getyMday() {
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        if (format_ymd == null) {
            format_ymd = new SimpleDateFormat("yyyy-MM-dd");
        }
        return format_ymd.format(date);
    }

    /**
     * 获取“年-月-日 时:分:秒”格式
     *
     * @return
     */
    public static String getyMdayhms() {
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        if (format_ymd == null) {
            format_ymd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return format_ymd.format(date);
    }

    public static SimpleDateFormat format_md3 = new SimpleDateFormat("yyyy-MM");
    /**
     * 获取“年-月”格式
     *
     * @return
     */
    public static String getYearMM() {
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return format_md3.format(date);
    }


    /**
     * 判断两个时间戳是否是同一天
     *
     * @param millis1
     * @param millis2
     * @param timeZone
     * @return
     */
    public static boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private static long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }

    /**
     * 获取当前 周 的开始和结束日前
     *
     * @param time 2023-12-07
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getWeekSE(String time) {
        LocalDate currentDate = LocalDate.parse(time);
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = currentDate.with(DayOfWeek.SUNDAY);

        System.out.println("当前周的开始日期：" + startOfWeek);
        System.out.println("当前周的结束日期：" + endOfWeek);
        return startOfWeek + "#" + endOfWeek;
    }

    /**
     * 根据提供的年月日获取该月份的第一天
     *
     * * @param time
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9
     */
    public static String getSupportBeginDayofMonth(String time) throws ParseException {
        Date date = DateUtils.parseDate(time, "yyyy-MM-dd");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Date firstDate = startDate.getTime();

        return formatDate2(firstDate);
    }

    /**
     * 根据提供的年月获取该月份的最后一天
     *
     * @param time
     * @return
     * @Description: (这里用一句话描述这个方法的作用)
     * @Author: gyz
     * @Since: 2017-1-9
     */
    public static String getSupportEndDayofMonth(String time) throws ParseException {
        Date date = DateUtils.parseDate(time, "yyyy-MM-dd");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        startDate.set(Calendar.HOUR_OF_DAY, 23);
        startDate.set(Calendar.MINUTE, 59);
        startDate.set(Calendar.SECOND, 59);
        startDate.set(Calendar.MILLISECOND, 999);
        Date firstDate = startDate.getTime();
        return formatDate2(firstDate);
    }

    /**
     * 当天的开始时间
     *
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 当天的结束时间
     *
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date.getTime();
    }
}
