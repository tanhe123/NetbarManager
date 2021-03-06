package net.xiayule.netbar.view.dialog;

import net.xiayule.netbar.db.dao.CardDao;
import net.xiayule.netbar.db.dao.ComputerDao;
import net.xiayule.netbar.db.dao.RecordDao;
import net.xiayule.netbar.db.dao.impl.CardDaoImp;
import net.xiayule.netbar.db.dao.impl.ComputerDaoImp;
import net.xiayule.netbar.db.dao.impl.RecordDaoImp;
import net.xiayule.netbar.utils.*;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * Created by tan on 15-1-8.
 */
public class ComputerOnDialog extends JDialog {

    JLabel usernameLabel = new JLabel("用户: ");
    JLabel passwordLabel = new JLabel("密码: ");

    private JTextField usernameText = ComponentUtils.createJTextField();
    private JTextField passwordText = ComponentUtils.createJpasswordField();

    private JPanel mainPanel = new JPanel();

    private JButton submit = new JButton("确定");

    private CardDao cardDao = new CardDaoImp();
    private ComputerDao computerDao = new ComputerDaoImp();
    private RecordDao recordDao = new RecordDaoImp();

    private final Integer computerId;

    public ComputerOnDialog(JFrame frame, Integer computerId) {
        super(frame, "上机--网吧计费系统", true);
        this.computerId = computerId;
        this.init();
        this.addComponent();
        this.addListener();
    }

    private void addListener() {
        submit.addActionListener(e -> {
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

            if (!cardDao.verify(username, password)) {
                Utils.showDialog("用户名或密码错误");
                return;
            }

            // 是否还有余额
            Double balance = cardDao.getBalance(username);
            if (balance < 0.005) {
                Utils.showDialog("余额不足，请充值");
                return;
            }

            // 获得 userid
            Integer userId = cardDao.getUserId(username);

            // 检查是否已在其他机器上上机
            Integer onComputerId = computerDao.queryForComputerId(userId);

            if (onComputerId != 0) {
                Utils.showDialog("该用户已经在" + onComputerId + "号机上机");
                return;
            }

            // 更新电脑状态
            computerDao.update(computerId, userId);

            recordDao.insert(userId, computerId, Calendar.getInstance());

            this.dispose();
        });
    }

    private void addComponent() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);

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

        // 按钮
        Box btnBox = BoxUtils.createHorizontalBox();
        btnBox.add(submit);
        mainPanel.add(btnBox);

        mainPanel.add(BoxUtils.createVerticalStrut());
    }

    private void init() {
        this.setResizable(false);
        setSize(300, 200);
        ViewUtils.center(this);
    }

}
