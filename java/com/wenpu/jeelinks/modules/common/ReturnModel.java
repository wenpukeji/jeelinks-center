package com.wenpu.jeelinks.modules.common;

import java.util.List;

public class ReturnModel {
	public static String NORMAL = "0";
	public static String ERROR = "1";
	public static String UPDATE = "2";
	
	private String status; //0正常 //1 出错 //2需要刷新
	private List returenList;
	private Object returnObject;
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List getReturenList() {
		return returenList;
	}
	public void setReturenList(List returenList) {
		this.returenList = returenList;
	}
	public Object getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
