package com.wisdomelon.base.code.sql.strategy;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
@FunctionalInterface
public interface Strategy {

    /***
     * 创建生成策略的具体方法
     * @return
     */
    String createValue();
}
