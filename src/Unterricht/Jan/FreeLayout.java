package Unterricht.Jan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by mtheilen on 15.01.2016.
 */
public class FreeLayout {

    public FreeLayout(){
        JFrame jf=new JFrame("NullLayout Test");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(null);


        JLabel jl=new DragLabel(new ImageIcon("./testdaten/Gb1b.jpg"));
        jl.setSize(jl.getPreferredSize());
        jl.setLocation(30,40);
        jf.add(jl);




        jf.setSize(500,500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new FreeLayout();
    }

    private class DragLabel extends JLabel{
        private Point start;

        public DragLabel(Icon ic){
            super(ic);
            MouseAdapter ma=new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    setLocation(e.getLocationOnScreen().x-start.x,e.getLocationOnScreen().y- start.y);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    start=new Point(e.getLocationOnScreen().x -getLocation().x,e.getLocationOnScreen().y-getLocation().y);


                }
            };
            addMouseListener(ma);
            addMouseMotionListener(ma);
        }
        public DragLabel(String s){
            super(s);
            MouseAdapter ma=new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    setLocation(e.getLocationOnScreen().x-start.x,e.getLocationOnScreen().y- start.y);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                    start=new Point(e.getLocationOnScreen().x -getLocation().x,e.getLocationOnScreen().y-getLocation().y);


                }
            };
            addMouseListener(ma);
            addMouseMotionListener(ma);
        }
    }
}
