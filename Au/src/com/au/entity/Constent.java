package com.au.entity;

import java.util.concurrent.LinkedBlockingQueue;

public class Constent {
	public final static String gatherList = "SGE_AU99_99";
	public final static int downCount = 5;//下降分析
	public final static int up_upCount = 3;//上升的上升阶段
	public final static int up_downCount = 3;//上升的下降阶段
	
	public static String upSendTime;//发送上涨邮件的时间
	public static String downSendTime;//发送下跌邮件的时间
	
	public static long DOWN_SLEEP = 1000*60*8;//sleep 8 min
	public static long UP_SLEEP = 1000*60*8;//sleep 8 min
	public static LinkedBlockingQueue<Mail> MAIL_QUEUE = new LinkedBlockingQueue<Mail>();//邮件待发送队列
	public static String MAIL_TO = "1028353676@qq.com";
}
