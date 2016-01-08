package com.au.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Component
public class SendMailUtils extends Thread{
	@Autowired
	private JavaMailSender mailSender;
	/*
	@Autowired
	private FreeMarkerConfigurer freemarkerConfiguration;
	*/
	private String title;
	private String msg;
	
	
	public SendMailUtils() {
		super();
	}
	@Override
	public void run() {
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 单纯的文本邮件
	 * @return
	 */
	public String sendSimpleMail(String msg) {
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setTo("1028353676@qq.com");// 接受者
			mail.setFrom("youxiangformajia@163.com");// 发送者,和xml中的一致
			mail.setSubject(title);// 主题
			mail.setText(msg);// 邮件内容
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "a";
	}
	/**
	 * html邮件
	 * @throws Exception
	 */
	/*
	public void sendHtmlMail() throws Exception{
		// 建立邮件消息,发送简单邮件和html邮件的区别  
        MimeMessage mailMessage = mailSender.createMimeMessage();  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"GBK");  
        
        messageHelper.setTo("1028353676@qq.com");// 接受者
        messageHelper.setFrom("youxiangformajia@163.com");// 发送者,和xml中的一致
        messageHelper.setSubject("邮件测试");// 主题  
        
        // true 表示启动HTML格式的邮件  
        messageHelper  
                .setText(  
                        "<html><head>	<title>html邮件测试</title></head><body>	<center><h1>html邮件测试</h1></center>	<div id='content'>		<p style='text-indent: 2em;'>			网友袁大宸名叫袁晨，是安徽淮南师范学院外国语学院的大三学生。9月8日早上8点左右，她在校门口扶起了一位摔倒的老太，看到情况严重就给她拨了120，结果被老人家属要求负全责。		</p>		<p style='text-indent: 2em;'>“淮南女大学生称扶老太被讹”的目击者已经出面向淮南警方说明情况，通过个人微博首度向社会公开她所看到的一幕并公布了现场照片。　目击者说，当时她和室友去淮南师范学院吃饭，与和小袁对向而行。事情发生之前，她看到老人走路不稳，一崴一崴的。后来，小袁骑车从老太身边经过，但不知道什么原因，老太就倒地了。当时小袁骑车已经过去了，老太又喊住小袁，小袁把车子停下，将老太扶着坐了起来。她当时犹豫是不是过去，后来还是过去了，和小袁一起帮老人揉腿。　目击者明确说，小袁骑车从老太身边经过时，两者之间是有距离的，且不足以撞倒老人，她并没有看到老人身上有擦伤之类的，老人被抬上120急救车前，一直是坐在地上的。</br>		</p>	</div>	<div id='adDiv'>		<img src='http://www.p5w.net/money/xfsh/201509/W020150911358035928505.png' width='300' height='300'/>		<a href='http://www.p5w.net/money/xfsh/201509/t20150911_1191590.htm' target='_blank'>查看原文</a>	</div></body></html>",  
                        true);  
        // 发送邮件  
        mailSender.send(mailMessage);  
  
        System.out.println("邮件发送成功..");  
	}
	*/
	/**
	 * 通过模板来发送邮件
	 * @return
	 */
	/*
	public void sendTemplateMail() throws Exception{
		if(mailSender == null){
			//不用再加载springContext.xml文件,因为在web.xml中配置了,在程序中启动是就有了。
			mailSender = (JavaMailSender) SpringContextUtil.getBean("mailSender");  
		}
		// 建立邮件消息,发送简单邮件和html邮件的区别  
        MimeMessage mailMessage = mailSender.createMimeMessage();  
        //这里一定要是gbk，如果是utf-8的话，会出现乱码问题
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"gbk");
        
//        messageHelper.setTo("1028353676@qq.com");// 接受者
        String[] toUsers = new String[ubL.size()];
        int i=0;
        for(UserBook ub: ubL){
        	toUsers[i++] = ub.getEmail(); 
        }
        messageHelper.setTo(toUsers);//接受者
        messageHelper.setFrom("youxiangformajia@163.com");// 发送者,和xml中的一致
        messageHelper.setSubject("邮件测试");// 主题  
        
        // true 表示启动HTML格式的邮件  
        messageHelper.setText(getEmailContent(), true);
        // 发送邮件  
        mailSender.send(mailMessage);
        System.out.println("邮件发送成功..");  
	}
	
	private String getEmailContent() throws Exception{
		try {
			if(freemarkerConfiguration == null){
				freemarkerConfiguration = (FreeMarkerConfigurer) SpringContextUtil.getBean("freemarkerConfiguration");  
			}
			Template template = freemarkerConfiguration.getConfiguration().getTemplate("mail.ftl");

			Map<String, String> map = new HashMap<String, String>();
			map.put("fromName", "<a href=\"http://www.baidu.com\" target=\"_blank\">神的首页</a>");
			map.put("sendTime", new Date().toString());
			map.put("content", book.getContent());
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			return content;

		} catch (TemplateException e) {
			System.out.println("Error while processing FreeMarker template ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Error while open template file ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while generate Email Content ");
			e.printStackTrace();
		}
		return "";
	}
*/
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
