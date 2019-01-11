package com.wisdomelon.sys.entity.ser;

import javax.persistence.*;

@Table(name = "FT_SER_NATION")
public class FtSerNation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "REMARK")
    private String remark;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return POSITION
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}