package net.xiayule.netbar.view;

import com.sun.corba.se.impl.logging.UtilSystemException;
import net.xiayule.netbar.db.dao.CardDao;
import net.xiayule.netbar.db.dao.ComputerDao;
import net.xiayule.netbar.db.dao.RecordDao;
import net.xiayule.netbar.db.dao.impl.CardDaoImp;
import net.xiayule.netbar.db.dao.impl.ComputerDaoImp;
import net.xiayule.netbar.db.dao.impl.RecordDaoImp;
import net.xiayule.netbar.db.entity.Record;
import net.xiayule.netbar.domain.ComputerModel;
import net.xiayule.netbar.domain.ComputerRow;
import net.xiayule.netbar.domain.RecordModel;
import net.xiayule.netbar.utils.*;
import net.xiayule.netbar.view.dialog.ComputerAddDialog;
import net.xiayule.netbar.view.dialog.ComputerOnDialog;
import net.xiayule.netbar.view.dialog.CreateCardDialog;
import net.xiayule.netbar.view.dialog.RechargeCardDialog;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tan on 15-1-5.
 */
public class MainFrame extends JFrame {
    private JMenuBar menuBar = new JMenuBar();

    private JMenu commondMenu = new JMenu("操作");
    private JMenu computerMenu = new JMenu("机器管理");
    private JMenu cardMenu = new JMenu("会员");

    // 操作
    private JMenuItem commondOn = new JMenuItem("上机");
    private JMenuItem commondOff = new JMenuItem("下机");

    // 计算机管理
    private JMenuItem computerAdd = new JMenuItem("新增机器");
    private JMenuItem computerRemove = new JMenuItem("删除机器");
    private JMenuItem computerAlter = new JMenuItem("修改机器");

    // 会员管理
    private JMenuItem cardAdd = new JMenuItem("新会员");
    private JMenuItem cardRecharge = new JMenuItem("充值会员");

    private ComputerDao computerDao = new ComputerDaoImp();
    private RecordDao recordDao = new RecordDaoImp();
    private CardDao cardDao = new CardDaoImp();

    // 组件
    private JTable computerMgrTable;
    private JTable recordMgrTable;
    private ComputerModel computerMgrModel;
    private RecordModel recordMgrModel;

    private JLabel usernameLabel = new JLabel("用户名: ");
    private JTextField usernameText = ComponentUtils.createJTextField();

    private JButton searchSubmit = new JButton("提交");
    private Component recordTablePanel;

    //todo: 查询与统计

    //todo 查询信息 {余额和，注销, 启用卡}

    public MainFrame() {
        this.init();
        this.addComponent();
        this.addListener();
    }

    private void init() {
        setSize(800, 600);
        ViewUtils.center(this);
        setTitle("网吧计费系统");
    }

    private void addComponent() {
        this.setJMenuBar(menuBar);

        menuBar.add(commondMenu);
        menuBar.add(computerMenu);
        menuBar.add(cardMenu);

        // 操作菜单
        commondMenu.add(commondOn);
        commondMenu.add(commondOff);

        // 机器管理菜单
        computerMenu.add(computerAdd);
        computerMenu.add(computerAlter);
        computerMenu.add(computerRemove);

        // 会员管理菜单
        cardMenu.add(cardAdd);
        cardMenu.add(cardRecharge);

        getContentPane().add(getJTabbedPane(), BorderLayout.CENTER);
    }

    /**
     * 标签面板
     */
    public JTabbedPane getJTabbedPane() {

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("上机管理", getComputerMgrScrollPanel());

        tabbedPane.setSelectedIndex(0);

        tabbedPane.add("记录管理", getRecordMgrPanel());

        return tabbedPane;
    }

