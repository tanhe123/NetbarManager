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
				// 获取输入的用户名
				String username = userNameText.getText();

				// 获取输入的金额，并转换成double类型
				double balance = Double.parseDouble(balanceText.getText());

				if (cardDao.exist(username)) { // 要确保该用户存在
					// 充值，写入数据库
					cardDao.rechargeCard(username, balance);
					Utils.showDialog("充值成功");
					RechargeCardDialog.this.dispose();
				} else { // 如果不存在用户
					Utils.showDialog("没有指定该用户");
				}
			}
		});
	}
}
