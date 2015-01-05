package net.xiayule.netbar.view;

import net.xiayule.netbar.utils.Utils;
import net.xiayule.netbar.view.dialog.CreateCardDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tan on 15-1-5.
 */
public class MainFrame extends JFrame {
    private JMenuBar menuBar = new JMenuBar();

    // todo: 操作(上机，下级)

    private JMenu cardMenu = new JMenu("卡管理");

    //todo: 计算机管理


    //todo: 查询与统计



    // 卡管理
    private JMenuItem cardAdd = new JMenuItem("新增卡");
    private JMenuItem cardRecharge = new JMenuItem("充值会员");

    //todo 查询信息 {余额和，注销, 启用卡}



    public MainFrame() {
        this.init();
        this.addComponent();
        this.addListener();
    }

    private void addListener() {
        // 会员卡相关
        cardAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new CreateCardDialog(MainFrame.this);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });

        //充值
        cardRecharge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //todo: 改成充值会员
                JDialog dialog = new CreateCardDialog(MainFrame.this);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });



    }

    private void addComponent() {
        this.setJMenuBar(menuBar);

        menuBar.add(cardMenu);

        cardMenu.add(cardAdd);
        cardMenu.add(cardRecharge);


    }

    private void init() {
        setSize(500, 600);
        Utils.center(this);
        setTitle("网吧计费系统");
    }

    private void initComponents() {

    }
}
