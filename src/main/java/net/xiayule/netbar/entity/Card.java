package net.xiayule.netbar.entity;

public class Card {
	private String cardid;
	private String username;
	private String password;
	private double balance;
	private int state;

	public Card() {
		this("", null, null, 0,0);
	}

	public Card(String cardid, String username, String password, double balance,int state) {
		this.cardid = cardid;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.state = state;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
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
