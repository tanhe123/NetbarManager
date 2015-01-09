package net.xiayule.netbar.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tan on 15-1-9.
 */
public class TimeUtils {

    //Calendar类型日期数据 转换为String类型日期
    public static String formateCalendar(Calendar c) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String date = sf.format(c.getTime());

        return date;
    }
}
