package com.wisdomelon.base.code.init.abst;

import com.wisdomelon.base.code.entity.Param;
import com.wisdomelon.base.code.init.Execute;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public abstract class AbstractExecute implements Execute,Param {

    protected String dbXmlDir;

    protected String templateDir;

    protected String dbName;

    protected String driverClass;

    protected String jdbcUrl;

    protected String username;

    protected String password;

    public String getDbXmlDir() {
        return dbXmlDir;
    }

    public void setDbXmlDir(String dbXmlDir) {
        this.dbXmlDir = dbXmlDir;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
