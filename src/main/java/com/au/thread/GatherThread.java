package com.au.thread;

import java.util.List;

import org.apache.log4j.Logger;

import com.au.entity.Price;
import com.au.service.PriceService;
import com.au.utils.Gather;
import com.au.utils.SpringContextUtil;

public class GatherThread extends Thread{
	Logger log = Logger.getLogger(GatherThread.class);

	@Override
	public void run() {
		Gather gather = (Gather) SpringContextUtil.getBean("gather");
		PriceService priceService = (PriceService) SpringContextUtil.getBean("priceService");
		while(true){
			try{
				List<Price> prices = gather.gather();
				priceService.addPrice(prices);
			}catch(Exception e){
				log.error("gatherThread error.", e);
			}
			try {
				Thread.sleep(1000*60*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}