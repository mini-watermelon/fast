package com.wisdomelon.sys.entity.ser;

import javax.persistence.*;

@Table(name = "FT_SER_FILE")
public class FtSerFile {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FILE_SIZE")
    private Integer fileSize;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "UPLOAD_TIME")
    private String uploadTime;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

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
     * @return FILE_SIZE
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return FILE_PATH
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return UPLOAD_TIME
     */
    public String getUploadTime() {
        return uploadTime;
    }

    /**
     * @param uploadTime
     */
    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
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
}