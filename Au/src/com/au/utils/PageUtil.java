package com.au.utils;

public class PageUtil {
	public static String appendCount(String sql) throws Exception{
		StringBuffer sb = new StringBuffer("SELECT count(*) from (").append(sql).append(")t");
		return sb.toString();
	}
	public static String appendPage(Page page, String sql) throws Exception {
		if(page == null){
			page = new Page();
		}
		StringBuffer sb = new StringBuffer("SELECT * from (").append(sql);
		sb.append(")t LIMIT ").append(page.getStart()).append(",").append(page.getPageSize());
		return sb.toString();
	}
}
