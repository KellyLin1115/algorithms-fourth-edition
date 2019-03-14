package chapter1.item3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private int n; //number of items in queue
    private Item[] q;
    private int first;//index of first item in queue
    private int last;//index of next available slot

    public ResizingArrayQueue() {
        n = 0;
        q = (Item[]) new Object[2];
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueue(Item item) {
        if (n == q.length) resize(2 * q.length);
        q[last++] = item;
        if(last == q.length) last = 0;
        n++;

    }

    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException("Queue is empty");
        Item item = q[first];
        q[first] = null;
        first++;
        if(first == q.length) first = 0;
        n--;
        if(n > 0 && n == q.length/4) resize(q.length/2);
        return item;
    }

    public Item peek(){
        if(isEmpty()) throw new NoSuchElementException("Queue is empty");
        return q[first];
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] tmp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tmp[i] = q[(first + i) % q.length];
        }
        first = 0;
        last = n;
        q = tmp;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
           return i < n;
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("Queue is empty");
            Item item = q[(i+first) % q.length];
            i++;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> stack = new ResizingArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            stack.enqueue("a" + i);
        }
        System.out.println(stack.size());
        for (String s : stack)
            System.out.print(s + " ");

        System.out.println();
        System.out.println(stack.size());

        ResizingArrayQueue<Integer> stack2 = new ResizingArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            stack2.enqueue(10 + i);
        }

        for (Integer s : stack2)
            System.out.print(s + " ");

    }
}
