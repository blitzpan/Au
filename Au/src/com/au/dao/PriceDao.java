package com.au.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
	public int addPrice(final List<Price> p) throws Exception{
		String sql = "insert into price(id,ename,cname,zxj,kpj,zgj,zdj,zdf,zsj,gxsj) "
				+ "values(replace(uuid(),'-',''),?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Price price = p.get(i);
				ps.setString(1, price.getEname());
				ps.setString(2, price.getCname());
				ps.setDouble(3, price.getZxj());
				ps.setDouble(4, price.getKpj());
				ps.setDouble(5, price.getZgj());
				ps.setDouble(6, price.getZdj());
				ps.setDouble(7, price.getZdf());
				ps.setDouble(8, price.getZsj());
				ps.setString(9, price.getGxsj());
			}
			@Override
			public int getBatchSize() {
				return p.size();
			}
		});
		return 0;
	}
}
