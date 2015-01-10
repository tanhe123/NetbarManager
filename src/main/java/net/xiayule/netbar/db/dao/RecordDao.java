package net.xiayule.netbar.db.dao;

import net.xiayule.netbar.db.entity.Record;

import java.util.Calendar;
import java.util.List;

public interface RecordDao {
	public void insert(Integer cardid, Integer computerid, Calendar begintime);
	public void update(Integer computerid, Double fee, Calendar endtime);
//	public Record getRecord(String computerid);
	public void deleteReCord(String cardid);
	public Calendar queryBeginTime(Integer computerId);
	public List<Record> queryRecordByUsername(String username);
}
