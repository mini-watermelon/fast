package com.wisdomelon.base.code.sql.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Delete {

    private String tableName;

    private Map<String, String> mapFieldWhere = new HashMap<>();

    public Delete() {}

    public Delete(String tableName, Map<String, String> mapFieldWhere) {
        this.tableName = tableName;
        this.mapFieldWhere = mapFieldWhere;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getMapFieldWhere() {
        return mapFieldWhere;
    }

    public void setMapFieldWhere(Map<String, String> mapFieldWhere) {
        this.mapFieldWhere = mapFieldWhere;
    }

    @Override
    public String toString() {
        return "Delete{" +
                "tableName='" + tableName + '\'' +
                ", mapFieldWhere=" + mapFieldWhere +
                '}';
    }
}
