package net.xiayule.netbar.db;


import net.xiayule.netbar.entity.Card;

import java.util.ArrayList;

public interface CardDao {
	public void insertCard(Card card);
	public void chargeCard(String cardid, double fee);
	public void deleteCard(String cardid);
	public ArrayList<Card> searchCardByState(int state);
	public boolean isONorOFF(String cardid);
	public void updateState(String cardid, int state);
	public boolean verifyCard(String cardid, String password);
	public double getBalanceByCardid(String cardid);
	public void UpdateBalanceByCard(String cardid, double balance);
	public int presence(String cardid);
}