    /**
     * 记录标签页面板
     *
     * @return
     */
    public JPanel getRecordMgrPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 添加搜索面板
        mainPanel.add(getRecordSearchPanel(), BorderLayout.NORTH);
        mainPanel.add(getRecordMgrScrollPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    /**
     * 记录标签页中的搜索面板
     * @return
     */
    public JPanel getRecordSearchPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(BoxUtils.createVerticalStrut(40));

        // 姓名
        Box usernameBox = BoxUtils.createHorizontalBox();
        usernameBox.add(usernameLabel);
        usernameBox.add(usernameText);

        panel.add(usernameBox);
        panel.add(BoxUtils.createVerticalStrut());

        panel.add(BoxUtils.createHorizontalStrut());

        // 按钮
        Box btnBox = BoxUtils.createHorizontalBox();
        btnBox.add(searchSubmit);
        panel.add(btnBox);

        panel.add(BoxUtils.createVerticalStrut());

        return panel;
    }

    public JScrollPane getRecordMgrScrollPanel() {
        recordMgrTable = new JTable();
        recordMgrModel = new RecordModel(recordMgrTable);
        recordMgrTable.setModel(recordMgrModel);

        // 只能选中单行
        recordMgrTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(recordMgrTable);

        return scrollPane;
    }

    /**
     * 显示上机下机信息表格
     */
    public JScrollPane getComputerMgrScrollPanel() {
        computerMgrTable = new JTable();
        computerMgrModel = new ComputerModel(computerMgrTable);
        computerMgrTable.setModel(computerMgrModel);

        // 只能选中单行
        computerMgrTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(computerMgrTable);
        return scrollPane;
    }

    private void addListener() {
        // 操作相关

        // 计算机相关

        computerAdd.addActionListener(e -> {
            JDialog dialog = new ComputerAddDialog(MainFrame.this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            refreshComputerMgrModel();
        });

        // 上机
        commondOn.addActionListener(e ->{
            Integer index = computerMgrTable.getSelectedRow();

            if (index < 0) {
                Utils.showDialog("请选定一个机器上机");
                return;
            }

            ComputerRow computerRow = computerMgrModel.getComputerRows().get(index);

            if (computerRow.getState().equals(ComputerRow.STATUS_ON)) {
                Utils.showDialog("请选择一个空闲的机器");
                return;
            }

            JDialog dialog = new ComputerOnDialog(MainFrame.this, computerRow.getId());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            refreshComputerMgrModel();
        });

        commondOff.addActionListener(e -> {
            Integer index = computerMgrTable.getSelectedRow();

            if (index < 0) {
                Utils.showDialog("请选定一个机器上机");
                return;
            }

            ComputerRow computerRow = computerMgrModel.getComputerRows().get(index);

            Integer computerId = computerRow.getId();

            // 下机
            computerDao.update(computerId, 0);

            // 计算费用
            Calendar beginTime = recordDao.queryBeginTime(computerId);
            Calendar endTime = Calendar.getInstance();
            Double fee = Utils.calc(beginTime, endTime);

            // 记录下机时间和费用
            recordDao.update(computerId, fee, endTime);

            // 减去费用
            String username = computerRow.getUsername().trim();
            cardDao.subchargeCard(username, fee);

            refreshComputerMgrModel();

            // 显示详细信息
            Utils.showDialog(computerRow.getUsername() + " 下机成功\n"
                    + "上机时间: " + TimeUtils.formateCalendar(beginTime) + "\n"
                    + "下机时间: " + TimeUtils.formateCalendar(endTime) + "\n"
                    + "费用合计: " + fee);
        });

        // 会员卡相关
        cardAdd.addActionListener(e -> {
            JDialog dialog = new CreateCardDialog(MainFrame.this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            refreshComputerMgrModel();
        });

        //充值
        cardRecharge.addActionListener(e -> {
            JDialog dialog = new RechargeCardDialog(MainFrame.this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            refreshComputerMgrModel();
        });

        // 记录查询提交按钮
        searchSubmit.addActionListener(e -> {
            String username = usernameText.getText();

            if (!StringUtils.hasText(username)) {
                Utils.showDialog("用户名不能为空");
                return;
            }

            if (!cardDao.exist(username)) {
                Utils.showDialog("您要检索的用户不存在");
                return;
            }

            List<Record> recordList = recordDao.queryRecordByUsername(username);

            // 填入数据，并显示
            recordMgrModel.setRecordList(recordList);
        });
    }


    /**
     * 刷新table数据信息，并显示
     */
    public void refreshComputerMgrModel() {
        computerMgrModel.refresh();
    }


}
