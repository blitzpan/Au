package com.au.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.au.thread.GatherThread;

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
		
	}

}
