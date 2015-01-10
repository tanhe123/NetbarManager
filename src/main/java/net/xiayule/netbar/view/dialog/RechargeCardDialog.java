package net.xiayule.netbar.view.dialog;

import net.xiayule.netbar.db.dao.CardDao;
import net.xiayule.netbar.db.dao.impl.CardDaoImp;
import net.xiayule.netbar.db.entity.Card;
import net.xiayule.netbar.utils.BoxUtils;
import net.xiayule.netbar.utils.ComponentUtils;
import net.xiayule.netbar.utils.Utils;
import net.xiayule.netbar.utils.ViewUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechargeCardDialog extends JDialog {

	private JLabel nameLabel = new JLabel("账户: ");
	private JLabel balanceLabel = new JLabel("金额: ");

	private JTextField userNameText = ComponentUtils.createJTextField();
	private JTextField balanceText = ComponentUtils.createJTextField();

	private JButton submit = new JButton("确定");

	private JPanel mainPanel = new JPanel();

	CardDao cardDao = new CardDaoImp();

	public RechargeCardDialog(JFrame frame) {
		super(frame, "充值--网吧计费系统", true);
		this.init();
		this.addComponent();
		this.addListener();
	}

	private void init() {
		this.setResizable(false);
		setSize(400, 200);
		ViewUtils.center(this);
	}

	private void addComponent() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);

		mainPanel.add(BoxUtils.createVerticalStrut(40));

		// 账户
		Box nameBox = BoxUtils.createHorizontalBox();
		nameBox.add(nameLabel);
		nameBox.add(userNameText);

		mainPanel.add(nameBox);
		mainPanel.add(BoxUtils.createVerticalStrut());

		// 金额
		Box balanceBox = BoxUtils.createHorizontalBox();
		balanceBox.add(balanceLabel);
		balanceBox.add(balanceText);
		mainPanel.add(balanceBox);

		mainPanel.add(BoxUtils.createVerticalStrut());

		// 按钮
		Box btnBox = BoxUtils.createHorizontalBox();
		btnBox.add(submit);

		mainPanel.add(btnBox);

		mainPanel.add(BoxUtils.createVerticalStrut());
	}

	// 充值
	private void addListener() {
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userNameText.getText();
				double balance = Double.parseDouble(balanceText.getText());

				Card card = new Card();
				card.setUsername(username);
				card.setBalance(balance);

				if (cardDao.exist(userNameText.getText().trim())) {
					cardDao.rechargeCard(username, balance);
					// todo: 添加log
					Utils.showDialog("充值成功");
					RechargeCardDialog.this.dispose();
				} else { // 如果不存在用户
					Utils.showDialog("没有指定该用户");
//					RechargeCardDialog.this.dispose();
				}
			}
		});
	}
}
