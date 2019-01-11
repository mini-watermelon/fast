package com.wisdomelon.sys.entity.auth;

import javax.persistence.*;

@Table(name = "FT_AUTH_MENU")
public class FtAuthMenu {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "SUPER_ID")
    private String superId;

    @Column(name = "STAT")
    private String stat;

    @Column(name = "MENU_LEVEL")
    private String menuLevel;

    @Column(name = "MENU_ORD")
    private Integer menuOrd;

    @Column(name = "ICON")
    private String icon;

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
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return SUPER_ID
     */
    public String getSuperId() {
        return superId;
    }

    /**
     * @param superId
     */
    public void setSuperId(String superId) {
        this.superId = superId;
    }

    /**
     * @return STAT
     */
    public String getStat() {
        return stat;
    }

    /**
     * @param stat
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    /**
     * @return MENU_LEVEL
     */
    public String getMenuLevel() {
        return menuLevel;
    }

    /**
     * @param menuLevel
     */
    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * @return MENU_ORD
     */
    public Integer getMenuOrd() {
        return menuOrd;
    }

    /**
     * @param menuOrd
     */
    public void setMenuOrd(Integer menuOrd) {
        this.menuOrd = menuOrd;
    }

    /**
     * @return ICON
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
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