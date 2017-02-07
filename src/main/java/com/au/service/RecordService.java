package com.au.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.au.dao.RecordDao;
import com.au.entity.Record;
import com.au.utils.Page;

@Service
@Transactional
public class RecordService {
	@Autowired
	private RecordDao recordDao;
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * @Description:新增记录 
	 * @param @param r
	 * @param @return
	 * @param @throws Exception   
	 * @return int  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public int addRecord(Record r) throws Exception{
		if(r!=null&&r.getType()==0){//买入
			return this.addBuyRecord(r);
		}else{//卖出
			return this.addSellRecord(r);
		}
	}
	/**
	 * @Description:新增买入记录 
	 * @param @param r
	 * @param @return
	 * @param @throws Exception   
	 * @return int  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	private int addBuyRecord(Record r) throws Exception {
		try{
			r.setAmount(r.getGram() * r.getPrice());
			return recordDao.addBuyRecord(r);
		}catch(Exception e){
			log.error("addBuyRecord异常！", e);
			throw e;
		}
	}
	/**
	 * @Description:新增卖出记录 
	 * @param @param r
	 * @param @return
	 * @param @throws Exception   
	 * @return int  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	private int addSellRecord(Record r) throws Exception {
		try{
			List upList = new ArrayList();
			List addList = new ArrayList();
			Record temp = new Record();
			temp.setSellall(0);
			temp.setUserName(r.getUserName());
			List<Record> records = recordDao.queryRecord(temp);//所有没有卖出的
			double notSell = 0;//未卖重量
			Record addR = null;
			for(Record tempR : records){
				if(Math.abs(r.getGram()-0)<0.00001){//要卖质量为0
					break;
				}
				notSell = tempR.getGram() - tempR.getSellgram();
				if(notSell - r.getGram() > 0.00001){//未卖质量>要卖质量，全卖
					//更新
					tempR.setSellgram(tempR.getSellgram() + r.getGram());
					tempR.setSelltime(r.getTime());
					tempR.setProfit(tempR.getProfit() + r.getGram() * (r.getPrice()-tempR.getPrice()));
					upList.add(tempR);
					//新增
					addR = new Record();
					addR.setGram(r.getGram());
					addR.setPrice(r.getPrice());
					addR.setTime(r.getTime());
					addR.setAmount(r.getGram() * r.getPrice());
					addR.setBuyid(tempR.getId());
					addR.setProfit(r.getGram() * (r.getPrice()-tempR.getPrice()));
					r.setGram(0);
					addR.setUserName(r.getUserName());
					addList.add(addR);
					break;
				}else{//未卖质量<=要卖质量，部分卖
					//更新
					tempR.setSellgram(tempR.getSellgram() + notSell);
					tempR.setSelltime(r.getTime());
					tempR.setProfit(tempR.getProfit() + notSell * (r.getPrice()-tempR.getPrice()));
					tempR.setSellall(1);//全卖
					upList.add(tempR);
					//新增
					addR = new Record();
					addR.setGram(notSell);
					addR.setPrice(r.getPrice());
					addR.setTime(r.getTime());
					addR.setAmount(notSell * r.getPrice());
					addR.setBuyid(tempR.getId());
					addR.setProfit(notSell * (r.getPrice()-tempR.getPrice()));
					addR.setUserName(r.getUserName());
					r.setGram(r.getGram() - notSell);
					addList.add(addR);
				}
			}
			if(r.getGram() -0 > 0.00001){//还有剩下的
				addR = new Record();
				addR.setGram(r.getGram());
				addR.setPrice(r.getPrice());
				addR.setTime(r.getTime());
				addR.setAmount(r.getGram() * r.getPrice());
				addR.setProfit(r.getGram() * r.getPrice());
				addR.setUserName(r.getUserName());
				addList.add(addR);
			}
			recordDao.batchUpdateBuyRecord(upList);
			recordDao.batchAddSellRecord(addList);
			return 1;
		}catch(Exception e){
			log.error("addSellRecord异常！", e);
			throw e;
		}
	}
	/**
	 * @Description:分析 
	 * @param @param record
	 * @param @return
	 * @param @throws Exception   
	 * @return Map  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public Map analyse(Record record) throws Exception{
		record.setSellall(0);
		List profitList = new ArrayList();
		List lossList = new ArrayList();
		List<Record> records = recordDao.queryRecordForAnalyse(record);
		double profit = 0;
		double gram = 0;
		double amount = 0;
		double profit2 = 0;
		double gram2 = 0;
		double amount2 = 0;
		double notSellG = 0;
		for(Record r : records){
			notSellG = r.getGram() - r.getSellgram();
			if(r.getPrice()<record.getPrice()){//盈利部分
				gram += notSellG;
				amount += notSellG * record.getPrice();
				profit += notSellG * (record.getPrice() - r.getPrice());
				profitList.add(r);
			}else{//不盈利部分
				gram2 += notSellG;
				amount2 += notSellG * record.getPrice();
				profit2 += notSellG * (record.getPrice() - r.getPrice());
				lossList.add(r);
			}
		}
		Map res = new HashMap();
		//盈利部分
		res.put("gram", gram);
		res.put("amount", amount);
		res.put("profit", profit);
		res.put("list", profitList);
		//不盈利部分
		res.put("gram2", gram2);
		res.put("amount2", amount2);
		res.put("profit2", profit2);
		res.put("lossList", lossList);
		return res;
	}
	public List getRecords(Page page,Properties p) throws Exception{
		return recordDao.getRecords(page, p);
	}
}