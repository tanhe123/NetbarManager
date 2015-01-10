package net.xiayule.netbar.db.dao.impl;


import net.xiayule.netbar.db.dao.ComputerDao;
import net.xiayule.netbar.db.dao.JdbcManager;
import net.xiayule.netbar.domain.ComputerRow;
import net.xiayule.netbar.db.entity.Computer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputerDaoImp implements ComputerDao {
	private JdbcTemplate jtl;
	

	public ComputerDaoImp() {
		jtl = JdbcManager.getJdbctemplate();
	}

	////根据状态查询Computer组
	@SuppressWarnings("unchecked")
	public ArrayList<Computer> getComputerByState(int state) {
		String sql = "select * from computer where state = ?"; 
		return (ArrayList<Computer>) jtl.query(sql, new Object[]{state}, new RowMapper<Computer>() {
				public Computer mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Computer(rs.getInt("computerid"),rs.getInt("state"));
				}
		});
	}

	public ArrayList<Computer> getComputer() {
		String sql = "select * from computer";
		return (ArrayList<Computer>) jtl.query(sql, new RowMapper<Computer>() {
			@Override
			public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Computer(rs.getInt("computerid"), rs.getInt("state"));
			}
		});
	}
	
	//更改机器状态
	public void update(Integer computerid, int state) {
		String sql = "update computer set state = ? where computerid = ?";
		Object[] params = new Object[]{state,computerid};
		jtl.update(sql, params);
	}

	/**
	 * 查看指定用户所上的机器
	 * @param cardid
	 * @return 如果没有上机，则返回0
	 */
	public Integer queryForComputerId(Integer cardid) {
		String sql = "select count(computerid) from computer where state=?";

		Object[] params = new Object[]{cardid};

		Integer count = jtl.queryForObject(sql, params, Integer.class);
		if (count == 0) {
			return 0;
		}

		sql = "select computerid from computer where state=?";
		return jtl.queryForObject(sql, params, Integer.class);
	}

	//根据机器号判断该机器状态
	@Override
	public boolean isONorOFF(Integer computerid) {
		String sql = "select state from computer where computerid = ?";
		Object[] params = new Object[]{computerid};
		int state = jtl.queryForInt(sql, params);
		return state == 1 ? true : false;
	}

	/**
	 * 获取将要在表中显示的信息
	 */
	public List<ComputerRow> queryComputerRows() {
		String sql = "select computerid, computer.state, username, balance from computer " +
				"left join card on computer.state=card.cardid";

		return (List<ComputerRow>) jtl.query(sql, new RowMapper<ComputerRow>() {
			public ComputerRow mapRow(ResultSet rs, int arg1) throws SQLException {
				return new ComputerRow(rs.getInt("computerid"),
						rs.getInt("state") == 0 ? ComputerRow.STATUS_OFF : ComputerRow.STATUS_ON,
						rs.getString("username"), rs.getDouble("balance"));
			}
		});
	}

	public static void main(String[] args) {
		ComputerDaoImp computerDaoImp = new ComputerDaoImp();
		System.out.println(computerDaoImp.isONorOFF(1));
	}
}
