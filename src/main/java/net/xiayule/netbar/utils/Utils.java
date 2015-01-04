package net.xiayule.netbar.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Utils {
	//使窗口居中
	public static void center(Window win) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int sw = toolkit.getScreenSize().width;
		int sh = toolkit.getScreenSize().height;
		int w = win.getWidth();
		int h = win.getHeight();

		int x = (sw - w) / 2;
		int y = (sh - h) / 2;
		win.setLocation(x, y);
	}
	//根据下机时间和上机时间计算费用
	public static double cal(Calendar begintime, Calendar endtime) {
		return (double) (Math.ceil((endtime.getTimeInMillis() - begintime
				.getTimeInMillis())/1000.0/60/15)*0.5);
	}
	//Calendar类型日期数据 转换为String类型日期
	public static String dealCalendar(Calendar c) {
		String s = "" + c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) < 9 ? 0 : "") + (c.get(Calendar.MONTH)+1)
				+ "-" + (c.get(Calendar.DATE) < 10 ? 0 : "")
				+ c.get(Calendar.DATE) + " "
				+ (c.get(Calendar.HOUR_OF_DAY) < 10 ? 0 : "")
				+ c.get(Calendar.HOUR_OF_DAY) + ":"
				+ (c.get(Calendar.MINUTE) < 10 ? 0 : "")
				+ c.get(Calendar.MINUTE) + ":"
				+ (c.get(Calendar.SECOND) < 10 ? 0 : "")
				+ c.get(Calendar.SECOND);
		return s;
	}

	//String类型日期还原为Calendar类型日期
	public static Calendar recoveryCalendar(String time) {
		Calendar calendar =Calendar.getInstance();
		String[] ss = time.split("[\\D]+");
		int[] is = new int[6];
		for (int i=0;i<ss.length;i++) {
			is[i] = Integer.parseInt(ss[i]);
		}
		calendar.set(is[0], is[1]-1, is[2], is[3], is[4], is[5]);
		return calendar;
	}
	
	
	//显示警告对话框
	public  static void showDialog(String s){
		JOptionPane.showMessageDialog(null, s,"警告 ",JOptionPane.WARNING_MESSAGE);//
	};
}
