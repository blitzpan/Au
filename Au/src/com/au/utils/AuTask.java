package com.au.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.entity.Price;
import com.au.service.PriceService;

@Component
public class AuTask {
	@Autowired
	private PriceService priceService;
	private Logger log = Logger.getLogger(this.getClass());
	
	public void gather(){
		try{
			Price p = null;
			priceService.addPrice(p);
		}catch(Exception e){
			log.error("gather采集出现异常。e=", e);
		}
	}
}
