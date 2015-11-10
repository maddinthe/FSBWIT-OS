package Unterricht.Nov;

import javax.swing.*;
import java.awt.event.InputEvent;

/**
 * Created by mtheilen on 10.11.2015.
 */
public class menutest {
    public static void main(String[] args) {
        JFrame fenster=new JFrame("Rechner");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar jmb =new JMenuBar();
        JMenu eins=new JMenu("Ansicht");
        JMenu zwei=new JMenu("Bearbeiten");
        JMenu drei=new JMenu("?");
        JMenuItem hilfe=new JMenuItem("Hilfe Anzeigen");
        JMenuItem info=new JMenuItem("Info");
        drei.add(hilfe);
        drei.addSeparator();
        drei.add(info);

        JMenuItem copy=new JMenuItem("Kopieren Strg-C");
        JMenuItem einfg=new JMenuItem("Einfügen Strg-V");
        JMenu verlauf=new JMenu("Verlauf");
        JMenuItem verlcopy=new JMenuItem("Verlauf Kopieren");
        JMenuItem verlbearb=new JMenuItem("Bearbeiten");
        JMenuItem verlabb=new JMenuItem("Bearbeiten Abbrechen");
        JMenuItem verlloesch=new JMenuItem("Löschen");
        verlauf.add(verlcopy);
        verlauf.add(verlbearb);
        verlauf.add(verlabb);
        verlauf.add(verlloesch);
        verlcopy.setEnabled(false);
        verlbearb.setEnabled(false);
        verlabb.setEnabled(false);
        verlloesch.setEnabled(false);

        zwei.add(copy);
        zwei.add(einfg);
        zwei.addSeparator();
        zwei.add(verlauf);

        ButtonGroup auswArt=new ButtonGroup();
        JRadioButtonMenuItem std=new JRadioButtonMenuItem("Standard");
        JRadioButtonMenuItem wiss=new JRadioButtonMenuItem("Wissenschaftlich");
        JRadioButtonMenuItem prog=new JRadioButtonMenuItem("Programmierer");
        JRadioButtonMenuItem stat=new JRadioButtonMenuItem("Statistik");
        auswArt.add(std);
        auswArt.add(wiss);
        auswArt.add(prog);
        auswArt.add(stat);
        std.setSelected(true);
        eins.add(std);
        eins.add(wiss);
        eins.add(prog);
        eins.add(stat);
        eins.addSeparator();

        JCheckBoxMenuItem verla=new JCheckBoxMenuItem("Verlauf");
        JCheckBoxMenuItem ziff=new JCheckBoxMenuItem("Zifferngruppierung");
        eins.add(verla);
        eins.add(ziff);
        eins.addSeparator();

        ButtonGroup mod=new ButtonGroup();
        JRadioButtonMenuItem bas=new JRadioButtonMenuItem("Basismodus");
        JRadioButtonMenuItem einh=new JRadioButtonMenuItem("Einheitenumrechnung");
        JRadioButtonMenuItem dat=new JRadioButtonMenuItem("Datumsberechnung");
        bas.setSelected(true);
        mod.add(bas);
        mod.add(einh);
        mod.add(dat);
        eins.add(bas);
        eins.add(einh);
        eins.add(dat);
        JMenu arbbl=new JMenu("Arbeitsblätter");
        JRadioButtonMenuItem hypo=new JRadioButtonMenuItem("Hypothek");
        JRadioButtonMenuItem fahrz=new JRadioButtonMenuItem("Fahrzeugleasing");
        JRadioButtonMenuItem wirtsch=new JRadioButtonMenuItem("Wirtschaftlichkeit");
        JRadioButtonMenuItem kratscht=new JRadioButtonMenuItem("Kraftstoffverbrauch");
        mod.add(hypo);
        mod.add(fahrz);
        mod.add(wirtsch);
        mod.add(kratscht);
        arbbl.add(hypo);
        arbbl.add(fahrz);
        arbbl.add(wirtsch);
        arbbl.add(kratscht);
        eins.add(arbbl);

        jmb.add(eins);
        jmb.add(zwei);
        jmb.add(drei);

        fenster.setJMenuBar(jmb);
        fenster.setSize(500,500);
        fenster.setVisible(true);
    }
}
