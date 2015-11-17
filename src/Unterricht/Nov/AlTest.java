package Unterricht.Nov;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mtheilen on 10.11.2015.
 */
public class AlTest {



    public static void main(String[] args) {
        JFrame fenster=new JFrame("test");
        JButton button=new JButton("Rot");
        JTextPane jtp=new JTextPane();
        jtp.setOpaque(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtp.getBackground() == Color.RED)
                    jtp.setBackground(null);
                else
                    jtp.setBackground(Color.RED);
            }
        });

        fenster.add(button, BorderLayout.NORTH);
        fenster.add(jtp,BorderLayout.CENTER);


        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setSize(300, 300);
        fenster.setVisible(true);
    }

}
