package net.xiayule.netbar.entity;

public class Computer {
	private String computerid;
	private int state;
	public Computer() {
	}
	public Computer(String computerid, int state) {
		this.computerid = computerid;
		this.state = state;
	}
	public String getComputerid() {
		return computerid;
	}
	public void setComputerid(String computerid) {
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
