package net.xiayule.netbar.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Utils {

	/**
	 * 根据下机时间和上机时间计算费用, 不够一小时，算一小时
	 */
	public static double calc(Calendar begintime, Calendar endtime) {
		return Math.ceil((endtime.getTimeInMillis() - begintime
				.getTimeInMillis()) / 1000.0 / 60 / 60)*2;
	}
	
	//显示警告对话框
	public  static void showDialog(String s){
		JOptionPane.showMessageDialog(null, s,"警告 ",JOptionPane.WARNING_MESSAGE);//
	};

}
