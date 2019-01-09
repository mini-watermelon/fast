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
public class Update {

    private String tableName;

    private List<String> listFieldName = new ArrayList<>();

    private Map<String, String> mapFieldValue = new HashMap<>();

    private Map<String, String> mapFieldStrategy = new HashMap<>();

    private Map<String, String> mapFieldWhere = new HashMap<>();

    public Update() {}

    public Update(String tableName, List<String> listFieldName, Map<String, String> mapFieldValue, Map<String, String> mapFieldStrategy, Map<String, String> mapFieldWhere) {
        this.tableName = tableName;
        this.listFieldName = listFieldName;
        this.mapFieldValue = mapFieldValue;
        this.mapFieldStrategy = mapFieldStrategy;
        this.mapFieldWhere = mapFieldWhere;
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

    public Map<String, String> getMapFieldWhere() {
        return mapFieldWhere;
    }

    public void setMapFieldWhere(Map<String, String> mapFieldWhere) {
        this.mapFieldWhere = mapFieldWhere;
    }

    @Override
    public String toString() {
        return "Update{" +
                "tableName='" + tableName + '\'' +
                ", listFieldName=" + listFieldName +
                ", mapFieldValue=" + mapFieldValue +
                ", mapFieldStrategy=" + mapFieldStrategy +
                ", mapFieldWhere=" + mapFieldWhere +
                '}';
    }
}
