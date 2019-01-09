package com.wisdomelon.base.code.sql.entity;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Truncate {

    private String tableName;

    public Truncate() {}

    public Truncate(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "Truncate{" +
                "tableName='" + tableName + '\'' +
                '}';
    }
}
