package com.wisdomelon.base.utils.file.support;

import java.io.Serializable;

/***
 * 文件组件参数类
 * @author P_Xyan
 *
 */
public class FileParam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String[] allowSuffix = {"doc","docx","DOC","DOCX","xls","xlsx","XLS","XLSX","ppt","pptx","PPT","PPTX","pdf","PDF","txt","TXT","properties","xml","html","sql"};
	
	public static final String[] officeSuffix = {"doc","docx","DOC","DOCX","xls","xlsx","XLS","XLSX","ppt","pptx","PPT","PPTX","pdf","PDF"};

	public static final String[] exportSuffix = {"xls","xlsx","XLS","XLSX"};
	
	public static final String[] exportHead = {"String[]","List","ArrayList","Map","HashMap"};
	
	public static final String pdfSuffix = "pdf";
	
	public static final String sprit = "/";
	
	public static final String dot = ".";
	
	public static final String ISO8859_1 = "ISO8859_1";
	
	public static final String GBK = "GBK";
	
	public static final String UTF8 = "UTF-8";
}
