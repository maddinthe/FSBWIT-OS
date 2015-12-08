package Hausaufgaben.ActiveJlistHA;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

/**
 * Created by Martin on 07.12.2015.
 */
public class ActiveJList<E> extends JList {


    public ActiveJList(){
        super();
        this.setModel(new MyListModel<E>());
    }

    public void remove(int index){
        ((MyListModel)this.getModel()).remove(index);
    }
    public void clear(){
        ((MyListModel)this.getModel()).clear();
    }
    public void add(int index,E element){
        ((MyListModel)this.getModel()).add(index, element);
    }
    public void add(E element){
        ((MyListModel)this.getModel()).add(element);
    }



    public class MyListModel<E> implements ListModel<E>{
        private ArrayList<E> daten=new ArrayList<>();
        private ArrayList<ListDataListener> ldls=new ArrayList<>();

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
