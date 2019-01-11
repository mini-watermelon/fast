package com.wisdomelon.sys.entity.auth;

import javax.persistence.*;

@Table(name = "FT_AUTH_RES")
public class FtAuthRes {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SCOPE")
    private String scope;

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
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return SCOPE
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
}