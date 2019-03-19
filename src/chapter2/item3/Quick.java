package chapter2.item3;

import java.util.Random;

public class Quick {
    private static Random rand = new Random();
    private Quick() {}

    public static void sort(Comparable[] a){
        int n = a.length;
        sort(a, 0, n-1);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int tmp = lo + rand.nextInt(hi-lo+1);
        exch(a, lo, tmp);

        Comparable vo = a[lo];
        int i = lo, j = hi + 1;
        while(true) {
            while(a[++i].compareTo(vo) < 0) {
                if(i == hi) break;
            }

            while(a[--j].compareTo(vo) > 0) {
                if(j == lo) break;
            }

            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) return;
        int k = partition(a, lo, hi);
        sort(a, lo, k-1);
        sort(a, k+1, hi);
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[15];
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(100);
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("After sort: ");
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
