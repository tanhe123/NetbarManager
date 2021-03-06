package net.xiayule.netbar.db.dao;


import net.xiayule.netbar.db.entity.Card;

public interface CardDao {
	public void insertCard(Card card);
	public void rechargeCard(String username, Double balance);
	public Integer getUserId(String username);
	public void chargeCard(Integer cardid, double fee);
	public void deleteCard(Integer cardid);
//	public ArrayList<Card> searchCardByState(int state);
	public boolean isONorOFF(String username);
	public void updateState(Integer cardid, int state);
	public boolean verify(String username, String password);
	public double getBalanceByCardid(Integer cardid);
	public void UpdateBalanceByCard(Integer cardid, double balance);
	public Boolean exist(String username);
	public Double getBalance(String username);
	public void subchargeCard(String username, Double balance);

}
