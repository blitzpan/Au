package com.au.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.au.entity.Record;
import com.au.entity.Res;
import com.au.service.RecordService;
import com.au.utils.Page;

@Controller
@RequestMapping(value="/recordCon")
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	private Logger log = Logger.getLogger(RecordController.class);
	
	@RequestMapping(value="/addRecord")
	@ResponseBody
	public Object addRecord(HttpSession session,@ModelAttribute("record") Record record) {
		Res res = new Res();
		try{
			log.debug("addRecord=" + record);
			res.setSuccessed("添加记录成功！", recordService.addRecord(record));
		}catch(Exception e){
			log.error("addRecord=", e);
			res.setFailed("程序发生异常！");
		}
		return res;
	}
	@RequestMapping(value="/analyse")
	@ResponseBody
	public Object analyse(@ModelAttribute("record") Record record){
		Res res = new Res();
		try{
			res.setSuccessed("分析完成！", recordService.analyse(record));
		}catch(Exception e){
			log.error("analyse=", e);
			res.setFailed("程序发生异常！");
		}
		return res;
	}
	/**
	 * @Description:record翻页查询 
	 * @param @param page
	 * @param @param begin
	 * @param @param end
	 * @param @return   
	 * @return Object  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月3日
	 */
	@RequestMapping(value="/getRecords")
	@ResponseBody
	public Object getRecords(Page page,String begin,String end){
		Res res = new Res();
		try{
			Properties p = new Properties();
			p.put("begin", begin);
			p.put("end", end);
			Map resMap = new HashMap();
			resMap.put("list", recordService.getRecords(page,p));
			resMap.put("page", page);
			res.setSuccessed("查询完成！", resMap);
		}catch(Exception e){
			log.error("getRecords=", e);
			res.setFailed("程序发生异常！");
		}
		return res;
	}
}
