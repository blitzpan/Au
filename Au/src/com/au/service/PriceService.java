package com.au.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.au.dao.PriceDao;
import com.au.entity.Price;

@Service
@Transactional
public class PriceService {
	@Autowired
	private PriceDao priceDao;
	private Logger log = Logger.getLogger(this.getClass());

	public void addPrice(List p) throws Exception {
		try{
			priceDao.addPrice(p);
		}catch(Exception e){
			log.error("addPrice新增采集结果异常！", e);
			throw e;
		}
	}
	/**
	 * @Description:查询时间范围内的数据 
	 * @param @param parm
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Price>  
	 * @throws
	 * @author Panyk
	 * @date 2016年1月7日
	 */
	public List<Price> queryPrices(Map parm) throws Exception{
		return priceDao.queryPrices(parm);
	}
	public String downAnalyse() throws Exception{
		String res="";
		Price p = priceDao.queryLastPrice();
		if(p.getKpj() > p.getZxj()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			res += sdf.format(p.getGxsj()) + " 开盘价：" + p.getKpj() + " 最新价："+ p.getZxj() + "\n" + p;
		}
		return res;
	}
}