package com.au.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.au.entity.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @Description:判断用户是否存在 
	 * @param @param user
	 * @param @return
	 * @param @throws Exception   
	 * @return boolean  true：存在，false：不存在
	 * @throws
	 * @author Panyk
	 * @date 2016年7月28日
	 */
	public boolean isUserExist(User user) throws Exception{
		String sql = "select count(*) from user where name=? and pw=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{user.getName(), user.getPw()}, Integer.class)>0;
	}
	/**
	 * @Description:判断一个用户名是否存在 
	 * @param @param user
	 * @param @return
	 * @param @throws Exception   
	 * @return boolean  
	 * @throws
	 * @author Panyk
	 * @date 2016年7月28日
	 */
	public boolean isNameExist(User user) throws Exception{
		String sql = "select count(*) from user where name=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{user.getName() }, Integer.class)>0;
	}
}
