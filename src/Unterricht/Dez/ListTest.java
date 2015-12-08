package Unterricht.Dez;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by mtheilen on 01.12.2015.
 */
public class ListTest {

    public static void main(String[] args) {
        new ListTest();
    }

    public ListTest(){
        JFrame fenster =new JFrame("ListTest");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton add=new JButton("ADD");
        fenster.add(add, BorderLayout.SOUTH);


        MyListModel<Integer> liste=new MyListModel<Integer>();

        for (int i = 0; i <20 ; i++) {
            liste.daten.add(i);
        }

        JList<Integer> jListe=new JList<>(liste);
        JScrollPane jsp=new JScrollPane(jListe);
        fenster.add(jsp);




        fenster.setSize(150, 300);
        fenster.setVisible(true);
    }

    private class MyListModel<E> implements ListModel{
        private java.util.List<E> daten=new ArrayList<>();
        private List<ListDataListener> ldls=new ArrayList<>();

        @Override
        public int getSize() {
            return daten.size();
        }

        @Override
        public E getElementAt(int index) {
            return daten.get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            ldls.add(l);
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            ldls.remove(l);
        }
        public void add(int index, E element){
            daten.add(index, element);
            for (ListDataListener l:ldls){
                l.intervalAdded(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED,index,index));
            }
        }
        public void add(E element){
            daten.add(element);
            for (ListDataListener l:ldls) {
                l.intervalAdded(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, daten.size(), daten.size()));

            }
        }

        public void remove(int index){
            daten.remove(index);
            for(ListDataListener l:ldls){
                l.intervalRemoved(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index));
            }


        }
        public void clear(){
            int i=daten.size()-1;
            daten.clear();
            for(ListDataListener l:ldls){
                l.intervalRemoved(new ListDataEvent(this,ListDataEvent.INTERVAL_REMOVED,0,i));
            }
        }

    }
}
