package net.xiayule.netbar.db.entity;

public class Card {
	private Integer cardid;
	private String username;
	private String password;
	private double balance;

	public Card() {
		this(0, null, null, 0);
	}

	public Card(String username, String password, double balance) {
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public Card(Integer cardid, String username, String password, double balance) {
		this.cardid = cardid;
		this.username = username;
		this.password = password;
		this.balance = balance;
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

	public String toString() {
		return cardid + " " + username + " " + password + " " + balance;
	}


}
