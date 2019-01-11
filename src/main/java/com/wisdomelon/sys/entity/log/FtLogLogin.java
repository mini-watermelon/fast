package com.wisdomelon.sys.entity.log;

import javax.persistence.*;

@Table(name = "FT_LOG_LOGIN")
public class FtLogLogin {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "USER_NO")
    private String userNo;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "LOGIN_IP")
    private String loginIp;

    @Column(name = "LOGIN_DATE")
    private String loginDate;

    @Column(name = "LOGON_DATE")
    private String logonDate;

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
     * @return LOGIN_IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * @param loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * @return LOGIN_DATE
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * @param loginDate
     */
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * @return LOGON_DATE
     */
    public String getLogonDate() {
        return logonDate;
    }

    /**
     * @param logonDate
     */
    public void setLogonDate(String logonDate) {
        this.logonDate = logonDate;
    }
}