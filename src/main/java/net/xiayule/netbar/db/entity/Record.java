package net.xiayule.netbar.db.entity;

import net.xiayule.netbar.utils.TimeUtils;

import java.util.Calendar;

public class Record {
	private Integer recordid;
	private Integer cardid;
	private Integer computerid;
	private Calendar begintime;
	private Calendar endtime;
	private double fee;
	public Record() {

	}
	public Record(Integer recordid, Integer cardid, Integer computerid,
			Calendar begintime, Calendar endtime, Double fee) {
		this.recordid = recordid;
		this.cardid = cardid;
		this.computerid = computerid;
		this.begintime = begintime;
		this.endtime = endtime;
		this.fee = fee;
	}

	public Record(Integer recordid, Integer computerid,
				  Calendar begintime, Calendar endtime, Double fee) {
		this(recordid, null, computerid, begintime, endtime, fee);
	}

	public int getRecordid() {
		return recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}

	public Integer getComputerid() {
		return computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public Calendar getBegintime() {
		return begintime;
	}
	public void setBegintime(Calendar begintime) {
		this.begintime = begintime;
	}
	public Calendar getEndtime() {
		return endtime;
	}
	public void setEndtime(Calendar endtime) {
		this.endtime = endtime;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public String toString() {
		return cardid +" " + computerid +" " + TimeUtils.formateCalendar(begintime) + " " +TimeUtils.formateCalendar(endtime) + " " +fee;
	}

}
