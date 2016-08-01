package com.au.thread;

import org.apache.log4j.Logger;

import com.au.entity.Constent;
import com.au.entity.Mail;
import com.au.service.PriceService;
import com.au.utils.SpringContextUtil;

public class UpThread extends Thread{
	Logger log = Logger.getLogger(UpThread.class);

	@Override
	public void run() {
		Mail mail;
		PriceService priceService = (PriceService) SpringContextUtil.getBean("priceService");
		while(true){
			try{
				String res = priceService.upAnalyse();
				String time = res.length()>0?res.substring(0, 19):"";
				if(!res.equals("") && !time.equals(Constent.upSendTime)){
					Constent.upSendTime = time;
					mail = new Mail();
					mail.setMailTo(Constent.MAIL_TO);
					mail.setSubject("上涨");
					mail.setText("【上涨】\n" + res);
					Constent.MAIL_QUEUE.put(mail);
				}
			}catch(Exception e){
				log.error("UpThread error.", e);
			}
			try {
				Thread.sleep(Constent.UP_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}