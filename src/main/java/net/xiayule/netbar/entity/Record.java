package net.xiayule.netbar.entity;

import net.xiayule.netbar.utils.Utils;

import java.util.Calendar;

public class Record {
	private int recordid;
	private String cardid;
	private String computerid;
	private Calendar begintime;
	private Calendar endtime;
	private double fee;
	public Record() {
	}
	public Record(int recordid, String cardid, String computerid,
			Calendar begintime, Calendar endtime, int fee) {
		this.recordid = recordid;
		this.cardid = cardid;
		this.computerid = computerid;
		this.begintime = begintime;
		this.endtime = endtime;
		this.fee = fee;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getComputerid() {
		return computerid;
	}
	public void setComputerid(String computerid) {
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
		
		return cardid +" " + computerid +" " + Utils.dealCalendar(begintime) + " " +Utils.dealCalendar(endtime) + " " +fee;
	}

}
