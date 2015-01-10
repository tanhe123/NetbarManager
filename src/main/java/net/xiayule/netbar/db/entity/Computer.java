package net.xiayule.netbar.db.entity;

public class Computer {
	private Integer computerid;

	/**
	 * 0 为空闲，否则为上机人的id
	 */
	private Integer state;
	public Computer() {

	}
	public Computer(Integer computerid, int state) {
		this.computerid = computerid;
		this.state = state;
	}

	public Integer getComputerid() {
		return computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public String toString() {
		return computerid + " : " +state;
	}
}
