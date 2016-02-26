package Unterricht.mar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automat.schalten(e);
            }
        };
        start.addActionListener(actionListener);
        startPanel.add(start);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
        JButton[] buttons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton jb = buttons[i] = new JButton();
            jb.setContentAreaFilled(false);
            jb.setOpaque(true);
            jb.setBackground(colors[i]);
            jb.setPreferredSize(new Dimension(200, 200));
            jb.addActionListener(actionListener);
            buttonPanel.add(jb);

        }

        JPanel loesenPanel = new JPanel();
        JButton loesen = new JButton("Lösen");
        loesen.addActionListener(actionListener);
        loesenPanel.add(loesen);


        jp.add(startPanel, "Start");
        jp.add(buttonPanel, "Spiel");
        jp.add(loesenPanel, "Lösen");

        automat = new Automat(jp, buttons);
        add(jp);

        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        new Simon();
    }

    private class Automat {
        private int pos = 0;
        private List<JButton> farben = new ArrayList<>();
        private int zustand = 1;
        private JPanel panel;
        private JButton[] buttons;

        public Automat(JPanel panel, JButton[] buttons) {
            this.panel = panel;
            this.buttons = buttons;
        }

        public void schalten(ActionEvent e) {

            switch (zustand) {
                case 1: {
                    ((CardLayout) panel.getLayout()).show(panel, "Spiel");
                    farben.add(buttons[(int) (Math.random() * buttons.length)]);
                    new Thread() {
                        public void run() {
                            for(JButton jb:buttons){
                                jb.setEnabled(false);
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e1) {}
                            for (JButton jb : farben) {
                                Color c = jb.getBackground();
                                jb.setBackground(new Color(c.getRGB() | 0x808080));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e1) {}
                                jb.setBackground(c);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e1) {}
                            }
                            ((CardLayout) panel.getLayout()).show(panel, "Lösen");
                            zustand = 3;
                            for(JButton jb:buttons){
                                jb.setEnabled(true);
                            }
                        }
                    }.start();

                    break;
                }
                case 3: {
                    ((CardLayout) panel.getLayout()).show(panel, "Spiel");
                    zustand=4;
                    break;
                }
                case 4: {
                    if(e.getSource() != farben.get(pos)){
                        zustand=1;
                        pos=0;
                        farben.clear();
                        ((CardLayout) panel.getLayout()).show(panel, "Start");

                    }
                    else if (pos<farben.size()-1){
                        pos++;
                    }else{
                        zustand=1;
                        pos=0;
                        ((CardLayout) panel.getLayout()).show(panel, "Start");
                    }


                    break;
                }
            }

        }

    }
}
