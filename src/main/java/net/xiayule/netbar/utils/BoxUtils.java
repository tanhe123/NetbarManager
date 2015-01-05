package net.xiayule.netbar.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tan on 15-1-5.
 */
public class BoxUtils {
    private static final int DEFAULT_STRUT_SIZE = 10;

    public static Box createVerticalBox() {
        Box b = Box.createVerticalBox();

        return b;
    }

    public static Box createHorizontalBox() {
        Box b = Box.createHorizontalBox();

        return b;
    }

    public static Component createHorizontalStrut() {
        return Box.createHorizontalStrut(DEFAULT_STRUT_SIZE);
    }

    public static Component createVerticalStrut() {
        return createVerticalStrut(DEFAULT_STRUT_SIZE);
    }

    public static Component createVerticalStrut(int height) {
        return Box.createVerticalStrut(height);
    }
}
