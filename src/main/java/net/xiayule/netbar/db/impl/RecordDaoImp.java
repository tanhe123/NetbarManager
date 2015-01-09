package net.xiayule.netbar.db.impl;


import net.xiayule.netbar.db.JdbcManager;
import net.xiayule.netbar.db.RecordDao;

import net.xiayule.netbar.utils.TimeUtils;
import net.xiayule.netbar.utils.Utils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Calendar;
import java.util.Date;

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
		Date endTime = jtl.queryForObject(sql, params, Date.class);

		Calendar c =  Calendar.getInstance();
		c.setTime(endTime);

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



	//通过记录号查找记录
	/*public Record getRecordByRecordid(int recordid) {
		String sql = "select * from record where recordid = ? ";
		Object[] params = new Object[]{recordid};
		Map map = jtl.queryForMap(sql, params);
		if (map==null) return null;
		Record record = new Record();
		record.setRecordid(((BigDecimal)map.get("RECORDID")).intValue());
		record.setCardid((String)map.get("CARDID"));
		record.setComputerid((String)map.get("COMPUTERID"));
		record.setBegintime(Utils.recoveryCalendar((String)map.get("BEGINTIME")));
		record.setEndtime(Utils.recoveryCalendar((String)map.get("ENDTIME")));
		record.setFee(((BigDecimal)map.get("FEE")).doubleValue());
		return record;
	}*/

	//通过机器号来查记录
/*	@Override
	public Record getRecord(String computerid) {
		String sql = "select * from record where computerid = ? and endtime is null";
		Object[] params = new Object[]{computerid};
		Map map = jtl.queryForMap(sql,params);
		
		if (map==null) return null;
		Record record = new Record();
		record.setRecordid(((BigDecimal)map.get("RECORDID")).intValue());
		record.setCardid((String)map.get("CARDID"));
		record.setComputerid((String)map.get("COMPUTERID"));
		record.setBegintime(Utils.recoveryCalendar((String)map.get("BEGINTIME")));
		return record;
	}*/

	//删除记录
	@Override
	public void deleteReCord(String cardid) {
		String sql = "delete from record where cardid = ?";
		Object[] params = new Object[] {cardid};
		jtl.update(sql,params);
	}

}
