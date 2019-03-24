package chapter3.item1;

import chapter1.item3.Queue;

public class SequentialSearchST<Key, Value> {

    private int n;
    private Node first;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if(key == null) throw new IllegalArgumentException("Argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Argument to get() is null");
        for (Node x = first; x != null; x = x.next) {
            if(key.equals(x.key))
                return x.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("First argument to put() is null");
        if(value == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if(key.equals(x.key))
                x.value = value;
            return;
        }
        first = new Node(key, value, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Argument to delete() is null");
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if(key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
}
