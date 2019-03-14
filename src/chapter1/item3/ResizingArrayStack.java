package chapter1.item3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * We double the size of the array in push() if it is full;
 * we halve the size of the array in pop() if it is less than one-quarter full.
 * @param <Item>
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;

    public ResizingArrayStack() {
        n = 0;
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == a.length;
    }

    public void push(Item item) {
        if (isFull()) resize(2 * a.length);
        a[n++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        if (n == a.length / 4) resize(a.length / 2);
        Item item = a[n - 1];
        a[n-1] = null;
        n--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[n - 1];
    }

    private void resize(int capacity) {
        Item[] tmp = a;
        a = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            a[i] = tmp[i];
        }
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<Item> {
        int i = n - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("Stack underflow");
            return a[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push("a" + i);
        }

        for (String s : stack)
            System.out.print(stack.pop() + " ");

        System.out.println();

        ResizingArrayStack<Integer> stack2 = new ResizingArrayStack<>();
        for (int i = 0; i < 10; i++) {
            stack2.push(10 + i);
        }

        for (Integer s : stack2)
            System.out.print(stack2.pop() + " ");
    }
}
