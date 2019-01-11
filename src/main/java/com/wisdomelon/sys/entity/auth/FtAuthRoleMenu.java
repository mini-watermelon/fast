package com.wisdomelon.sys.entity.auth;

import javax.persistence.*;

@Table(name = "FT_AUTH_ROLE_MENU")
public class FtAuthRoleMenu {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "MENU_ID")
    private String menuId;

    @Column(name = "MENU_RES")
    private String menuRes;

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
     * @return ROLE_ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return MENU_ID
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * @return MENU_RES
     */
    public String getMenuRes() {
        return menuRes;
    }

    /**
     * @param menuRes
     */
    public void setMenuRes(String menuRes) {
        this.menuRes = menuRes;
    }
}