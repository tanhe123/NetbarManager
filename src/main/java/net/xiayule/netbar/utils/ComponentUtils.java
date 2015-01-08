package net.xiayule.netbar.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tan on 15-1-5.
 */
public class ComponentUtils {
    public static JTextField createJTextField() {
        JTextField jTextField = new JTextField(15);

        jTextField.setMaximumSize(new Dimension(250, 25));

        return jTextField;
    }

    public static JPasswordField createJpasswordField() {
        JPasswordField jPasswordField = new JPasswordField(15);

        jPasswordField.setMaximumSize(new Dimension(250, 25));

        return jPasswordField;
    }
}
