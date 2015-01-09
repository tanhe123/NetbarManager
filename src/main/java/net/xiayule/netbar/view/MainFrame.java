package net.xiayule.netbar.view;

import net.xiayule.netbar.db.CardDao;
import net.xiayule.netbar.db.ComputerDao;
import net.xiayule.netbar.db.RecordDao;
import net.xiayule.netbar.db.impl.CardDaoImp;
import net.xiayule.netbar.db.impl.ComputerDaoImp;
import net.xiayule.netbar.db.impl.RecordDaoImp;
import net.xiayule.netbar.domain.ComputerModel;
import net.xiayule.netbar.domain.ComputerRow;
import net.xiayule.netbar.utils.TimeUtils;
import net.xiayule.netbar.utils.Utils;
import net.xiayule.netbar.utils.ViewUtils;
import net.xiayule.netbar.view.dialog.ComputerOnDialog;
import net.xiayule.netbar.view.dialog.CreateCardDialog;
import net.xiayule.netbar.view.dialog.RechargeCardDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

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
    JPanel computerPanel = new JPanel();

    private JTable table;
    private ComputerModel computerModel;

    //todo: 查询与统计

    //todo 查询信息 {余额和，注销, 启用卡}

    public MainFrame() {
        this.init();
        this.addComponent();
        this.addListener();
    }

    /**
     * 刷新table数据信息，并显示
     */
    public void refresh() {
        computerModel.refresh();
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
    }

    private void init() {
        setSize(500, 600);
        ViewUtils.center(this);
        setTitle("网吧计费系统");

        initComponents();
    }

    private void initComponents() {
        getContentPane().add(getTableScrollPanel(), BorderLayout.CENTER);

    }

    public JScrollPane getTableScrollPanel() {
        table = new JTable();
        computerModel = new ComputerModel(table);
        table.setModel(computerModel);

        // 只能选中单行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    private void addListener() {
        // 操作相关

        // 上机
        commondOn.addActionListener(e ->{
            Integer index = table.getSelectedRow();

            if (index < 0) {
                Utils.showDialog("请选定一个机器上机");
                return;
            }

            ComputerRow computerRow = computerModel.getComputerRows().get(index);

            if (computerRow.getState().equals(ComputerRow.STATUS_ON)) {
                Utils.showDialog("请选择一个空闲的机器");
                return;
            }

            JDialog dialog = new ComputerOnDialog(MainFrame.this, computerRow.getId());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            refresh();
        });

        commondOff.addActionListener(e -> {
            Integer index = table.getSelectedRow();

            if (index < 0) {
                Utils.showDialog("请选定一个机器上机");
                return;
            }

            ComputerRow computerRow = computerModel.getComputerRows().get(index);

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

            refresh();

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

            refresh();
        });

        //充值
        cardRecharge.addActionListener(e -> {
            JDialog dialog = new RechargeCardDialog(MainFrame.this);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

            refresh();
        });
    }
}
