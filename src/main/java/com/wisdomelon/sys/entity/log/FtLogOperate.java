package com.wisdomelon.sys.entity.log;

import javax.persistence.*;

@Table(name = "FT_LOG_OPERATE")
public class FtLogOperate {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "USER_NO")
    private String userNo;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_IP")
    private String userIp;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "OPER_TIME")
    private String operTime;

    @Column(name = "OPER_URL")
    private String operUrl;

    @Column(name = "DETAILS")
    private String details;

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
     * @return USER_NO
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * @param userNo
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * @return USER_NAME
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return USER_IP
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * @param userIp
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    /**
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return OPER_TIME
     */
    public String getOperTime() {
        return operTime;
    }

    /**
     * @param operTime
     */
    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    /**
     * @return OPER_URL
     */
    public String getOperUrl() {
        return operUrl;
    }

    /**
     * @param operUrl
     */
    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    /**
     * @return DETAILS
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }
}