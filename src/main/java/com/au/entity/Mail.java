package com.au.entity;

public class Mail {
	private String mailTo;//接收者
	private String mailFrom = "youxiangformajia@163.com";//发送者，和xml中配置的一样
	private String subject;//主题
	private String text;//邮件内容
	
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}