package net.xiayule.netbar.view.dialog;

import net.xiayule.netbar.db.impl.CardDaoImp;
import net.xiayule.netbar.db.impl.ComputerDaoImp;
import net.xiayule.netbar.db.impl.RecordDaoImp;
import net.xiayule.netbar.entity.Card;
import net.xiayule.netbar.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCardDialog extends JDialog {

	private JLabel numberLabel = new JLabel("卡号：");
	private JLabel nameLabel = new JLabel("姓名：");
	private JLabel passwordLabel = new JLabel("密码：");
	private JLabel moneyLabel = new JLabel("金额： ");
	private JLabel stateLabel = new JLabel("状态： ");

	private JTextField cardidText = new JTextField(11);
	private TextField nameText = new TextField(15);
	private JTextField passwordText = new JTextField(11);
	private JTextField moneyText = new JTextField(11);
	private JTextField stateText = new JTextField("0");

	private JButton submit = new JButton("确定");
	private JButton afresh = new JButton("重置");

	private JPanel numberPanel = new JPanel();
	private JPanel namePanel = new JPanel();
	private JPanel passwordPanel = new JPanel();
	private JPanel moneyPanel = new JPanel();
	private JPanel statePanel = new JPanel();
	private JPanel btnPanel = new JPanel();

	CardDaoImp cdi = new CardDaoImp();
	ComputerDaoImp cpdi = new ComputerDaoImp();
	RecordDaoImp rdi = new RecordDaoImp();

	public CreateCardDialog(JFrame frame) {
		super(frame, "网吧计费系统", true);
		this.init();
		this.addComponent();
		this.pack();
		this.addListener();
	}

	private void init() {
		this.setLocation(300, 200);
		this.setLayout(new GridLayout(6, 1));
	}

	private void addComponent() {
		this.add(numberPanel);
		this.add(namePanel);
		this.add(passwordPanel);
		this.add(moneyPanel);
		this.add(statePanel);
		this.add(btnPanel);

		numberPanel.add(numberLabel);
		numberPanel.add(cardidText);

		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);

		namePanel.add(nameLabel);
		namePanel.add(nameText);

		btnPanel.add(submit);
		btnPanel.add(afresh);

		moneyPanel.add(moneyLabel);
		moneyPanel.add(moneyText);

		statePanel.add(stateLabel);
		statePanel.add(stateText);

	}

	// 重置
	private void addListener() {
		afresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardidText.setText(null);
				passwordText.setText(null);
				nameText.setText(null);
				moneyText.setText(null);
				stateText.setText(null);
			}
		});
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String a = cardidText.getText();
				String password = passwordText.getText();
				String username = nameText.getText();
				double balance = Double.parseDouble(moneyText.getText());
				int state = Integer.parseInt(stateText.getText());

				Card card = new Card();
//				card.setCardid(a);
				card.setPassword(password);
				card.setUsername(username);
				card.setBalance(balance);
				card.setState(state);
				if (cdi.presence(nameText.getText()) == 0) {
					//todo:
//					rdi.deleteReCord(card.getCardid());

					cdi.insertCard(card);
					Utils.showDialog("创建成功");
					CreateCardDialog.this.dispose();
				} else {
					Utils.showDialog("创建失败");
					CreateCardDialog.this.dispose();
				}
			}
		});
	}
}
