package net.xiayule.netbar.entity;

public class Card {
	private Integer cardid;
	private String username;
	private String password;
	private double balance;
	/**
	 * 记录所上的电脑的 id
 	 */
	private int state;

	public Card() {
		this(0, null, null, 0, 0);
	}

	public Card(String username, String password, double balance,int state) {
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.state = state;
	}

	public Card(Integer cardid, String username, String password, double balance,int state) {
		this.cardid = cardid;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.state = state;
	}

	public Integer getCardid() {
		return cardid;
	}

	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String toString() {
		return cardid + " " + username + " " + password + " " + balance;
	}


}
