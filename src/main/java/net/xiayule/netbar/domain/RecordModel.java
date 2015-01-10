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

    private static final String[] COLUMN_NAMES = {"机号", "开始时间", "结束时间", "时间总计(分钟)", "费用"};
    private static final Class<?>[] COLUMN_TYPES = {Integer.class, String.class, String.class, String.class, Double.class};

    private List<Record> recordList;
    private JTable table;

    public RecordModel(JTable table) {
        this.table = table;
    }

    public RecordModel(JTable table, List<Record> recordList) {
        this(table);
        this.recordList = recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
        refresh();
    }

    protected void refresh() {
        table.updateUI();
    }

    @Override
    public int getRowCount() {
        return recordList == null ? 0 : recordList.size();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record record = recordList.get(rowIndex);

        if (columnIndex == 0) { // 机号
            return record.getComputerid();
        } else if (columnIndex == 1) { // 开始时间
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
