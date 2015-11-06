package Hausaufgaben.AutoLister;

import javax.swing.*;
import java.util.*;

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
        JFrame fenster = new JFrame("Autoliste");
        fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        AutoFinder.Auto[] autoArr = liste.toArray(new AutoFinder.Auto[liste.size()]);
        JList<AutoFinder.Auto> aListe = new JList<>(autoArr);
        JScrollPane listeSrcoller = new JScrollPane(aListe);
        fenster.add(listeSrcoller);

        fenster.setSize(1000, 500);
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
