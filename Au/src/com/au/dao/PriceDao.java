package com.au.dao;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.au.entity.Price;

@Repository
public class PriceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @Description:新增 
	 * @param @return
	 * @param @throws Exception   
	 * @return int  
	 * @throws
	 * @author Panyk
	 * @date 2016年1月5日
	 */
	public int addPrice(Price p) throws Exception{
		String sql = "";
		Vector values = new Vector();
		values.add(0);
		return jdbcTemplate.update(sql, values);
	}
}
