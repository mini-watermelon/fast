package com.wisdomelon.base.code.sql.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Operate implements Serializable{

    private static final long serialVersionUID = -4503836137278430619L;

    private String create;

    private Truncate truncate;

    private List<Insert> insertList = new ArrayList<>();

    private List<Update> updateList = new ArrayList<>();

    private List<Delete> deleteList = new ArrayList<>();

    public Operate() {}

    public Operate(String create) {
        this.create = create;
    }

    public Operate(String create, Truncate truncate, List<Insert> insertList, List<Update> updateList, List<Delete> deleteList) {
        this.create = create;
        this.truncate = truncate;
        this.insertList = insertList;
        this.updateList = updateList;
        this.deleteList = deleteList;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public Truncate getTruncate() {
        return truncate;
    }

    public void setTruncate(Truncate truncate) {
        this.truncate = truncate;
    }

    public List<Insert> getInsertList() {
        return insertList;
    }

    public void setInsertList(List<Insert> insertList) {
        this.insertList = insertList;
    }

    public List<Update> getUpdateList() {
        return updateList;
    }

    public void setUpdateList(List<Update> updateList) {
        this.updateList = updateList;
    }

    public List<Delete> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<Delete> deleteList) {
        this.deleteList = deleteList;
    }

    @Override
    public String toString() {
        return "Operate{" +
                "create='" + create + '\'' +
                ", truncate=" + truncate +
                ", insertList=" + insertList +
                ", updateList=" + updateList +
                ", deleteList=" + deleteList +
                '}';
    }
}
