package com.au.action;

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
	@RequestMapping(value="/getAllArticle")
	public ModelAndView getAllArticle(Page page,QueryParm qp){
		ModelAndView mv = new ModelAndView();
		Res res = new Res();
		try{
			res.setSuccessed("翻页查询成功！", articleService.getAllArticle(page, qp));
			System.out.println(res.getRes());
		}catch(Exception e){
			res.setFailed("翻页查询异常！");
			log.error("getAllArticle", e);
		}
		mv.setViewName("/junxun/public/articleList");
		mv.addObject("res",res);
		mv.addObject("page", page);
		return mv;
	}
}
