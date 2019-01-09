package com.wisdomelon.base.utils.file.entity;

import java.io.Serializable;

/***
 * 文件组件异常参数类
 * @author P_Xyan
 *
 */
public class FileExceptionParam implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int NOT_ALLOW_SUFFIX = 1010;
	public static final String NOT_ALLOW_SUFFIX_MSG = "上传文件的文件后缀名不属于"+FileParam.allowSuffix+"中的一种！";
	
	public static final int FILE_UPLOAD_FAILED = 1011;
	public static final String FILE_UPLOAD_FAILED_MSG = "文件上传失败！";
	
	public static final int FILE_COMPRESS_FAILED = 1012;
	public static final String FILE_COMPRESS_FAILED_MSG = "文件压缩失败！";
	
	public static final int FILE_DOWNNLOAD_FAILED = 1013;
	public static final String FILE_DOWNLOAD_FAILED_MSG = "文件下载失败！";
	
	public static final int FILE_PREVIEW_FAILED = 1014;
	public static final String FILE_PREVIEW_FAILED_MSG = "文件预览加载失败！";
	
	public static final int NOT_EXPORT_SUFFIX = 1015;
	public static final String NOT_EXPORT_SUFFIX_MSG = "导出文件后缀名不属于"+FileParam.exportSuffix+"中的一种！";
	
	public static final int FILE_EXPORT_FAILED = 1016;
	public static final String FILE_EXPORT_FAILED_MSG = "文件导出失败！";
	
	public static final String NOT_EXPORT_HEAD_MSG = "导出数据的表头类型不属于"+FileParam.exportHead+"中的一种！";
	public static final String NOT_EQUEALS_HEAD_MSG = "导出数据每个行的长度不等于表头的行的长度";
	public static final String FILE_NOT_FOUNT_MSG = "文件在服务器上不存在！";
	public static final String REFLECT_NOTEQUEALS_SHEET_MSG = "反射class的长度不等于excel中sheet的个数！";
	public static final String REFLECT_NOTEQUEALS_FIELDS_MSG = "反射class的长度不等于映射字段集合的长度！";
	public static final String SHEET_NOTEQUEALS_FIELD_MSG = "excel中sheet的个数不等于映射字段的个数！";
	public static final String METHOD_INVOKE_FALIED_MSG = "excel导入内容的方法映射失败！";
	
	public static final int FILE_IMPORT_FAILED = 1017;
	public static final String FILE_IMPORT_FAILED_MSG = "文件上传解析失败！";
	
	public static final int FILE_CONVERT_FAILED = 1018;
	public static final String FILE_CONVERT_FAILED_MSG = "文件转换失败！";
}
