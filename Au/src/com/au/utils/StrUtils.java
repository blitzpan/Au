package com.au.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StrUtils {
	public static double str2Double(String str){
		if(str == null || str.trim().length()==0){
			return 0;
		}
		double res = 0;
		try{
			res = Double.parseDouble(str);
		}catch(Exception e){
			res = 0;
		}
		return res;
	}
	public static Date str2Date(String str) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.parse(str);
	}
}
