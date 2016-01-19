package Unterricht.Jan.maedn.gui;

import Unterricht.Jan.maedn.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by mtheilen on 19.01.2016.
 */
public class SpielBeginn extends JDialog {
    private Spieler[] startaufstellung=new Spieler[4];
    private JButton weiter = new JButton("weiter");
    public SpielBeginn(){
        super((JFrame) null, "Mensch ärgere dich nicht - Spielstart", true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                startaufstellung = null;
                setVisible(false);

            }
        });
        JPanel oben=new JPanel(new GridLayout(2,1));
        oben.add(new JLabel("Zieht die Figuren mit denen ihr Spielen wollt",JLabel.CENTER));
        oben.add(new JLabel("in die gewünschten Ecken.",JLabel.CENTER));
        add(oben, BorderLayout.NORTH);

        //todo: hier Fehlt noch das Zurechgeschiebe und layout und listener und so weiter;
        JPanel mitte=new JPanel(null);

        weiter.setEnabled(false);
        weiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(weiter, BorderLayout.SOUTH);

        pack();
        setVisible(true);


    }

    public Spieler[] getStartaufstellung() {
        return startaufstellung;
    }

    public static void main(String[] args) {
        new SpielBeginn();
    }
}
