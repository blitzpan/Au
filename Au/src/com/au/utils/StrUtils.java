package com.au.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.au.entity.User;

import sun.misc.BASE64Encoder;

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
	public static String generatePw(User user){
		String res = "";
		try{
			res = md5(user.getName() + user.getPw());
			res.substring(8, 16);
			res = md5(res);
		}catch(Exception e){
			res = "error";
		}
		return res;
	}
	private static String md5(String str) throws Exception{
		//确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        //加密后的字符串
        byte[] resBytes = md5.digest(str.getBytes("utf-8"));
        return new String(new BASE64Encoder().encode(resBytes));//这一行是我自己编的，不知道正常的是什么样
	}
	public static void main(String[] args) {
		User u = new User("1028353676", "pjyikv");
		String res = generatePw(u);
		System.out.println(res);
	}
}