package com.au.thread;

import org.apache.log4j.Logger;

import com.au.entity.Constent;
import com.au.entity.Mail;
import com.au.service.PriceService;
import com.au.utils.SpringContextUtil;

public class DownThread extends Thread{
	Logger log = Logger.getLogger(DownThread.class);

	@Override
	public void run() {
		Mail mail;
		PriceService priceService = (PriceService) SpringContextUtil.getBean("priceService");
		while(true){
			try{
				String res = priceService.downAnalyse();
				String time = res.length()>0?res.substring(0, 19):"";
				if(!res.equals("") && !time.equals(Constent.downSendTime)){
					Constent.downSendTime = time;
					mail = new Mail();
					mail.setMailTo(Constent.MAIL_TO);
					mail.setSubject("下跌");
					mail.setText("【下跌】\n" + res);
					Constent.MAIL_QUEUE.put(mail);
				}
			}catch(Exception e){
				log.error("DownThread error.", e);
			}
			try {
				Thread.sleep(Constent.DOWN_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}