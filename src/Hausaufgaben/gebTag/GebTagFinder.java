package Hausaufgaben.gebTag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;

/**
 * Created by Martin on 15.01.2016.
 */
public class GebTagFinder {
    private String[] tage = {"Fehler", "Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};

    public GebTagFinder() {
        JFrame jf = new JFrame("Geburtstagsfinder");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new FlowLayout());
        GregorianCalendar cal = new GregorianCalendar();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        JTextField datum = new JTextField(df.format(cal.getTime()));
        Font f = datum.getFont().deriveFont(40f);
        datum.setFont(f);
        datum.setHorizontalAlignment(JTextField.CENTER);

        JButton ok = new JButton("OK");
        ok.setFont(f);
        JLabel tag = new JLabel(tage[cal.get(GregorianCalendar.DAY_OF_WEEK)]);
        tag.setHorizontalAlignment(JLabel.CENTER);
        tag.setFont(f);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cal.setTime(df.parse(datum.getText()));
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(jf, "Datum hat Falsches Format: DD.MM.YYYY", "Datumsfehler", JOptionPane.ERROR_MESSAGE);
                }
                tag.setText(tage[cal.get(GregorianCalendar.DAY_OF_WEEK)]);
                jf.pack();
            }

            ;


        });
        jf.add(datum);
        jf.add(ok);
        jf.add(tag);


        jf.pack();
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new GebTagFinder();
    }
}
