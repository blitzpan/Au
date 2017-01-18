package com.au.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.au.entity.Constent;
import com.au.entity.Price;

@Component
@Scope("prototype")
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "gb2312"));
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
            String zdf = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while(m.find()){
            	tempStr = m.group();
                log.debug(tempStr);
                tempStr = tempStr.substring(1, tempStr.length()-1);
                log.debug(tempStr);
                strArr = tempStr.split(",");
                price = new Price();
                price.setEname(strArr[0]);
                price.setCname(strArr[1]);
                price.setZxj(StrUtils.str2Double(strArr[3]));
                price.setKpj(StrUtils.str2Double(strArr[6]));
                price.setZgj(StrUtils.str2Double(strArr[7]));
                price.setZdj(StrUtils.str2Double(strArr[8]));
                zdf = strArr[17];
                if(zdf.endsWith("%")){
                	zdf = zdf.substring(0, zdf.length()-1);
                }
                price.setZdf(StrUtils.str2Double(zdf));
                price.setZsj(StrUtils.str2Double(strArr[5]));
                
                price.setGxsj(sdf.parse(strArr[16]));
                //当最新价不为0的时候入库
                if(price.getZxj() - 0 > 0.0001){
                	res.add(price);
                }
            }
		}
		return res;
	}
	public static void main(String[] args) {
		Gather g = new Gather();
		g.gather();
	}
}
