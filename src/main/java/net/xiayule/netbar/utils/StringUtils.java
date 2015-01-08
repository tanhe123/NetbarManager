package net.xiayule.netbar.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by tan on 15-1-8.
 */
public class StringUtils {
    public static Boolean hasText(String s) {
        if (s == null || s.trim().equals("")) {
            return false;
        }

        return true;
    }
}
