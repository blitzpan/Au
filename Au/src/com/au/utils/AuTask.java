package com.au.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.entity.Constent;
import com.au.entity.Price;
import com.au.service.PriceService;

@Component
public class AuTask {
	@Autowired
	private PriceService priceService;
	@Autowired
	private Gather gather;
	@Autowired
	private SendMailUtils sendMailUtils;
	private Logger log = Logger.getLogger(this.getClass());
	
	public void gather(){
		try{
			List<Price> prices = gather.gather();
			priceService.addPrice(prices);
		}catch(Exception e){
			log.error("gather采集出现异常。e=", e);
		}
	}
	public void sendDownMail(){
		try{
			String res = priceService.downAnalyse();
			String time = res.substring(0, 19);
			log.debug("time=" + time);
			if(!res.equals("") && !time.equals(Constent.downSendTime)){
				Constent.downSendTime = time;
				sendMailUtils.setTitle("下跌");
				res = "【下跌】\n" + res;
				sendMailUtils.sendSimpleMail(res);
			}
		}catch(Exception e){
			log.error("下跌分析异常。e=",e);
		}
	}
	public void sendUpMail(){
		try{
			String res = priceService.upAnalyse();
			System.out.println("res=" + res);
			String time = res.substring(0, 19);
			log.debug("time=" + time);
			if(!res.equals("") && !time.equals(Constent.upSendTime)){
				Constent.upSendTime = time;
				sendMailUtils.setTitle("上涨");
				res = "【上涨】\n" + res;
				sendMailUtils.sendSimpleMail(res);
			}
		}catch(Exception e){
			log.error("上涨分析异常。e=",e);
		}
	}
	
	public void sendPriceMail(){
		try{
			String res = priceService.getPrices(10);
			if(!res.equals("")){
				sendMailUtils.setTitle("实时价格");
				res = "【实时价格】\n" + res;
				sendMailUtils.sendSimpleMail(res);
			}
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

	public Gather getGather() {
		return gather;
	}

	public void setGather(Gather gather) {
		this.gather = gather;
	}
	public SendMailUtils getSendMailUtils() {
		return sendMailUtils;
	}
	public void setSendMailUtils(SendMailUtils sendMailUtils) {
		this.sendMailUtils = sendMailUtils;
	}
}
