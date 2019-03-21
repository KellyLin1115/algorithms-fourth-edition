package chapter2.item4;

import java.util.NoSuchElementException;
import java.util.Random;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public void insert(Key x) {
        if(n == pq.length) throw new ArrayIndexOutOfBoundsException();
        pq[n++] = x;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key deleteMax(){
        if(isEmpty()) throw new NoSuchElementException();
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (pq[max].compareTo(pq[i]) < 0) max = i;
        }
        Key v = pq[max];
        exch(max, --n);
        pq[n] = null;
        return v;
    }

    private void exch(int i, int j){
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static void main(String[] args) {
        UnorderedArrayMaxPQ<Integer> maxPQ = new UnorderedArrayMaxPQ<>(20);
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            Integer item = rand.nextInt(100);
            System.out.print(item + " ");
            maxPQ.insert(item);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            Integer item = maxPQ.deleteMax();
            System.out.print(item + " ");
        }
    }
}
