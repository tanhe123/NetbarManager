package net.xiayule.netbar.domain;

import net.xiayule.netbar.db.dao.ComputerDao;
import net.xiayule.netbar.db.dao.impl.ComputerDaoImp;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by tan on 15-1-8.
 */
public class ComputerModel extends AbstractTableModel {

    private JTable table; // 持有的 table 实例

    /**
     * 表格的列名
     */
    private static final String[] COLUMN_NAMES = {"机号", "状态", "上机人", "余额"};

    /**
     * 表格每一列的类型
     */
    private static final Class<?>[] COLUMN_TYPES = {String.class, String.class, String.class, String.class};

    /**
     * 用来查询计算机相关的信息的dao组件
     */
    private ComputerDao computerDao = new ComputerDaoImp();

    /**
     * 表格的中所有的数据
     */
    private List<ComputerRow> computerRows;

    public ComputerModel(JTable table) {
        this.table = table;
        computerRows = computerDao.queryComputerRows();
    }

    public void refresh() {
        computerRows = computerDao.queryComputerRows();
        table.updateUI();
    }

    /**
     * 返回要显示的行数
     * @return
     */
    @Override
    public int getRowCount() {
        return computerRows == null ? 0 : computerRows.size();
    }

    /**
     * 列的数量
     * @return
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     *获得每一列的名称
     * @param columnIndex
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    /**
     * 获得每一列的类型
     * @param columnIndex
     * @return
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    /**
     * 定制每一个单元格是否可以编辑
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * 获得要显示的数据
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // 取得第 rowIndex 行的数据
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


    public List<ComputerRow> getComputerRows() {
        return computerRows;
    }

    public void setComputerRows(List<ComputerRow> computerRows) {
        this.computerRows = computerRows;
    }
}
