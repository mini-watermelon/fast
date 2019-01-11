package com.wisdomelon.base.utils.file.support;

/***
 * 文件处理异常类
 * @author P_Xyan
 */
public class FileDisposeException extends Exception{

	private static final long serialVersionUID = 1L;

	protected int code;

	public int getCode(){
		return code;
	}

	public FileDisposeException(int code){
		super(null, null);
		this.code = code;
	}
	
	public FileDisposeException(int code, String message){
		super(message, null);
		this.code = code;
	}
	
	public FileDisposeException(int code, Throwable t){
		super(null, t);
		this.code = code;
	}
	
	public FileDisposeException(int code, String message, Throwable t) {
		super(message, t);
		this.code = code;
	}

	@Override
	public String toString() {
		return "FileDisposeException code : " + code + ", " + super.toString();
	}
}
