package net.xiayule.netbar.db.dao.impl;


import net.xiayule.netbar.db.dao.JdbcManager;
import net.xiayule.netbar.db.dao.RecordDao;

import net.xiayule.netbar.db.entity.Record;
import net.xiayule.netbar.utils.TimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sun.nio.cs.US_ASCII;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecordDaoImp implements RecordDao {
	private JdbcTemplate jtl;
	CardDaoImp cdi = new CardDaoImp();
	ComputerDaoImp cpdi = new ComputerDaoImp();

	public RecordDaoImp() {
		jtl = JdbcManager.getJdbctemplate();

	}

	/**
	 * 	登入时，插入一条记录
	 */
	@Override
	public void insert(Integer cardid, Integer computerid, Calendar begintime) {
		String sql = "insert into record(cardid,computerid,begintime) values (?,?,?)";
		Object[] params = new Object[]{cardid,computerid, TimeUtils.formateCalendar(begintime)};
		jtl.update(sql, params);
	}

	public Calendar queryBeginTime(Integer computerId) {
		String sql = "select begintime from record where computerid = ? and endtime is null";
		Object[] params = new Object[]{computerId};
		Date beginTime = jtl.queryForObject(sql, params, Date.class);

		Calendar c =  Calendar.getInstance();
		c.setTime(beginTime);

		return c;
	}

	/**
	 * 设置指定机器的下机时间
	 * 该机器的endtime之前一定没有被设置
	 * @param computerid
	 * @param endtime
	 */
	@Override
	public void update(Integer computerid, Double fee, Calendar endtime) {
		//todo:
		String sql = "update record set endtime = ?, fee = ? where computerid = ? and endtime is null";
		//todo: 计算费用
		Object[] params = new Object[]{TimeUtils.formateCalendar(endtime), fee, computerid};
		jtl.update(sql, params);
	}


	public List<Record> queryRecordByUsername(String username) {

		String sql = "select recordid, computerid, begintime, endtime, fee from record, card " +
				"where record.cardid=card.cardid and username='" + username + "'";

		return (List<Record>) jtl.query(sql, new RowMapper<Record>() {
			@Override
			public Record mapRow(ResultSet rs, int rowNum) throws SQLException {

				Integer recordId = rs.getInt("recordid");
				Integer computerId = rs.getInt("computerid");

				Calendar beginTime =  Calendar.getInstance();
				beginTime.setTime(rs.getTimestamp("begintime"));

				Calendar endTime =  Calendar.getInstance();
				endTime.setTime(rs.getTimestamp("endtime"));

				Double fee = rs.getDouble("fee");

				return new Record(recordId,
						computerId,
						beginTime,
						endTime,
						fee);
			}
		});
	}

	//删除记录
	@Override
	public void deleteReCord(String cardid) {
		String sql = "delete from record where cardid = ?";
		Object[] params = new Object[] {cardid};
		jtl.update(sql,params);
	}

}
