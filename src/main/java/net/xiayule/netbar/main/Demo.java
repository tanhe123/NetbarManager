package net.xiayule.netbar.main;

import net.xiayule.netbar.view.MainFrame;

import javax.swing.*;
import java.util.Calendar;

/**
 * Created by tan on 15-1-5.
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {

        /*JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
                } catch (Exception e) {
                    System.out.println("Substance Graphite failed to initialize");
                }
                JFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });*/
/*        try {
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (Exception e) {
            System.out.println("Substance Graphite failed to initialize");
        }*/

        JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
