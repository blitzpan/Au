package com.au.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.au.dao.PriceDao;

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
}