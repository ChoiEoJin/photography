package com.collabo.photography.common.exception;

@SuppressWarnings("serial")
public class CommonException extends RuntimeException {
	
	private Object object;
	private String code;
	private String message;
	private Exception ex;
	private Object params[];
	
	public CommonException(String msg) {
		this.code = "400";
		this.message = msg;
	}
	public CommonException(String code, String msg) {
		this.code = code;
		this.message = msg;
	}
	public CommonException(String code, Object params[]) {
		this.code = code;
		this.params = params;
	}

	public CommonException(Exception ex, String code) {
		super(ex);
		this.code = code;
		this.ex = ex;
	}

	public CommonException(Exception ex, String code, Object params[]) {
		super(ex);
		this.code = code;
		this.ex = ex;
		this.params = params;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object params[]) {
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
