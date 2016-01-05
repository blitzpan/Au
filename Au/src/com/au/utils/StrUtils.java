package com.au.utils;

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
}
