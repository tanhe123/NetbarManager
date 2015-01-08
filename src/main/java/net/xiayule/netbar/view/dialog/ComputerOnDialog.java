package net.xiayule.netbar.view.dialog;

import net.xiayule.netbar.utils.BoxUtils;
import net.xiayule.netbar.utils.ComponentUtils;

import javax.swing.*;
import java.awt.*;

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

    public ComputerOnDialog(JFrame frame) {
        super(frame, "上机--网吧计费系统", true);
        this.init();
        this.addComponent();

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
    }


}
