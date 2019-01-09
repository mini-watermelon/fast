package com.wisdomelon.base.code.sql.strategy.impl;

import com.wisdomelon.base.code.sql.strategy.StrategyArgs;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class DateStrategyArgs implements StrategyArgs<Date> {

    @Override
    public String createValue(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String str = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str;
    }
}
