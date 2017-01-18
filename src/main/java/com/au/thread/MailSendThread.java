package com.au.thread;

import org.apache.log4j.Logger;

import com.au.entity.Constent;
import com.au.entity.Mail;
import com.au.utils.SendMailUtils;
import com.au.utils.SpringContextUtil;

public class MailSendThread extends Thread{
	Logger log = Logger.getLogger(MailSendThread.class);
	
	@Override
	public void run() {
		Mail mail;
		SendMailUtils smu = (SendMailUtils) SpringContextUtil.getBean("sendMailUtils");
		while(true){
			try{
				mail = Constent.MAIL_QUEUE.take();//因为这个方法是阻塞的，所以就不需要sleep了。
				if(mail==null){//这个方法应该永远不会进来。如果报了这行日志，那么就是我对take的理解有错误。
					log.error("mail is null.");
					continue;
				}
				smu.sendSimpleMail(mail);
			}catch(Exception e){
				log.error("send mail error.",e);
			}
		}
	}
	public static void main(String[] args) {
	}
}