package com.wisdomelon.sys.entity.auth;

import javax.persistence.*;

@Table(name = "FT_AUTH_ORG_ROLE")
public class FtAuthOrgRole {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "ORG_ID")
    private String orgId;

    @Column(name = "ROLE_ID")
    private String roleId;

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
     * @return ORG_ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
}