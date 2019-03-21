package chapter2.item4;

import java.util.*;

public class MaxPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int capacity) {
        pq = (Key[]) new Object[capacity+1];
        n = 0;
        comparator = null;
    }

    public MaxPQ(int capacity, Comparator<Key> comparator) {
        this(capacity);
        this.comparator = comparator;
    }

    public MaxPQ(Key[] a){
        int n = a.length;
        pq = (Key[]) new Object[n+1];
        this.n = n;
        for (int i = 0; i < n; i++) {
            pq[i + 1] = a[i];
        }
        for (int k = n/2; k >= 1 ; k--) {
            sink(k);
        }
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
        this.comparator = comparator;
    }

    public MaxPQ(Key[] a, Comparator<Key> comparator){
        this(a);
        this.comparator = comparator;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public Key max() {
        if(isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    public void insert(Key key) {
        if(n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = key;
        swim(n);
    }

    public Key delMax() {
        if(isEmpty()) throw new NoSuchElementException();
        Key item = pq[1];
        exch(1, n);
        pq[n] = null;
        n--;
        sink(1);
        if((n > 0) && (n == (pq.length -1)/4)) resize(pq.length/2);
        return item;
    }

    public Iterator<Key> iterator(){
        return new PQIterator();
    }

    private class PQIterator implements Iterator<Key>{
        private MaxPQ<Key> copy;
        public PQIterator(){
            copy = new MaxPQ<>(n);
            for (int i = 0; i < n; i++) {
                copy.pq[i+1] = pq[i+1];
            }
            copy.n = n;
            copy.comparator = comparator;
        }

        public boolean hasNext(){
            return !copy.isEmpty();
        }

        public Key next() {
            if(!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    private void sink(int k) {
        while(2*k <= n){
            int j = 2 * k;
            if(j < n && less(j, j + 1)) j = j + 1;
            if(less(k, j)) {
                exch(k, j);
                k = j;
            } else break;
        }
    }

    private void swim(int k) {
        while(k > 1){
            int j = k/2;
            if(less(j, k)) {
                exch(k, j);
                k = k/2;
            }else break;
        }
    }

    private boolean less(int i ,int j) {
        if(comparator == null){
            return ((Comparable<Key>)pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private void resize(int capacity) {
        Key[] tmp = (Key[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tmp[i+1] = pq[i+1];
        }
        pq = tmp;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Integer[] ls = new Integer[10];
        System.out.println("Array:");
        for (int i = 0; i < 10; i++) {
            ls[i] = rand.nextInt(100);
            System.out.print(ls[i] + " ");
        }
        MaxPQ<Integer> maxPQ = new MaxPQ<>(ls);
        System.out.println();
        System.out.println("MaxPQ:");
        for (Integer item : maxPQ) {
            System.out.print(item + " ");
        }
        System.out.println();

        maxPQ.insert(0);
        maxPQ.insert(100);

        System.out.println("MaxPQ after add:");
        for (Integer item : maxPQ) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println(maxPQ.delMax() + " " + maxPQ.delMax());
        System.out.println();
        for (Integer item : maxPQ) {
            System.out.print(item + " ");
        }
    }

}