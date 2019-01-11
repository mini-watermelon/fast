package com.wisdomelon.sys.entity.auth;

import javax.persistence.*;

@Table(name = "FT_AUTH_ACCOUNT")
public class FtAuthAccount {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "USERNO")
    private String userno;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ORG_ID")
    private String orgId;

    @Column(name = "ORG_NAME")
    private String orgName;

    @Column(name = "STAT")
    private String stat;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "QQ")
    private String qq;

    @Column(name = "NATION")
    private String nation;

    @Column(name = "MARRIAGE")
    private String marriage;

    @Column(name = "POLITICAL")
    private String political;

    @Column(name = "EDU")
    private String edu;

    @Column(name = "ID_CARD")
    private String idCard;

    @Column(name = "IMG_SRC")
    private String imgSrc;

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
     * @return USERNO
     */
    public String getUserno() {
        return userno;
    }

    /**
     * @param userno
     */
    public void setUserno(String userno) {
        this.userno = userno;
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return ORG_NAME
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
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
     * @return SEX
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return BIRTHDAY
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return TEL
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
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
     * @return QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return NATION
     */
    public String getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * @return MARRIAGE
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * @param marriage
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    /**
     * @return POLITICAL
     */
    public String getPolitical() {
        return political;
    }

    /**
     * @param political
     */
    public void setPolitical(String political) {
        this.political = political;
    }

    /**
     * @return EDU
     */
    public String getEdu() {
        return edu;
    }

    /**
     * @param edu
     */
    public void setEdu(String edu) {
        this.edu = edu;
    }

    /**
     * @return ID_CARD
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * @param idCard
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * @return IMG_SRC
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * @param imgSrc
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}