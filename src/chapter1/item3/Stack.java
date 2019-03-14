package chapter1.item3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
    private Node<Item> first;
    private int n;

    public Stack() {
        n = 0;
        first = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        if(isEmpty()) throw new NoSuchElementException("Stack overflow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Stack overflow");
        return first.item;
    }

    public int size() {
        return n;
    }

    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Item item : this)
            sb.append(item + " ");
        return sb.toString();
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            if(!hasNext()) throw new NoSuchElementException("Stack overflow");
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push("a" + i);
        }

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        for (String s : stack)
            System.out.print(s + " ");
        System.out.println();

        System.out.println(stack.size());

        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack2.push(10 + i);
        }

        System.out.println(stack2);
    }

}
