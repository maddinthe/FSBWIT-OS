package Unterricht.Nov;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mtheilen on 17.11.2015.
 */
public class SchiebePuzzle implements ActionListener {
    private final int XX = 4;
    private final int YY = 3;
    private JButton[] buttons = new JButton[XX * YY];
    private int lastButton = 0;
    private Icon lastIcon = null;
    private boolean firstClick = true;

    public SchiebePuzzle() {
        JFrame jf = new JFrame("Schiebe-Puzzle");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new GridLayout(YY, XX));
        try {
            BufferedImage bim = ImageIO.read(new File("./Testdaten/HexEdit/wald.jpg"));
            int w = bim.getWidth();
            int h = bim.getHeight();
            for (int y = 0; y < YY; y++)
                for (int x = 0; x < XX; x++) {
                    int pos=y * XX + x;
                    buttons[pos] = new JButton(new ImageIcon(bim.getSubimage(x * (w / XX), y * (h / YY), w / XX, h / YY)));
                    buttons[pos].setBorder(new LineBorder(Color.BLACK, 2));
                    buttons[pos].setActionCommand("" + (pos));
                    buttons[pos].setName("" + (pos));
                    buttons[pos].addActionListener(this);
                    jf.add(buttons[pos]);
                }


        } catch (IOException e) {
            e.printStackTrace();
        }

        jf.pack();
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new SchiebePuzzle();
    }

    private boolean isDone() {
        for(JButton b:buttons)
            if(!b.getName().equals(b.getActionCommand()))return false;
        return true;
    }

    private void switchButton(int pos) {
        if(inTouch(pos)){
        String h=buttons[lastButton].getName();
        Icon old=buttons[lastButton].getIcon();
        buttons[lastButton].setName(buttons[pos].getName());
        buttons[lastButton].setIcon(buttons[pos].getIcon());
        buttons[pos].setName(h);
        buttons[pos].setIcon(old);
            lastButton=pos;
    }
    }

    private boolean inTouch(int pos) {
        return (((pos % XX) == (lastButton % XX)) && (Math.abs(pos / XX - lastButton / XX) < 2)) || (((pos / XX) == (lastButton / XX)) && (Math.abs(pos % XX - lastButton % XX) < 2));

    }

    public void actionPerformed(ActionEvent e) {
        if (firstClick) {
            firstClick = false;                     // firstClick = false setzen, da der erste Zug gemacht wurde
            lastIcon = buttons[0].getIcon();        // lastIcon festlegen => Auf das Feld oben links, wo das Bild "entfernt" wird
            buttons[0].setIcon(null);               // buttons an der Stelle 0 (oben links) auf null setzen, damit das Bildchen "weg" ist
            for (int i=0; i<1000; i++) {              // Schleife Bildverschiebung (Bildverschiebungen, damit das Spiel beginnen kann)
                // => Kleine Zahl = wenige Verschiebungen und leichtes loesen!
                int zufall = (int)(Math.random()*XX*YY);    // Zufallszahl/zug - Erzeugung
                switchButton(zufall);                       // Die Zuege durchfuehren
            }
            while (lastButton % XX != 0) {      // Sorgt dafuer, dass das leere Feld ganz links ist --> Spaltenverschiebung
                switchButton(lastButton-1);         // "Berechnung" --> lastButton 'geteilt durch' XX
            }                                       //                  Wenn RESTWERT != 0  =>  switchButton nochmal aufrufen

            while(lastButton / XX != 0){        // Sorgt dafuer, dassd das leere Feld ganz oben ist --> Zeilenverschiebung
                switchButton(lastButton-XX);        // "Berechnung" --> lastButton 'geteilt durch' XX
            }                                       //                  Wenn Ergebnis != 0  =>  switchButton nochmal aufrufen
        }
        else {      // "Methode" zum Beenden des Spieles / feststellen, dass das Bild komplett ist
            int pos = Integer.parseInt(e.getActionCommand());   //
            switchButton(pos);
            if (isDone()) {
                buttons[0].setIcon(lastIcon);
                firstClick = true;
            }

        }

    }
}
