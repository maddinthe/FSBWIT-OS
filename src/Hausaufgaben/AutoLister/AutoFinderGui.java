package Hausaufgaben.AutoLister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Martin on 06.11.2015.
 */
public class AutoFinderGui {


    public static void main(String[] args) {
        AutoFinder af = new AutoFinder();
        List<AutoFinder.Auto> autos = new LinkedList<>(new HashSet<>(af.autoLister(af.autoBundler(af.autosToString(af.getAutoFiles("./Testdaten/autos"))))));
        System.out.println(autos.size());
        gui2(autos);
    }


    public static void gui(List<AutoFinder.Auto> liste) {
        JFrame fenster = new JFrame("Autoliste");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AutoFinder.Auto[] autoArr = liste.toArray(new AutoFinder.Auto[liste.size()]);
        JList<AutoFinder.Auto> aListe = new JList<>(autoArr);
        JScrollPane listeSrcoller = new JScrollPane(aListe);
        fenster.add(listeSrcoller, BorderLayout.CENTER);

        fenster.setSize((int) aListe.getPreferredSize().getWidth() + 50, 500);
        fenster.isAlwaysOnTop();
        fenster.setVisible(true);

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Ausgabe sortiert nach: 1 = Name, 2 = KM, 3=Ort, 4=Ende");
            String eing = s.next();

            switch (eing.charAt(0)) {
                case '1': {
                    Arrays.sort(autoArr);
                    break;
                }
                case '2': {
                    Arrays.sort(autoArr, AutoFinder.Auto.KM);
                    break;
                }
                case '3': {
                    Arrays.sort(autoArr, AutoFinder.Auto.ORT);
                    break;
                }
                case '4':
                    System.exit(0);
            }

            aListe.setListData(autoArr);

        }


    }


    public static void gui2(List<AutoFinder.Auto> autoListe) {
        JFrame fenster = new JFrame("Autos");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AutoFinder.Auto[] autos = autoListe.toArray(new AutoFinder.Auto[autoListe.size()]);
        JList<AutoFinder.Auto> aListe = new JList<>(autos);
        JScrollPane scrollP = new JScrollPane(aListe);
        fenster.add(scrollP, BorderLayout.CENTER);

        JRadioButtonMenuItem km = new JRadioButtonMenuItem("KM");
        JRadioButtonMenuItem ort = new JRadioButtonMenuItem("Ort");
        JRadioButtonMenuItem name = new JRadioButtonMenuItem("Name");
        JRadioButtonMenuItem unsortiert = new JRadioButtonMenuItem("unsortiert");
        unsortiert.setSelected(true);
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("Sortierung");
        jmb.add(jm);
        fenster.setJMenuBar(jmb);
        ButtonGroup bg = new ButtonGroup();
        bg.add(km);
        bg.add(ort);
        bg.add(name);
        bg.add(unsortiert);
        jm.add(km);
        jm.add(ort);
        jm.add(name);
        jm.add(unsortiert);


        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButtonMenuItem evI = (JRadioButtonMenuItem) e.getSource();
                if (evI.getText() == "KM") {
                    Arrays.sort(autos, AutoFinder.Auto.KM);

                } else if (evI.getText() == "Ort") {
                    Arrays.sort(autos, AutoFinder.Auto.ORT);

                } else if (evI.getText() == "Name") {
                    Arrays.sort(autos);

                }
                if(unsortiert.isEnabled())unsortiert.setEnabled(false);
                aListe.setListData(autos);


            }
        };
        km.addActionListener(al);
        ort.addActionListener(al);
        name.addActionListener(al);


        fenster.setSize((int) aListe.getPreferredSize().getWidth() + 50, 500);
        fenster.setVisible(true);


    }


}
