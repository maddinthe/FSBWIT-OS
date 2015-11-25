package Unterricht.Nov;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by mtheilen on 24.11.2015.
 */
public class Sudoku2 {

    private JLabel[][] labels=new JLabel[9][9];

    public static void main(String[] args) {
        new Sudoku2();
    }

    public Sudoku2(){
        JFrame jf=new JFrame("Sudoku2");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel top=new JPanel(new BorderLayout());
        JPanel time=new JPanel();
        JLabel zeit=new JLabel("Zeit:");
        zeit.setFont(zeit.getFont().deriveFont(40f));
        time.add(zeit);
        JLabel timer=new JLabel("03:12");
        timer.setFont(timer.getFont().deriveFont(60f));
        timer.setForeground(Color.BLUE);
        time.add(timer);
        top.add(time, BorderLayout.WEST);
        JButton neu=new JButton("Neu");
        top.add(neu, BorderLayout.EAST);
        jf.add(top, BorderLayout.NORTH);



        JPanel center=new JPanel(new GridLayout(3,3));
        for (int i = 0; i <9 ; i++) {
            JPanel neuner=new JPanel(new GridLayout(3,3));
            for (int j = 0; j < 9; j++) {
                JLabel jl=new JLabel();
                labels[i][j]=jl;
                jl.setHorizontalAlignment(JLabel.CENTER);
                jl.setBorder(new LineBorder(Color.BLACK,1));
                jl.setBackground(Color.white);
                jl.setFont(jl.getFont().deriveFont(40f));
                jl.setOpaque(true);
                neuner.add(jl);

            }
            neuner.setBorder(new LineBorder(Color.BLACK, 2));
            center.add(neuner);
        }
        jf.add(center, BorderLayout.CENTER);


        JPanel south=new JPanel(new GridLayout(2,1));
        JPanel einsBisSechs=new JPanel(new GridLayout(1,6));
        for (int i = 1; i <7; i++) {
            JButton jb=new JButton(""+i);
            jb.setFont(jb.getFont().deriveFont(40f));
            einsBisSechs.add(jb);
        }
        south.add(einsBisSechs);

        JPanel siebenBisNeun=new JPanel(new GridLayout(1,3));
        for (int i = 7; i <10; i++) {
            JButton jb=new JButton(""+i);
            jb.setFont(jb.getFont().deriveFont(40f));
            siebenBisNeun.add(jb);
        }
        JPanel southUnten=new JPanel(new GridLayout(1,2));
        southUnten.add(siebenBisNeun);

        JPanel eraseReset=new JPanel(new BorderLayout());
        BufferedImage bim= null;
        try {
            bim = ImageIO.read(new File("./Testdaten/Radiergummi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton erase=new JButton(new ImageIcon(bim));
        eraseReset.add(erase, BorderLayout.WEST);
        JButton reset=new JButton("Reset");
        reset.setFont(reset.getFont().deriveFont(40f));
        eraseReset.add(reset);
        southUnten.add(eraseReset);
        south.add(southUnten);


        jf.add(south, BorderLayout.SOUTH);

        jf.setSize(600,800);
        jf.setVisible(true);
    }
}
