package net.xiayule.netbar.domain;

import com.oracle.deploy.update.UpdateCheck;
import net.xiayule.netbar.db.ComputerDao;
import net.xiayule.netbar.db.impl.ComputerDaoImp;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by tan on 15-1-8.
 */
public class ComputerModel extends AbstractTableModel {

    private JTable table; // 持有的 table 实例

    private static final String[] COLUMN_NAMES = {"机号", "状态", "上机人", "余额"};
    private static final Class<?>[] COLUMN_TYPES = {Integer.class, Integer.class, String.class, Double.class};

    private ComputerDao computerDao = new ComputerDaoImp();

    private List<ComputerRow> computerRows;

    public ComputerModel(JTable table) {
        this.table = table;
        computerRows = computerDao.queryComputerRows();
    }

    public void refresh() {
        computerRows = computerDao.queryComputerRows();
        table.updateUI();
    }

    @Override
    public int getRowCount() {
        return computerRows == null ? 0 : computerRows.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ComputerRow computerRow = computerRows.get(rowIndex);

        if (columnIndex == 0) {
            return computerRow.getId();
        } else if (columnIndex == 1) {
            return computerRow.getState();
        } else if (columnIndex == 2) {
            return computerRow.getUsername();
        } else if (columnIndex == 3) {
            return computerRow.getBalance();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //todo: 待补充
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public List<ComputerRow> getComputerRows() {
        return computerRows;
    }

    public void setComputerRows(List<ComputerRow> computerRows) {
        this.computerRows = computerRows;
    }
}
