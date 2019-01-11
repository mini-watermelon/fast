package com.wisdomelon.sys.entity.ser;

import javax.persistence.*;

@Table(name = "FT_SER_MAIL")
public class FtSerMail {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "SMTP_IP")
    private String smtpIp;

    @Column(name = "SMTP_PORT")
    private String smtpPort;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

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
     * @return SMTP_IP
     */
    public String getSmtpIp() {
        return smtpIp;
    }

    /**
     * @param smtpIp
     */
    public void setSmtpIp(String smtpIp) {
        this.smtpIp = smtpIp;
    }

    /**
     * @return SMTP_PORT
     */
    public String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param smtpPort
     */
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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