package Unterricht.Jan.maedn.darstellung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mtheilen on 19.01.2016.
 */
public class DragLabel extends JLabel {
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



