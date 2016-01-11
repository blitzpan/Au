package com.au.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.au.dao.PriceDao;
import com.au.entity.Constent;
import com.au.entity.Price;

@Service
@Transactional
public class PriceService {
	@Autowired
	private PriceDao priceDao;
	private Logger log = Logger.getLogger(this.getClass());

	public void addPrice(List pL) throws Exception {
		try{
			//排除已经采集的数据
			Price tempP;
			for(int i=pL.size()-1; i>=0; i--){
				tempP = (Price)pL.get(i);
				if(priceDao.queryPricesCount(tempP) > 0){
					pL.remove(i);
				}
			}
			priceDao.addPrice(pL);
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
		List<Price> pL = priceDao.queryTopPrices(Constent.downCount);
		boolean down = true;
		for(int i=0; i<Constent.downCount-1; i++){
			if(pL.get(i).getZxj() > pL.get(i+1).getZxj()){
				down = false;
				break;
			}
		}
		if(down){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(Price p : pL){
				res += sdf.format(p.getGxsj()) + "    最新价="+ p.getZxj() + "\n";
			}
		}
		return res;
	}
	public String upAnalyse() throws Exception{
		String res="";
		List<Price> pL = priceDao.queryTopPrices(Constent.up_downCount + Constent.up_upCount);
		boolean up = true;
		for(int i=0; i<Constent.up_upCount-1; i++){
			if(pL.get(i).getZxj() <= pL.get(i+1).getZxj()){
				up = false;
				break;
			}
		}
		for(int i=Constent.up_upCount; i < (Constent.up_downCount+Constent.up_upCount-1); i++){
			if(pL.get(i).getZxj() > pL.get(i+1).getZxj()){
				up = false;
				break;
			}
		}
		if(up){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(Price p : pL){
				res += sdf.format(p.getGxsj()) + "    最新价="+ p.getZxj() + "\n";
			}
		}
		return res;
	}
}