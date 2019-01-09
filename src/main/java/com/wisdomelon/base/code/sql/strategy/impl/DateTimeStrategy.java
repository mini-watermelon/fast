package com.wisdomelon.base.code.sql.strategy.impl;

import com.wisdomelon.base.code.sql.strategy.Strategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class DateTimeStrategy implements Strategy {

    @Override
    public String createValue() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String str = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return str;
    }
}
