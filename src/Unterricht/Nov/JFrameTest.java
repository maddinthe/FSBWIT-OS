package Unterricht.Nov;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mtheilen on 03.11.2015.
 */
public class JFrameTest {
    //    public static void main(String[] args) {
//        JFrame fenster= new JFrame("Hallo Welt");
//        fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//
//
//        JLabel label=new JLabel("Hallo Welt!",JLabel.CENTER);
//        label.setBackground(Color.green);
//        label.setOpaque(true);
//        label.setForeground(Color.RED);
//        label.setFont(label.getFont().deriveFont(40.0f));
//        fenster.add(label);
//
//
//        fenster.setSize(400, 400);
//        fenster.setLocationRelativeTo(null);
//        fenster.setVisible(true);
//        //new JframeDemo();
//
//    }
    public static void main(String[] args) {
        JFrame eins = new JFrame("1");
        JFrame zwei = new JFrame("2");
        eins.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        zwei.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("Hallo Welt!", JLabel.CENTER);
        label.setBackground(Color.green);
        label.setOpaque(true);
        label.setForeground(Color.RED);
        label.setFont(label.getFont().deriveFont(40.0f));
        eins.add(label);
        zwei.add(label);
        eins.setSize(200,200);
        zwei.setSize(200,200);
        eins.setVisible(true);
        zwei.setVisible(true);

    }


}
