package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {

    @Override
    public Iterator iterator(){
        return new Iterator(){
            Element current = head;
            @Override
            public boolean hasNext(){
                return current != null;
            }

            @Override
            public Object next(){
                Object temp = current.GetItem();
                current = current.next;
                return temp;
            }
        };
    }
    public Element head = null;
    private int size = 0;

    /*
    static, weil innere Klasse nichts von äußeren Klasse wissen muss
    public: ELement braucht Instanz von äußeren Klasse
    */
    private static class Element{
        private Object Item;
        private Element next;

        public Element(Object item){
            this.Item = item;
        }

        public Object GetItem(){
            return Item;
        }

        public Element GetNext(){
            return next;
        }
    }

    @Override
    public void add(Object o){
        size = size + 1;

        if (head == null){
            head = new Element(o);
            return;
        }

        Element it = head;
        while (it.next != null){
            it = it.next;
        }
        it.next = new Element(o);
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter){
        SimpleListImpl neu = new SimpleListImpl();
        for (Object o : this) {
            if (filter.include(o))
                neu.add(o);
        }
        return neu;
        }

}
