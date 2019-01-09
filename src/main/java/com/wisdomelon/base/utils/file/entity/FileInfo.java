package com.wisdomelon.base.utils.file.entity;

import java.io.Serializable;

/***
 * 文件上传后保留的所有文件信息
 * @author P_Xyan
 *
 */
public class FileInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 被上传的文件名称 由用户传进来*/
	private String uploadFileName;
	
	/** 服务器存放文件的根目录 由用户传进来*/
	private String uploadDir;
	
	/** 服务器存放文件的路径： saveDir + '/' + fileName（带后缀）*/
	private String filePath;
	
	/** 服务器存放文件的uploadDir目录后的 自动生成目录或用户自定义目录*/
	private String saveDir;
	
	/** 服务器存放文件的全目录： uploadDir + saveDir*/
	private String rootDir;
	
	/** 服务器存放文件的全部路径： rootDir + '/' + fileName*/
	private String allFilePath;
	
	/** 上传文件的加密处理后的新文件名称或 为uploadFileName或 为用户自定义名称（均带后缀）*/
	private String fileName;
	
	/** 上传文件的后缀名（不带.）*/
	private String fileSuffix;
	
	/** 上传文件的上传时间*/
	private String fileUploadTime;
	
	/** 上传文件的大小*/
	private double fileSize;
	
	public FileInfo() {}

	public FileInfo(String uploadFileName, String uploadDir, String filePath,
			String saveDir, String rootDir, String allFilePath,
			String fileName, String fileSuffix, String fileUploadTime,
			double fileSize) {
		super();
		this.uploadFileName = uploadFileName;
		this.uploadDir = uploadDir;
		this.filePath = filePath;
		this.saveDir = saveDir;
		this.rootDir = rootDir;
		this.allFilePath = allFilePath;
		this.fileName = fileName;
		this.fileSuffix = fileSuffix;
		this.fileUploadTime = fileUploadTime;
		this.fileSize = fileSize;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public String getAllFilePath() {
		return allFilePath;
	}

	public void setAllFilePath(String allFilePath) {
		this.allFilePath = allFilePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileUploadTime() {
		return fileUploadTime;
	}

	public void setFileUploadTime(String fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileInfo [uploadFileName=" + uploadFileName + ", uploadDir="
				+ uploadDir + ", filePath=" + filePath + ", saveDir=" + saveDir
				+ ", rootDir=" + rootDir + ", allFilePath=" + allFilePath
				+ ", fileName=" + fileName + ", fileSuffix=" + fileSuffix
				+ ", fileUploadTime=" + fileUploadTime + ", fileSize="
				+ fileSize + "]";
	}
	
}
