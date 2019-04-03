package chapter3.item3;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private int size;
        private Node left;
        private Node right;
        private boolean color;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if(x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if(x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key){
        if(key == null) throw new IllegalArgumentException();
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp > 0) return get(x.right, key);
        else if(cmp < 0) return get(x.left, key);
        else return x.value;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value){
        if(key == null) throw new IllegalArgumentException();
        //if(value == null) {
        // delete(key, value);
        //return;
        //}
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if(h == null) return new Node(key, value, RED,1);
        int cmp = key.compareTo(h.key);
        if(cmp < 0) {
            h.left = put(h.left, key, value);
        }else if(cmp > 0) {
            h.right = put(h.right, key , value);
        }else {
            h.value = value;
        }
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        else if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        else if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right)  + 1;
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
}
