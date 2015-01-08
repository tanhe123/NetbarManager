package net.xiayule.netbar.db.impl;


import com.sun.org.apache.xpath.internal.operations.Bool;
import net.xiayule.netbar.db.CardDao;
import net.xiayule.netbar.db.JdbcManager;
import net.xiayule.netbar.entity.Card;
import net.xiayule.netbar.utils.Utils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CardDaoImp implements CardDao {
	private JdbcTemplate jtl; 

	public CardDaoImp() {
		this(JdbcManager.getJdbctemplate());
	}

	public CardDaoImp(JdbcTemplate jtl) {
		this.jtl = jtl;
	}

	//新建卡
	@Override
	public void insertCard(Card card) {
		String sql = "insert into card(username, password, balance) values(?,?,?)";
		Object[] params = new Object[] {card.getUsername(),card.getPassword(),
				card.getBalance()};
		jtl.update(sql,params);
	}

	//充值
	@Override
	public void chargeCard(Integer cardid, double fee) {
		String sql = "select balance from card where cardid = ?";
		Object[] params = new Object[] {cardid};
		double balance = jtl.queryForInt(sql,params);
		sql = "update card set balance = ? where cardid = ?";
		params = new Object[] {balance+fee,cardid};
		jtl.update(sql, params);
	}

	//注销卡
	@Override
	public void deleteCard(Integer cardid) {
		String sql = "delete from card where cardid = ?";
		Object[] params = new Object[] {cardid};
		jtl.update(sql,params);
	}

	/**
	 * 充值
	 */
	public void rechargeCard(String username, Double balance) {
		String sql = "update card set balance = balance + ? where username=?";
		Object[] params = new Object[] {balance, username};
		jtl.update(sql, params);
	}
	
	//根据上机状态 查询Card组
/*	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Card> searchCardByState(int state) {
		String sql = "select * from card where state = ?";
		return (ArrayList<Card>) jtl.query(sql, new Object[]{state}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return new Card(rs.getInt("cardid"),rs.getString("username"),
						rs.getString("password"),rs.getDouble("balance"),rs.getInt("state"));
			}
		});
	}*/
	
	//根据卡号判断状态
	@Override
	public boolean isONorOFF(String username) {
		String sql = "select state from card where username = ?";
		Object[] params = new Object[]{username};
		int state = jtl.queryForInt(sql, params);
		return state == 1 ? true : false;
	}
	
	//更改卡状态
	@Override
	public void updateState(Integer cardid,int state) {
		String sql = "update card set state= ?  where cardid = ?";
		Object[] params = new Object[]{state,cardid};
		jtl.update(sql, params);
	}
	
	//验证卡号，密码和上机状态
	@Override
	public boolean verify(String username, String password) {
		//todo: count(*)
		String sql = "select * from card where username = ?";
		Object[] params = new Object[]{username};
		Map map = jtl.queryForMap(sql,params);
		if (map==null) {
			Utils.showDialog("卡号错误或为空"); return  false;}
		if (!map.get("PASSWORD").equals(password)) {Utils.showDialog("密码错误或为空"); return false;}
		if (isONorOFF(username))  {Utils.showDialog("该卡已经上机"); return  false;}
		Utils.showDialog("上机成功");
		return true;
	}

	//查询余额
	@Override
	public double getBalanceByCardid(Integer cardid) {
		String sql = "select balance from card where cardid = ?";
		Object[] params =  new Object[]{cardid};
//		Object o = jtl.queryForObject(sql,new Object[]{cardid});
		Object o = jtl.queryForObject(sql,BigDecimal.class, new Object[]{cardid});
		return ((BigDecimal)o).doubleValue();
	}

//	public static void main(String[] args) {
//		CardDaoImp cdi = new CardDaoImp();
//		double a = cdi.getBalanceByCardid("CARD001");
//		System.out.println(a);
//	}

	//改变卡余额
	@Override
	public void UpdateBalanceByCard(Integer cardid, double balance) {
		String sql = "update card set balance = ? where cardid = ?";
		Object[] params = new Object[]{balance,cardid};
		jtl.update(sql, params);
	}

	/**
	 * 检测卡号是否已经存在
	 */
	public Boolean exist(String username){
		String sql = "select count(*) from card where username = ?";
		System.out.println(username);
		Object [] params = new Object[]{username};
		return jtl.queryForInt(sql, params) != 0;
	}

	public static void main(String[] args) {
		CardDaoImp cdi = new CardDaoImp();

		Card card = new Card("tan贺", "622", 3.14);
		cdi.insertCard(card);

		System.out.println(cdi.exist("tan贺"));
	}
}
