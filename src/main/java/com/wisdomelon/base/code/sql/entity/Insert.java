package com.wisdomelon.base.code.sql.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Insert {

    private String tableName;

    private List<String> listFieldName = new ArrayList<>();

    private Map<String, String> mapFieldValue = new HashMap<>();

    private Map<String, String> mapFieldStrategy = new HashMap<>();

    public Insert() {}

    public Insert(String tableName, List<String> listFieldName, Map<String, String> mapFieldValue, Map<String, String> mapFieldStrategy) {
        this.tableName = tableName;
        this.listFieldName = listFieldName;
        this.mapFieldValue = mapFieldValue;
        this.mapFieldStrategy = mapFieldStrategy;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getListFieldName() {
        return listFieldName;
    }

    public void setListFieldName(List<String> listFieldName) {
        this.listFieldName = listFieldName;
    }

    public Map<String, String> getMapFieldValue() {
        return mapFieldValue;
    }

    public void setMapFieldValue(Map<String, String> mapFieldValue) {
        this.mapFieldValue = mapFieldValue;
    }

    public Map<String, String> getMapFieldStrategy() {
        return mapFieldStrategy;
    }

    public void setMapFieldStrategy(Map<String, String> mapFieldStrategy) {
        this.mapFieldStrategy = mapFieldStrategy;
    }

    @Override
    public String toString() {
        return "Insert{" +
                "tableName='" + tableName + '\'' +
                ", listFieldName=" + listFieldName +
                ", mapFieldValue=" + mapFieldValue +
                ", mapFieldStrategy=" + mapFieldStrategy +
                '}';
    }
}
