package chapter2.item3;

import java.util.Random;

public class Quick3Way {
    private static Random rand = new Random();
    private Quick3Way() {}

    public static void sort(Comparable[] a){
        int n = a.length;
        sort(a, 0, n-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) return;
        int tmp = lo + rand.nextInt(hi-lo+1);
        exch(a, lo, tmp);

        Comparable v = a[lo];
        int i = lo + 1, lt = lo, gt = hi;
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0) {
                exch(a, lt, i);
                i++;
                lt++;
            }else if(cmp == 0){
                i++;
            }else{
                exch(a, i, gt);
                gt--;
            }
        }
        sort(a, lo,lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[17];
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
