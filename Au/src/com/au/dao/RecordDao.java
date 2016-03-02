package com.au.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.au.entity.Record;
import com.au.utils.StrUtils;

@Repository
public class RecordDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @Description:新增买入记录 
	 * @param @param record
	 * @param @return
	 * @param @throws Exception   
	 * @return int  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public int addBuyRecord(Record record) throws Exception{
		String sql = "insert into buyrecord(id,gram,price,time,amount) values(REPLACE(uuid(),'-',''),?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{record.getGram(),record.getPrice(),record.getTime(),record.getAmount()});
	}
	/**
	 * @Description:查询满足条件的记录 
	 * @param @param record
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Record>  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public List<Record> queryRecord(Record record) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT * from buyrecord where 1=1");
		Vector values = new Vector();
		if(record.getSellall()!=-1){//-1是没有任何意义的。
			sql.append(" and sellall=" + record.getSellall());
		}
		sql.append(" ORDER BY price asc");
		return jdbcTemplate.query(sql.toString(), values.toArray(), new BeanPropertyRowMapper(Record.class));
	}
	/**
	 * @Description:批量更新买入数据 
	 * @param @param rs
	 * @param @return
	 * @param @throws Exception   
	 * @return   
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public void batchUpdateBuyRecord(final List<Record> rs) throws Exception{
		String sql = "update buyrecord set sellgram=?,selltime=?,profit=?,sellall=? where id=?";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Record r = rs.get(i);
				ps.setDouble(1, r.getSellgram());
				ps.setString(2, r.getSelltime());
				ps.setDouble(3, r.getProfit());
				ps.setInt(4, r.getSellall());
				ps.setString(5, r.getId());
			}
			@Override
			public int getBatchSize() {
				return rs.size();
			}
		});
	}
	/**
	 * @Description:批量插入卖出数据 
	 * @param @param rs
	 * @param @throws Exception   
	 * @return void  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public void batchAddSellRecord(final List<Record> rs) throws Exception{
		final Date time = StrUtils.str2Date(rs.get(0).getTime());
		String sql = "insert into sellRecord(id,gram,price,amount,buyid,time,profit) values(REPLACE(uuid(),'-',''),?,?,?,?,?,?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Record r = rs.get(i);
				ps.setDouble(1, r.getGram());
				ps.setDouble(2, r.getPrice());
				ps.setDouble(3, r.getAmount());
				ps.setString(4, r.getBuyid());
				ps.setString(5, r.getTime());
				ps.setDouble(6, r.getProfit());
			}
			@Override
			public int getBatchSize() {
				return rs.size();
			}
		});
	}
	/**
	 * @Description:价格分析查询所有符合条件记录 
	 * @param @param record
	 * @param @return
	 * @param @throws Exception   
	 * @return List<Record>  
	 * @throws
	 * @author Panyk
	 * @date 2016年3月2日
	 */
	public List<Record> queryRecordForAnalyse(Record record) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT * from buyrecord where 1=1");
		Vector values = new Vector();
		if(record.getSellall()!=-1){//-1是没有任何意义的。
			sql.append(" and sellall=" + record.getSellall());
		}
		if(record.getPrice()-0>0.00001){//找出所有价格小于当前价格的
			sql.append(" and price<?");
			values.add(record.getPrice());
		}
		sql.append(" ORDER BY price asc");
		return jdbcTemplate.query(sql.toString(), values.toArray(), new BeanPropertyRowMapper(Record.class));
	}
}
