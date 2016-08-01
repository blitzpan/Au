package com.au.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.au.thread.DownThread;
import com.au.thread.GatherThread;
import com.au.thread.MailSendThread;
import com.au.thread.UpThread;

/**
 * ClassName: StartListener 
 * @Description:参考文章：http://www.cnblogs.com/rollenholt/p/3612440.html 
 * @author Panyk
 * @date 2016年7月28日
 */
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent cre) {
		//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
		GatherThread gt = new GatherThread();
		gt.start();
		//发送邮件线程
		MailSendThread mst = new MailSendThread();
		mst.start();
		//下跌线程
		DownThread dt = new DownThread();
		dt.start();
		//上涨线程
		UpThread ut = new UpThread();
		ut.start();
	}

}
