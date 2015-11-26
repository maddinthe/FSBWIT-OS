package TestSachen;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by mtheilen on 26.11.2015.
 */
public class Uebung_1 {

    public static void main(String[] args) {
        new Uebung_1();
    }

    public Uebung_1(){
        JFrame fenster=new JFrame("ExtremeText");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea jta=new JTextArea();
        JScrollPane jsp=new JScrollPane(jta);
        fenster.add(jsp, BorderLayout.CENTER);

        JPanel north=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel datei=new JPanel(new GridLayout(2,1));
        datei.setBorder(new TitledBorder("Datei"));

        JPanel name=new JPanel();
        JLabel nameLabel=new JLabel("Name:");
        JTextField eingabeName=new JTextField(20);
        name.add(nameLabel);
        name.add(eingabeName);
        datei.add(name);

        JPanel oeffnenSpeichern=new JPanel();
        JButton oeffnen=new JButton("Öffnen");
        JButton speichern=new JButton("Speichern");
        oeffnenSpeichern.add(oeffnen);
        oeffnenSpeichern.add(speichern);
        datei.add(oeffnenSpeichern);

        JPanel einfuegen=new JPanel(new GridLayout(2,1));
        einfuegen.setBorder(new TitledBorder("Einfügen"));
        JPanel anzahlWort=new JPanel();
        JLabel anzahl=new JLabel("Anzahl:");
        anzahlWort.add(anzahl);
        JTextField anzahlText=new JTextField(5);
        anzahlWort.add(anzahlText);
        JLabel wort=new JLabel("Wort:");
        anzahlWort.add(wort);
        JTextField wortText=new JTextField(10);
        anzahlWort.add(wortText);
        einfuegen.add(anzahlWort);
        JButton einfuegenButton=new JButton("Einfügen");
        JPanel einfuegenButtonPanel=new JPanel();
        einfuegenButtonPanel.add(einfuegenButton);
        einfuegen.add(einfuegenButtonPanel);

        JPanel loeschen=new JPanel(new GridLayout(2,1));
        loeschen.setBorder(new TitledBorder("Löschen"));
        JPanel loeschenWort=new JPanel();
        JLabel wortLoeschen=new JLabel("Wort");
        JTextField wortLoeschenText=new JTextField(10);
        loeschenWort.add(wortLoeschen);
        loeschenWort.add(wortLoeschenText);
        loeschen.add(loeschenWort);
        JPanel loeschenButtonPanel=new JPanel();
        JButton loeschenButton=new JButton("Löschen");
        loeschenButtonPanel.add(loeschenButton);
        loeschen.add(loeschenButtonPanel);







        north.add(datei);
        north.add(einfuegen);
        north.add(loeschen);


        oeffnen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader br = new BufferedReader(new FileReader(eingabeName.getText()))) {
                    jta.setText("");
                    String zeile;
                    while ((zeile = br.readLine()) != null) {
                        jta.append(zeile);
                        jta.append("\n");

                    }

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(fenster,"Lesefehler","datein nicht gefunden",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        speichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(eingabeName.getText()))) {
                    bw.write(jta.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(fenster, "Schreibfehler", "kein zugriff", JOptionPane.ERROR_MESSAGE);;
                }
            }
        });
        einfuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < Integer.parseInt(anzahlText.getText()); i++) {
                        jta.append(wortText.getText());

                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(fenster,"Formatfehler","Fehler im Feld Anzahl",JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(jta.getText().replaceAll(wortLoeschenText.getText(),""));
            }
        });





        fenster.add(north, BorderLayout.NORTH);

        fenster.setSize(900, 400);
        fenster.setVisible(true);

    }

}
