package chapter1.item3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {

    private int n;
    private Node<Item> first;
    private Node<Item> last;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    public Item peek(){
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
    public void enqueue(Item item){
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if(isEmpty()) last = null;
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException("Queue underflow");
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Queue<String> stack = new Queue<>();
        for (int i = 0; i < 10; i++) {
            stack.enqueue("a" + i);
        }
        System.out.println(stack.size());

        System.out.println(stack);

        System.out.println();

        Queue<Integer> stack2 = new Queue<>();
        for (int i = 0; i < 10; i++) {
            stack2.enqueue(10 + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(stack2.dequeue() + " ");
        }

    }
}
