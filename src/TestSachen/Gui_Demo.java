package TestSachen;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by mtheilen on 26.11.2015.
 */
public class Gui_Demo {
    public static void main(String[] args) {
        new Gui_Demo();
    }

    public Gui_Demo(){
        JFrame fenster=new JFrame("Titel");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField jtf=new JTextField(20);  //20 Zeichen langens JTF
        JTextArea jta=new JTextArea();  // Mehrzeiliges Display
        JPanel jp=new JPanel();
        JScrollPane scp=new JScrollPane(jta);
        jp.setBorder(new TitledBorder("Test")); //Border mit titel Test
        JButton jb=new JButton("Aufschrift");
        jp.add(jb);


        fenster.add(jtf,BorderLayout.NORTH);
        fenster.add(jp,BorderLayout.SOUTH);
        fenster.add(scp,BorderLayout.CENTER);

        jta.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n");


        fenster.setSize(400, 200);
        fenster.setVisible(true);


    }
}
