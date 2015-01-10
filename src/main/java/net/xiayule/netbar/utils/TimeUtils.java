package net.xiayule.netbar.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tan on 15-1-9.
 */
public class TimeUtils {

    //Calendar类型日期数据 转换为String类型日期
    public static String formateCalendar(Calendar c) {
        // HH 表示24小时制， hh 表示12小时制
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String date = sf.format(c.getTime());

        return date;
    }

    public static String miliSecondToMinite(long miliSecond) {
        DecimalFormat df = new DecimalFormat("0.00");

        double minite = miliSecond / 1000.0 / 60;

        return df.format(minite);
    }
}
