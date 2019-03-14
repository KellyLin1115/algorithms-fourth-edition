package chapter1.item3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStackOfString implements Iterable<String> {

    private String[] a;
    private int n; // number of items in stack

    public FixedCapacityStackOfString(int capacity) {
        n = 0;
        a = new String[capacity];
    }

    public boolean isFull() {
        return n == a.length;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String item) {
        if (isFull()) throw new RuntimeException("The stack is full");
        a[n++] = item;
    }

    public String pop() {
        if (isEmpty()) throw new RuntimeException("The stack is empty");
        return a[--n];
    }

    public String peek() {
        return a[n-1];
    }

    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<String> {
        private int i = n-1;

        public boolean hasNext(){
            return i >= 0;
        }

        public String next(){
            if(!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        FixedCapacityStackOfString stack = new FixedCapacityStackOfString(10);
        for (int i = 0; i < 10; i++) {
            stack.push("a" + i);
        }

        for (String s : stack)
            System.out.println(stack.pop() + " ");
    }
}
