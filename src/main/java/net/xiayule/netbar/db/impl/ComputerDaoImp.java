package net.xiayule.netbar.db.impl;


import net.xiayule.netbar.db.ComputerDao;
import net.xiayule.netbar.db.JdbcManager;
import net.xiayule.netbar.entity.Computer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComputerDaoImp implements ComputerDao {
	private JdbcTemplate jtl;
	

	public ComputerDaoImp() {
		jtl = JdbcManager.getJdbctemplate();
	}

	////根据状态查询Computer组
	@SuppressWarnings("unchecked")
	public ArrayList<Computer> getComputerByState(int state) {
		String sql = "select * from computer where state = ?"; 
		return (ArrayList<Computer>) jtl.query(sql, new Object[]{state}, new RowMapper() {
				public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Computer(rs.getInt("computerid"),rs.getInt("state"));
				}
		});
	}
	
	//更改机器状态
	public void update(Integer computerid, int state) {
		String sql = "update computer set state = ? where computerid = ?";
		Object[] params = new Object[]{state,computerid};
		jtl.update(sql, params);
	}

	//根据机器号判断该机器状态
	@Override
	public boolean isONorOFF(Integer computerid) {
		String sql = "select state from computer where computerid = ?";
		Object[] params = new Object[]{computerid};
		int state = jtl.queryForInt(sql, params);
		return state == 1 ? true : false;
	}

	public static void main(String[] args) {
		ComputerDaoImp computerDaoImp = new ComputerDaoImp();
		System.out.println(computerDaoImp.isONorOFF(1));
	}
}
