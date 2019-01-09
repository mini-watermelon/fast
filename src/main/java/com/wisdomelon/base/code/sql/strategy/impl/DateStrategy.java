package com.wisdomelon.base.code.sql.strategy.impl;

import com.wisdomelon.base.code.sql.strategy.Strategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class DateStrategy implements Strategy{

    @Override
    public String createValue() {
        LocalDate localDate = LocalDate.now();
        String str = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return str;
    }
}
