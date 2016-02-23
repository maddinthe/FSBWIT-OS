package Unterricht.mar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by mtheilen on 23.02.2016.
 */
public class Simon extends JFrame {
    private Automat automat;

    public Simon() {
        super("Simon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel jp = new JPanel(new CardLayout());

        JPanel startPanel = new JPanel();
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)jp.getLayout()).show(jp,"Spiel");
            }
        });
        startPanel.add(start);

        JPanel buttonPanel = new JPanel(new GridLayout(2,2));
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        JButton[] buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton jb = buttons[i] = new JButton();
            jb.setContentAreaFilled(false);
            jb.setOpaque(true);
            jb.setBackground(colors[i]);
            jb.setPreferredSize(new Dimension(200,200));
            buttonPanel.add(jb);

        }

        JPanel loesenPanel = new JPanel();

        jp.add(startPanel,"Start");
        jp.add(buttonPanel,"Spiel");
        jp.add(loesenPanel,"Lösen");

        add(jp);

        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        new Simon();
    }

    private class Automat {
        private int pos;
        private List<JButton> farben;

        public Automat() {

        }

        public void schalten(ActionEvent e) {

        }

    }
}
