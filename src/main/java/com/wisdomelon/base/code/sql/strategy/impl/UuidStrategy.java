package com.wisdomelon.base.code.sql.strategy.impl;

import com.wisdomelon.base.code.sql.strategy.Strategy;

import java.util.UUID;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class UuidStrategy implements Strategy{


    @Override
    public String createValue() {
        return UUID.randomUUID().toString();
    }

}
