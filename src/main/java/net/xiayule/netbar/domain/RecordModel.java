package net.xiayule.netbar.domain;

import net.xiayule.netbar.db.entity.Record;
import net.xiayule.netbar.utils.TimeUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tan on 15-1-10.
 */
public class RecordModel extends AbstractTableModel {

    /**
     * 记录表格的列名
     */
    private static final String[] COLUMN_NAMES = {"机号", "开始时间", "结束时间", "时间总计(分钟)", "费用"};

    /**
     * 表格每一列的类型
     */
    private static final Class<?>[] COLUMN_TYPES = {Integer.class, String.class, String.class, String.class, Double.class};

    /**
     * 要显示的记录的数据数组
     */
    private List<Record> recordList;

    /**
     * talbe实例
     */
    private JTable table;

    public RecordModel(JTable table) {
        this.table = table;
    }

    public RecordModel(JTable table, List<Record> recordList) {
        this(table);
        this.recordList = recordList;
    }

    /**
     * 设置数据列表
     * 调用该函数，就可以改变当前保留的数据
     * @param recordList
     */
    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
        refresh();
    }

    /**
     * 刷新表格显示
     */
    protected void refresh() {
        table.updateUI();

    }

    /**
     * 获得行数
     */
    @Override
    public int getRowCount() {
        return recordList == null ? 0 : recordList.size();
    }

    /**
     * 获得每一列的名称
     */
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    /**
     * 获得列的数量
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * 获得表格每一个单元格的数据
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // 获得rowIndex行的数据
        Record record = recordList.get(rowIndex);

        if (columnIndex == 0) { // 机号
            return record.getComputerid();
        } else if (columnIndex == 1) { // 开始时间
            // 返回格式化后的时间
            return TimeUtils.formateCalendar(record.getBegintime());
        } else if (columnIndex == 2) { // 结束时间
            return TimeUtils.formateCalendar(record.getEndtime());
        } else if (columnIndex == 3) { // 上机时间
            Calendar beiginTime = record.getBegintime();
            Calendar endTime = record.getEndtime();

            long  t = endTime.getTimeInMillis() - beiginTime.getTimeInMillis();

            return TimeUtils.miliSecondToMinite(t);
        } else { // 费用
            return record.getFee();
        }
    }
}
