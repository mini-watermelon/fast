package com.wisdomelon.base.code.sql.strategy.impl;

import com.wisdomelon.base.code.sql.strategy.StrategyArgs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class DateTimeStrategyArgs implements StrategyArgs<Date> {

    @Override
    public String createValue(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String str = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return str;
    }
}
