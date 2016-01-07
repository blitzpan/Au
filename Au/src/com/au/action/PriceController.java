package com.au.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.au.entity.Res;
import com.au.service.PriceService;

@Controller
@RequestMapping(value="/priceCon")
public class PriceController {
	@Autowired
	private PriceService priceService;
	private Logger log = Logger.getLogger(PriceController.class);
	
	@RequestMapping(value="/queryPrices")
	@ResponseBody
	public Object queryPrices(HttpSession session, String startTime, String endTime) {
		Res res = new Res();
		try{
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(endTime == null || endTime.trim().equals("")){
				endTime = sdf.format(c.getTime());
			}
			if(startTime == null || startTime.trim().equals("")){
				c.add(Calendar.DATE, -2);
				startTime = sdf.format(c.getTime());
			}
			log.info("queryPrices=" + startTime + "-" + endTime);
			Map parm = new HashMap();
			parm.put("startTime", startTime);
			parm.put("endTime", endTime);
			res.setSuccessed(priceService.queryPrices(parm));
		}catch(Exception e){
			log.error("queryPrices=", e);
			res.setFailed("程序发生异常！");
		}
		return res;
	}
}
