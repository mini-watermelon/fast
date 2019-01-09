package com.wisdomelon.test.utils;

import com.wisdomelon.base.utils.datetime.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class TestDate {

    @Test
    public void test1(){
        System.out.println(DateUtils.getNowDateStr());
        System.out.println(DateUtils.getNowTimeStr());
        System.out.println(DateUtils.getNowTime24Str());
        System.out.println(DateUtils.getNowDateTimeStr());
        System.out.println(DateUtils.getNowDateTime24Str());
    }

    @Test
    public void test2(){
        System.out.println(DateUtils.getDate("2015-10-18"));
        System.out.println(DateUtils.getDateTime24("2015-10-18 10:11:12"));
    }

    @Test
    public void test3() {
        Date date = DateUtils.getNowDate();
        System.out.println(DateUtils.getDateStr(date));
        System.out.println(DateUtils.getTimeStr(date));
        System.out.println(DateUtils.getTime24Str(date));
        System.out.println(DateUtils.getDateTimeStr(date));
        System.out.println(DateUtils.getDateTime24Str(date));
    }

    @Test
    public void test4() {
        System.out.println(DateUtils.getNowFirstDayOfMonth("yyyy/MM/dd HH"));
        System.out.println(DateUtils.getNowLastDayOfMonth("yyyy/MM/dd HH"));
    }
}
