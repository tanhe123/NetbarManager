package net.xiayule.netbar.view.dialog;

import net.xiayule.netbar.db.dao.ComputerDao;
import net.xiayule.netbar.db.dao.impl.ComputerDaoImp;
import net.xiayule.netbar.utils.Utils;
import net.xiayule.netbar.utils.ViewUtils;
import net.xiayule.netbar.view.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tan on 15-1-10.
 */
public class ComputerAddDialog extends JDialog {
    JLabel numberLabel = new JLabel("增加的数量: ");
    JSpinner numberSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));

    JButton submit = new JButton("增加");

    private ComputerDao computerDao = new ComputerDaoImp();

    public ComputerAddDialog(JFrame frame) {
        super(frame, true);
        this.init();
        this.addComponent();
        this.addListener();
    }

    private void init() {
        setTitle("新增机器--网吧计费系统");
        this.setResizable(false);
        setSize(400, 100);
        ViewUtils.center(this);
    }

    private void addComponent() {
        this.getContentPane().add(getMainPanel());
    }

    public JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();

        mainPanel.add(numberLabel);
        mainPanel.add(numberSpinner);

        mainPanel.add(submit);

        return mainPanel;
    }

    private void addListener() {
        submit.addActionListener(e -> {
            Integer num = (Integer)numberSpinner.getModel().getValue();

            for (int i=0; i<num; i++) {
                computerDao.insertComputer();
            }

            Utils.showDialog("成功添加" + num + "台机器");

            this.dispose();
        });


    }
}
