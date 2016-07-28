package com.au.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.au.entity.Res;
import com.au.entity.User;
import com.au.service.UserService;

@Controller
@RequestMapping(value="/loginCon")
public class LoginController {
	@Autowired
	private UserService userService;
	
	private Logger log = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping(value="/login")
	@ResponseBody
	public Object login(HttpSession session, String name, String pw){
		Res res = new Res();
		try{
			User user = new User(name, pw);
			String[] resArr = userService.login(user);
			if(resArr[0].equals("1")){
				user.setPw(null);
				session.setAttribute("user", user);
				res.setSuccessed("登陆成功！", null);
			}else{
				res.setFailed(resArr[1]);
			}
		}catch(Exception e){
			log.error("login=", e);
			res.setFailed("登陆发生异常！");
		}
		return res;
	}
}
