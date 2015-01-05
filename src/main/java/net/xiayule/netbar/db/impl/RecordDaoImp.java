package net.xiayule.netbar.db.impl;


import net.xiayule.netbar.db.JdbcManager;
import net.xiayule.netbar.db.RecordDao;
import net.xiayule.netbar.entity.Record;
import net.xiayule.netbar.utils.Utils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

public class RecordDaoImp implements RecordDao {
	private JdbcTemplate jtl;
	CardDaoImp cdi = new CardDaoImp();
	ComputerDaoImp cpdi = new ComputerDaoImp();

	public RecordDaoImp() {
		jtl = JdbcManager.getJdbctemplate();

	}


/*	//登入时，插入一条记录
	@Override
	public void  insert(String cardid,String computerid,Calendar begintime) {
		String sql = "select max(recordid) from record";
		Object o = jtl.queryForObject(sql, Integer.class);
		int recordid = 1;
		if(o != null) {
			recordid = Integer.parseInt(o.toString())+1;
		}
		sql = "insert into record(recordid,cardid,computerid,begintime) values (?,?,?,?)";
		Object[] params = new Object[]{recordid,cardid,computerid, Utils.dealCalendar(begintime)};
		jtl.update(sql, params);
	}*/

	// 返回记录对象 -- Record
	/*@Override
	public void update(String computerid, Calendar endtime) {
		
		String sql = "update record set endtime = ?, fee = ? where recordid = ? ";
		double fee = Utils.cal(getRecord(computerid).getBegintime(),endtime);
		Object[] params = new Object[]{Utils.dealCalendar(endtime),
				fee,
				getRecord(computerid).getRecordid()};
		jtl.update(sql, params);
	}*/

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
	/*@Override
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
/*	//删除记录
	@Override
	public void deleteReCord(String cardid) {
		String sql = "delete from record where cardid = ?";
		Object[] params = new Object[] {cardid};
		jtl.update(sql,params);
	}
	*/

}
