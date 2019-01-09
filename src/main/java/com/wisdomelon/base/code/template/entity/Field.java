package com.wisdomelon.base.code.template.entity;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Field implements Serializable{

    private static final long serialVersionUID = -6063306503819696881L;

    private String tableName;

    private String name;

    private String type;

    private String length;

    private String special;

    private String defaultValue;

    private String comment;

    public Field() {}

    public Field(String tableName, String name, String type, String length, String special, String defaultValue, String comment) {
        this.tableName = tableName;
        this.name = name;
        this.type = type;
        this.length = length;
        this.special = special;
        this.defaultValue = defaultValue;
        this.comment = comment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Field{" +
                "tableName='" + tableName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", length='" + length + '\'' +
                ", special='" + special + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
