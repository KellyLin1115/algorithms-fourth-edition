package chapter1.item3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private int n;
    private Node<Item> first;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag(){
        n = 0;
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(Item item){
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Item item: this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator<Item> iterator(){
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;
        public ListIterator(Node<Item> first){
            current = first;
        }
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException("Bag underflow");
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        for (int i = 0; i < 10; i++) {
            bag.add("a"+i);
        }
        System.out.println(bag);
    }
}
