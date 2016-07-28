package com.au.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.au.dao.UserDao;
import com.au.entity.User;
import com.au.utils.StrUtils;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDao userDao;
	private Logger log = Logger.getLogger(this.getClass());
	
	public String[] login(User u) throws Exception{
		u.setPw(StrUtils.generatePw(u));
		if(userDao.isUserExist(u)){//存在
			return new String[]{"1",""};
		}else{
			if(userDao.isNameExist(u)){//用户名存在
				return new String[]{"0","用户密码错误！"};
			}else{
				return new String[]{"0","用户名不存在！"};
			}
		}
	}
}