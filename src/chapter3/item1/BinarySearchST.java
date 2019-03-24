package chapter3.item1;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int n;

    public BinarySearchST(){
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity){
        keys = (Key[])new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        n = 0;
    }

    private void resize(int capacity) {
        if(capacity < n) throw new IllegalArgumentException("New size is smaller than existing items");
        Key[] tmpK = (Key[])new Comparable[capacity];
        Value[] tmpV = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tmpK[i] = keys[i];
            tmpV[i] = values[i];
        }
        keys = tmpK;
        values = tmpV;
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
        if(key == null) throw new IllegalArgumentException("Argument to get() is null");
        if(isEmpty()) return null;
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public int rank(Key key) {
        if(key == null) throw new IllegalArgumentException("Argument to rank() is null");
        int lo = 0, hi = n - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(key.compareTo(keys[mid]) < 0) {
                hi = mid - 1;
            }else if(key.compareTo(keys[mid]) > 0) {
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value value) {
        if(key == null) throw new IllegalArgumentException("First argument to put() is null");
        if(value == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if(i < n && key.compareTo(keys[i]) == 0) {
            values[i] = value;
            return;
        }

        if(keys.length == n) resize(2 * n);
        for (int j = n-1; j >= i ; j--) {
            keys[j+1] = keys[j];
            values[j+1] = values[j];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public void delete(Key key) {
        if(key == null) throw new IllegalArgumentException("Argument to delete() is null");
        if(isEmpty()) return;
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0) {
            for (int j = i; j < n-1; j++) {
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }
            keys[n-1] = null;
            values[n-1] = null;
            n--;
        }
        if(n > 0 && n <= keys.length/4) resize(keys.length/2);
    }

    public void deleteMin(){
        if(isEmpty()) throw new NoSuchElementException();
        delete(min());
    }

    public void deteleMax(){
        if(isEmpty()) throw new NoSuchElementException();
        delete(max());
    }

    public Key min(){
        if(isEmpty()) throw new NoSuchElementException();
        return keys[0];
    }

    public Key max() {
        if(isEmpty()) throw new NoSuchElementException();
        return keys[n-1];
    }

    public Key select(int k) {
        if(k < 0 || k >= size()) throw new IllegalArgumentException();
        return keys[k];
    }

    public Key floor(Key key){
        if(key == null) throw new NoSuchElementException();
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0) return keys[i];
        if(i == 0) return null;
        else return keys[i-1];
    }

    public Key ceiling(Key key) {
        if(key == null) throw new IllegalArgumentException();
        int i = rank(key);
        if(i == n) return null;
        else return keys[i];
    }
}
