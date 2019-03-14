package chapter1.item3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;

    public FixedCapacityStack(int capacity) {
        n = 0;
        a = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == a.length;
    }

    public void push(Item item) {
        if(isFull()) throw new RuntimeException("Stack is full");
        a[n++] = item;
    }

    public Item pop() {
        if(isEmpty()) throw new RuntimeException("Stack is empty");
        return a[--n];
    }

    public Item peek() {
        if(isEmpty()) throw new RuntimeException("Stack is empty");
        return a[n-1];
    }

    public Iterator<Item> iterator() {
       return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = n - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(10);
        for (int i = 0; i < 10; i++) {
            stack.push("a" + i);
        }

        for (String s : stack)
            System.out.print(stack.pop() + " ");

        System.out.println();

        FixedCapacityStack<Integer> stack2 = new FixedCapacityStack<>(10);
        for (int i = 0; i < 10; i++) {
            stack2.push(10 + i);
        }

        for (Integer s : stack2)
            System.out.print(stack2.pop() + " ");

    }

}
