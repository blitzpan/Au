package com.au.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.entity.Constent;
import com.au.entity.Mail;
import com.au.entity.Price;
import com.au.service.PriceService;

@Component
public class AuTask {
	@Autowired
	private PriceService priceService;
	private Logger log = Logger.getLogger(this.getClass());
	

	public void sendUpMail(){
		try{
			
		}catch(Exception e){
			log.error("上涨分析异常。e=",e);
		}
	}
	
	public void sendPriceMail(){
		try{
			String res = priceService.getPrices(10);
			Mail mail = new Mail();
			mail.setMailTo("1028353676@qq.com");
			mail.setSubject("实时价格");
			mail.setText(res);
			Constent.MAIL_QUEUE.put(mail);//这个是个阻塞的方法。
		}catch(Exception e){
			log.error("发送价格异常。e=", e);
		}
	}

	public PriceService getPriceService() {
		return priceService;
	}

	public void setPriceService(PriceService priceService) {
		this.priceService = priceService;
	}
}