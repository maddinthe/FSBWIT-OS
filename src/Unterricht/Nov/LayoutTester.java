package Unterricht.Nov;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mtheilen on 06.11.2015.
 */
public class LayoutTester {
    public static void main(String[] args) {
        JFrame fenster=new JFrame("Test");
        fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addJLabel(fenster, "Nord", Color.blue, BorderLayout.NORTH);
//        addJLabel(fenster,"Süd",Color.cyan,BorderLayout.SOUTH);
//        addJLabel(fenster,"Ost",Color.lightGray,BorderLayout.EAST);
//        addJLabel(fenster,"West",Color.ORANGE,BorderLayout.WEST);
//        addJLabel(fenster,"Mitte",Color.GREEN,BorderLayout.CENTER);
        JButton button=new JButton("Hallo");
        fenster.add(button, BorderLayout.WEST);
        JCheckBoxMenuItem jcbmi=new JCheckBoxMenuItem("Hallo");
        fenster.add(jcbmi, BorderLayout.EAST);
        JColorChooser cc=new JColorChooser();
        fenster.add(cc, BorderLayout.CENTER);
        JFileChooser jfc=new JFileChooser();
        fenster.add(jfc,BorderLayout.SOUTH);




        fenster.setSize(400,400);
        fenster.setVisible(true);
    }

    public static void addJLabel(JFrame frame,String inhalt,Color farbe,String pos){
        JLabel jl = new JLabel(inhalt,JLabel.CENTER);
        jl.setOpaque(true);
        jl.setBackground(farbe);
        jl.setFont(jl.getFont().deriveFont(40f));
        frame.add(jl,pos);

    }



}
