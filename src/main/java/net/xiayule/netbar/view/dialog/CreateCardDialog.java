package net.xiayule.netbar.view.dialog;

import net.xiayule.netbar.db.CardDao;
import net.xiayule.netbar.db.impl.CardDaoImp;
import net.xiayule.netbar.entity.Card;
import net.xiayule.netbar.utils.*;
import sun.swing.StringUIClientPropertyKey;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCardDialog extends JDialog {

//	private JLabel numberLabel = new JLabel("卡号：");
	private JLabel usernameLabel = new JLabel("账户: ");
	private JLabel passwordLabel = new JLabel("密码: ");
	private JLabel balanceLabel = new JLabel("金额: ");
	private JLabel stateLabel = new JLabel("状态: ");

//	private JTextField cardidText = new JTextField(11);
	private JTextField usernameText = ComponentUtils.createJTextField();
	private JTextField passwordText = ComponentUtils.createJTextField();
	private JTextField balanceText = ComponentUtils.createJTextField();
	private JTextField stateText = ComponentUtils.createJTextField();

	private JButton submit = new JButton("确定");
	private JButton afresh = new JButton("重置");

	private JPanel mainPanel = new JPanel();

	CardDao cardDao = new CardDaoImp();
//	ComputerDaoImp cpdi = new ComputerDaoImp();
//	RecordDaoImp rdi = new RecordDaoImp();

	public CreateCardDialog(JFrame frame) {
		super(frame, "新会员--网吧计费系统", true);
		this.init();
		this.addComponent();
//		this.pack();
		this.addListener();
	}

	private void init() {
		this.setResizable(false);
		setSize(400, 400);
		ViewUtils.center(this);
	}

	private void addComponent() {

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

//		mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);

//		mainPanel.setBorder(BorderFactory.createTitledBorder("新的会员:"));

		mainPanel.add(BoxUtils.createVerticalStrut(40));

		// 姓名
		Box usernameBox = BoxUtils.createHorizontalBox();
		usernameBox.add(usernameLabel);
		usernameBox.add(usernameText);

		mainPanel.add(usernameBox);
		mainPanel.add(BoxUtils.createVerticalStrut());

		// 密码
		Box passBox = BoxUtils.createHorizontalBox();
		passBox.add(passwordLabel);
		passBox.add(passwordText);
		mainPanel.add(passBox);

		mainPanel.add(BoxUtils.createVerticalStrut());

		// 金额
		Box balanceBox = BoxUtils.createHorizontalBox();
		balanceBox.add(balanceLabel);
		balanceBox.add(balanceText);
		mainPanel.add(balanceBox);

		mainPanel.add(BoxUtils.createVerticalStrut());

		/*// 状态
		Box stateBox= BoxUtils.createHorizontalBox();
		stateBox.add(stateLabel);
		stateBox.add(stateText);
		mainPanel.add(stateBox);

		mainPanel.add(BoxUtils.createVerticalStrut(40));*/


		// 按钮
		Box btnBox = BoxUtils.createHorizontalBox();
		btnBox.add(submit);
		btnBox.add(BoxUtils.createHorizontalStrut());
		btnBox.add(afresh);
		mainPanel.add(btnBox);

		mainPanel.add(BoxUtils.createVerticalStrut());
	}

	// 重置
	private void addListener() {
		afresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordText.setText(null);
				usernameText.setText(null);
				balanceText.setText(null);
				stateText.setText(null);
			}
		});
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameText.getText();
				if (!StringUtils.hasText(username)) {
					Utils.showDialog("用户名不能为空");
					return;
				}
				String password = passwordText.getText();
				if (!StringUtils.hasText(password)) {
					Utils.showDialog("密码不能为空");
					return;
				}

				if (!StringUtils.hasText(balanceText.getText())) {
					Utils.showDialog("金额不能为空");
					return;
				}
				double balance = Double.parseDouble(balanceText.getText());

				Card card = new Card();
				card.setPassword(password);
				card.setUsername(username);
				card.setBalance(balance);
//				card.setState(state);

				if (!cardDao.exist(usernameText.getText())) { // 如果不存在用户
					//todo: 记录 log
//					rdi.deleteReCord(card.getCardid());
					cardDao.insertCard(card);
					Utils.showDialog("创建成功");
					CreateCardDialog.this.dispose();
				} else {
					Utils.showDialog("会员名已存在");
					return;
//					CreateCardDialog.this.dispose();
				}
			}
		});
	}
}
