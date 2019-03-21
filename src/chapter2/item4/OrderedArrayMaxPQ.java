package chapter2.item4;

import java.util.NoSuchElementException;
import java.util.Random;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public OrderedArrayMaxPQ(int capacity){
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public void insert(Key x){
        if(n == pq.length) throw new ArrayIndexOutOfBoundsException();
        int i = n - 1;
        while(i >= 0 && x.compareTo(pq[i]) < 0){
            pq[i+1] = pq[i];
            i--;
        }
        pq[i+1] = x;
        n++;
    }

    public Key deleteMax() {
        if(isEmpty()) throw new NoSuchElementException();
        Key item = pq[n-1];
        pq[n-1] = null;
        n--;
        return item;
    }


    public static void main(String[] args) {
        OrderedArrayMaxPQ<Integer> maxPQ = new OrderedArrayMaxPQ<>(20);
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
