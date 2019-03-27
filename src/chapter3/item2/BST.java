package chapter3.item2;

import chapter1.item3.Queue;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;
        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BST() {}

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.size;
    }

    public boolean contains(Key key){
        if(key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if(key == null) throw new IllegalArgumentException();
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x.value;
        else if (cmp > 0) return get(x.right, key);
        else return get(x.left, key);
    }

    public void put(Key key,Value value) {
        if(key == null) throw new IllegalArgumentException();
        if(value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value){
        Node item = new Node(key, value, 1);
        if (x == null) return x = item;
        int cmp = key.compareTo(x.key);
        if(cmp > 0){
            x.right = put(x.right, key, value);
        }else if(cmp < 0) {
            x.left = put(x.left, key, value);
        }else{
            x.value = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right)+1;
        return x;
    }

    public void deleteMax() {
        if(isEmpty()) throw new NoSuchElementException();
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if(x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.right) + size(x.left) + 1;
        return x;
    }

    public void delete(Key key) {
        if(key == null) throw new IllegalArgumentException();
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if(x == null) throw new NoSuchElementException();
        int cmp = key.compareTo(x.key);
        if(cmp > 0) {
            x.right =  delete(x.right, key);
        } else if (cmp < 0) {
            x.left = delete(x.left, key);
        }else{
            if(x.left == null) return x.right;
            else if(x.right == null) return x.left;
            else{
                Node t = x;
                x = min(t.right);
                x.left = t.left;
                x.right = deleteMin(t.right);
            }
        }
        x.size = size(x.right) + size(x.left) + 1;
        return x;
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        if(isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key){
        if(key == null) throw new IllegalArgumentException();
        if(isEmpty()) throw new NoSuchElementException();
        Node x = floor(root, key);
        if(x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp > 0) {
            Node item = floor(x.right, key);
            if(item == null) return x;
            return item;
        }else if(cmp < 0){
            return floor(x.left, key);
        }else return x;
    }

    public Key ceiling(Key key){
        if(key == null) throw new IllegalArgumentException();
        if(isEmpty()) throw new NoSuchElementException();
        Node x = ceiling(root, key);
        if(x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp > 0) return ceiling(x.right, key);
        else if(cmp < 0) {
            Node item = ceiling(x.left,key);
            if(item != null) return item;
            else return x;
        }else return x;
    }

    public Key select(int k) {
        if(isEmpty()) throw new NoSuchElementException();
        if(k < 0 || k >= size()) throw new IllegalArgumentException();
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        int n = size(x.left);
        if(k == n) return x;
        else if(k < n) return select(x.left, k);
        else return select(x.right, k-n-1);
    }

    public int rank(Key key){
        if(key == null) throw new IllegalArgumentException();
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return size(x.left);
        else if(cmp > 0) return size(x.left) + 1 + rank(x.right, key);
        else return rank(x.left, key);
    }

    public Iterable<Key> keys() {
        if(isEmpty()) return new Queue<>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue queue, Key lo, Key hi){
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, queue, lo, hi);
        else if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        else if(cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("a", 1);
        bst.put("c", 3);
        bst.put("b", 3);
        bst.put("d", 4);
        bst.put("b", null);
        bst.put("e", 5);
        System.out.println(bst.select(0));
        System.out.println(bst.select(1));
        System.out.println(bst.select(2));
        System.out.println(bst.select(3));
        System.out.println("min:" + bst.min() + " max:" + bst.max());
        System.out.println("floor b:" + bst.floor("b"));
        System.out.println("floor c:" + bst.floor("c"));
        System.out.println("ceiling b:" + bst.ceiling("b"));
        System.out.println("ceiling c:" + bst.ceiling("c"));
        System.out.println("rank b:" + bst.rank("b"));
        System.out.println("rank c:" + bst.rank("c"));
        System.out.println("rank e:" + bst.rank("e"));
    }
}
