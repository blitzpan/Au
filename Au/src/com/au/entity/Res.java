package com.au.entity;

public class Res {
	private String state = "1";//1是成功，0是失败
	private String info = "";//原因
	private Object res = null;//具体的结果
	
	public void setFailed(String info){
		this.state = "0";
		this.info = info;
	}
	public void setSuccessed(String info, Object res){
		this.state = "1";
		this.info = info;
		this.res = res;
	}
	public void setSuccessed(Object res){
		this.state = "1";
		this.res = res;
	}
	public String getState() {
		return state;
	}
	public String getInfo() {
		return info;
	}
	public Object getRes() {
		return res;
	}
}
