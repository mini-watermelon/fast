package com.wisdomelon.base.utils.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "hh:mm:ss";

    public static final String TIME_24_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final String DATE_TIME_24_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /***
     * 获取当前日期的指定格式的日期字符串
     * @param format
     * @return
     */
    public static String getFormatStr(String format) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /***
     * 获取当前日期的指定格式的日期字符串
     * @param formatter
     * @return
     */
    public static String getFormatStr(DateTimeFormatter formatter) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(formatter);
    }

    /***
     * 获取指定日期的指定格式的日期字符串
     * @param date
     * @param format
     * @return
     */
    public static String getFormatStr(Date date, String format) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /***
     * 获取指定日期的指定格式的日期字符串
     * @param date
     * @param formatter
     * @return
     */
    public static String getFormatStr(Date date, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(formatter);
    }


    /***
     * 获取当前日期的[yyyy-MM-dd]格式字符串
     * @return
     */
    public static String getNowDateStr(){
        return getFormatStr(DATE_FORMAT);
    }

    /***
     * 获取当前日期的[hh:mm:ss]格式字符串
     * @return
     */
    public static String getNowTimeStr() {
        return getFormatStr(TIME_FORMAT);
    }

    /***
     * 获取当前日期的[HH:mm:ss]格式字符串
     * @return
     */
    public static String getNowTime24Str() {
        return getFormatStr(TIME_24_FORMAT);
    }

    /***
     * 获取当前日期的[yyyy-MM-dd hh:mm:ss]格式字符串
     * @return
     */
    public static String getNowDateTimeStr() {
        return getFormatStr(DATE_TIME_FORMAT);
    }

    /***
     * 获取当前日期的[yyyy-MM-dd HH:mm:ss]格式字符串
     * @return
     */
    public static String getNowDateTime24Str() {
        return getFormatStr(DATE_TIME_24_FORMAT);
    }

    /***
     * 获取指定日期的[yyyy-MM-dd]格式字符串
     * @return
     */
    public static String getDateStr(Date date){
        return getFormatStr(date, DATE_FORMAT);
    }

    /***
     * 获取指定日期的[hh:mm:ss]格式字符串
     * @return
     */
    public static String getTimeStr(Date date) {
        return getFormatStr(date, TIME_FORMAT);
    }

    /***
     * 获取指定日期的[HH:mm:ss]格式字符串
     * @return
     */
    public static String getTime24Str(Date date) {
        return getFormatStr(date, TIME_24_FORMAT);
    }

    /***
     * 获取指定日期的[yyyy-MM-dd hh:mm:ss]格式字符串
     * @return
     */
    public static String getDateTimeStr(Date date) {
        return getFormatStr(date, DATE_TIME_FORMAT);
    }

    /***
     * 获取指定日期的[yyyy-MM-dd HH:mm:ss]格式字符串
     * @return
     */
    public static String getDateTime24Str(Date date) {
        return getFormatStr(date, DATE_TIME_24_FORMAT);
    }

    /***
     * 根据指定格式获取的日期
     * @return
     */
    public static Date getFormatDate(String dateStr, String format) {
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format));
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /***
     * 根据指定格式获取的日期
     * @return
     */
    public static Date getFormatDateTime(String dateStr, String format) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format));
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /***
     * 根据[yyyy-MM-dd]格式字符串获取日期
     * @return
     */
    public static Date getDate(String dateStr){
        return getFormatDate(dateStr, DATE_FORMAT);
    }

    /***
     * 根据[yyyy-MM-dd HH:mm:ss]格式字符串获取日期
     * @return
     */
    public static Date getDateTime24(String dateStr) {
        return getFormatDateTime(dateStr, DATE_TIME_24_FORMAT);
    }


    /***
     * 获取当前日期
     * @return
     */
    public static Date getNowDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /***
     * 获取本月第一天，根据指定format返回字符串
     * @param format
     * @return
     */
    public static String getNowFirstDayOfMonth(String format){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime firstLocalDateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        return firstLocalDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /***
     * 获取本月最后一天，根据指定format返回字符串
     * @param format
     * @return
     */
    public static String getNowLastDayOfMonth(String format){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime firstLocalDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        return firstLocalDateTime.format(DateTimeFormatter.ofPattern(format));
    }




}
