package Hausaufgaben.AutoLister;

import javax.swing.*;
import java.awt.*;
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
        gui(autos);
    }


    public static void gui(List<AutoFinder.Auto> liste) {
        boolean gedrueckt=false;
        JFrame fenster = new JFrame("Autoliste");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AutoFinder.Auto[] autoArr = liste.toArray(new AutoFinder.Auto[liste.size()]);
        JList<AutoFinder.Auto> aListe = new JList<>(autoArr);
        JScrollPane listeSrcoller = new JScrollPane(aListe);
        fenster.add(listeSrcoller, BorderLayout.CENTER);

        fenster.setSize((int)aListe.getPreferredSize().getWidth()+50,500);
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


}
