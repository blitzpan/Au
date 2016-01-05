package com.au.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.au.entity.Constent;
import com.au.entity.Price;

@Component
public class Gather {
	private Logger log = Logger.getLogger(this.getClass());

	public List<Price> gather() {
		List res = null;
		try {
			// 创建HttpClient实例
			HttpClient httpclient = new DefaultHttpClient();
			// 创建Get方法实例
			HttpGet httpgets = new HttpGet("http://hq.sinajs.cn/rn=9pl6s&list=" + Constent.gatherList);
			HttpResponse response = httpclient.execute(httpgets);
			HttpEntity entity = response.getEntity();
			String str = "";
			if (entity != null) {
				InputStream instreams = entity.getContent();
				str = convertStreamToString(instreams);
				log.debug("get=" + str);
				httpgets.abort();
			}
			res = this.str2Prices(str);
		} catch (Exception e) {
			log.error("gather", e);
		}
		return res;
	}

	public String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			log.error("convertStreamToString error.e=", e);
			throw e;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				log.error("convertStreamToString error.e=", e);
			}
		}
		return sb.toString();
	}
	private List str2Prices(String str) throws Exception{
		List res = new ArrayList();
		if(str != null || str.trim().length()>0){
			Pattern p=Pattern.compile("\"(.*?)\"");
            Matcher m=p.matcher(str);
            String tempStr = "";
            String[] strArr = null;
            Price price = null;
            while(m.find()){
            	tempStr = m.group();
                log.debug(tempStr);
                tempStr = tempStr.substring(1, tempStr.length()-1);
                log.debug(tempStr);
                strArr = tempStr.split(",");
                price = new Price();
                price.setEname(strArr[0]);
                price.setCname(strArr[1]);
                
            }
		}
		return res;
	}
	public static void main(String[] args) {
		Gather g = new Gather();
		g.gather();
	}
}
