package com.wisdomelon.sys.entity.auth;

import javax.persistence.*;

@Table(name = "FT_AUTH_ACCOUNT_ORG")
public class FtAuthAccountOrg {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ORG_ID")
    private String orgId;

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
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
}