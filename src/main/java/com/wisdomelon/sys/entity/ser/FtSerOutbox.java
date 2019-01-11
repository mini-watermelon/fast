package com.wisdomelon.sys.entity.ser;

import javax.persistence.*;

@Table(name = "FT_SER_OUTBOX")
public class FtSerOutbox {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "RECIPENTS")
    private String recipents;

    @Column(name = "COPYS")
    private String copys;

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
     * @return EMAIL_ID
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return RECIPENTS
     */
    public String getRecipents() {
        return recipents;
    }

    /**
     * @param recipents
     */
    public void setRecipents(String recipents) {
        this.recipents = recipents;
    }

    /**
     * @return COPYS
     */
    public String getCopys() {
        return copys;
    }

    /**
     * @param copys
     */
    public void setCopys(String copys) {
        this.copys = copys;
    }
}