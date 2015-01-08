package net.xiayule.netbar.view;

import net.xiayule.netbar.db.ComputerDao;
import net.xiayule.netbar.db.impl.ComputerDaoImp;
import net.xiayule.netbar.domain.ComputerModel;
import net.xiayule.netbar.utils.Utils;
import net.xiayule.netbar.view.dialog.CreateCardDialog;
import net.xiayule.netbar.view.dialog.RechargeCardDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tan on 15-1-5.
 */
public class MainFrame extends JFrame {
    private JMenuBar menuBar = new JMenuBar();

    // todo: 操作(上机，下级)
    private JMenu computerMenu = new JMenu("机器管理");
    private JMenu cardMenu = new JMenu("会员");

    // 计算机管理
    private JMenuItem computerAdd = new JMenuItem("新增机器");
    private JMenuItem computerRemove = new JMenuItem("删除机器");
    private JMenuItem computerAlter = new JMenuItem("修改机器");

    // 会员管理
    private JMenuItem cardAdd = new JMenuItem("新会员");
    private JMenuItem cardRecharge = new JMenuItem("充值会员");

    private ComputerDao computerDao = new ComputerDaoImp();

    // 组件
    JPanel computerPanel = new JPanel();

    //todo: 查询与统计

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
                JDialog dialog = new RechargeCardDialog(MainFrame.this);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
            }
        });
    }

    private void addComponent() {
        this.setJMenuBar(menuBar);

        menuBar.add(computerMenu);
        menuBar.add(cardMenu);

        // 机器管理菜单
        computerMenu.add(computerAdd);
        computerMenu.add(computerAlter);
        computerMenu.add(computerRemove);

        // 会员管理菜单
        cardMenu.add(cardAdd);
        cardMenu.add(cardRecharge);
    }

    private void init() {
        setSize(500, 600);
        Utils.center(this);
        setTitle("网吧计费系统");

        initComponents();
        setListener();
    }

    private void setListener() {
        computerDao.getComputer();
    }

    private void initComponents() {
        getContentPane().add(getTableScrollPanel(), BorderLayout.CENTER);

    }

    public JScrollPane getTableScrollPanel() {
        String[] columnNames = {"机号","状态", "上机人"};   //列名

        String [][]tableVales={{"A1","B1"},{"A2","B2"},{"A3","B3"},{"A4","B4"},{"A5","B5"}}; //数据
        TableModel tableModel = new DefaultTableModel(tableVales,columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(new ComputerModel());
        // 只能选中单行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }
}
