package net.xiayule.netbar.utils;

import java.awt.*;

/**
 * Created by tan on 15-1-8.
 */
public class ViewUtils {
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
}
