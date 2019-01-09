package com.wisdomelon.base.code.template.entity;

import com.wisdomelon.base.code.sql.entity.Operate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Table implements Serializable{

    private static final long serialVersionUID = -7159615234523088361L;

    private String moduleName;

    private String tableName;

    private String tableType;

    private Boolean tableCode;

    private String tableComment;

    private Operate operate;

    private List<Field> fieldList = new ArrayList<>();

    public Table() {}

    public Table(String moduleName, String tableName, String tableType, Boolean tableCode, String tableComment, Operate operate, List<Field> fieldList) {
        this.moduleName = moduleName;
        this.tableName = tableName;
        this.tableType = tableType;
        this.tableCode = tableCode;
        this.tableComment = tableComment;
        this.operate = operate;
        this.fieldList = fieldList;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public Boolean getTableCode() {
        return tableCode;
    }

    public void setTableCode(Boolean tableCode) {
        this.tableCode = tableCode;
    }

    @Override
    public String toString() {
        return "Table{" +
                "moduleName=" + moduleName +
                ", tableName='" + tableName + '\'' +
                ", tableType='" + tableType + '\'' +
                ", tableCode=" + tableCode +
                ", tableComment='" + tableComment + '\'' +
                ", operate=" + operate +
                ", fieldList=" + fieldList +
                '}';
    }
}
