package net.xiayule.netbar.entity;

public class Computer {
	private Integer computerid;
	private int state;
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
