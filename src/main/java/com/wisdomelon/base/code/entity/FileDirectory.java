package com.wisdomelon.base.code.entity;

import java.io.Serializable;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class FileDirectory implements Serializable{

    private static final long serialVersionUID = -5248646159803322633L;

    /** 数据库xml文件存放路径*/
    private String dbXmlDir;

    public FileDirectory() { }

    public FileDirectory(String dbXmlDir) {
        this.dbXmlDir = dbXmlDir;
    }

    public String getDbXmlDir() {
        return dbXmlDir;
    }

    public void setDbXmlDir(String dbXmlDir) {
        this.dbXmlDir = dbXmlDir;
    }

    @Override
    public String toString() {
        return "FileDirectory{" +
                "dbXmlDir='" + dbXmlDir + '\'' +
                '}';
    }
}
