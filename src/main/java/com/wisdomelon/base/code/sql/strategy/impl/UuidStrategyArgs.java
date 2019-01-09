package com.wisdomelon.base.code.sql.strategy.impl;

import com.wisdomelon.base.code.sql.strategy.StrategyArgs;

import java.util.UUID;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class UuidStrategyArgs implements StrategyArgs<String>{


    @Override
    public String createValue(String prefix) {
        if ("".equals(prefix) || null == prefix) {
            return UUID.randomUUID().toString();
        }
        return prefix + UUID.randomUUID().toString();
    }

}
