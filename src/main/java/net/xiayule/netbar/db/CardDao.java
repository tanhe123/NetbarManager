package net.xiayule.netbar.db;


import net.xiayule.netbar.entity.Card;

import java.util.ArrayList;

public interface CardDao {
	public void insertCard(Card card);
	public void chargeCard(Integer cardid, double fee);
	public void deleteCard(Integer cardid);
	public ArrayList<Card> searchCardByState(int state);
	public boolean isONorOFF(Integer cardid);
	public void updateState(Integer cardid, int state);
	public boolean verifyCard(Integer cardid, String password);
	public double getBalanceByCardid(Integer cardid);
	public void UpdateBalanceByCard(Integer cardid, double balance);
	public int presence(String username);
}
