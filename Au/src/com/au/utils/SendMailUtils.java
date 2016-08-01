package com.au.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.au.entity.Mail;

@Component
@Scope("prototype")
public class SendMailUtils{
	@Autowired
	private JavaMailSender mailSender;
	
	public SendMailUtils() {
		super();
	}
	public String sendSimpleMail(Mail mB) {
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setTo(mB.getMailTo());// 接受者
			mail.setFrom(mB.getMailFrom());// 发送者,和xml中的一致
			mail.setSubject(mB.getSubject());// 主题
			mail.setText(mB.getText());// 邮件内容
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "a";
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}