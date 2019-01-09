package com.wisdomelon.base.code.template.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class Module implements Serializable{

    private static final long serialVersionUID = 8386206831067401824L;

    private String moduleName;

    private boolean code;

    private List<Table> tableList = new ArrayList<>();

    public Module() {}

    public Module(String moduleName, boolean code) {
        this.moduleName = moduleName;
        this.code = code;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleName='" + moduleName + '\'' +
                ", code=" + code +
                ", tableList=" + tableList +
                '}';
    }
}
