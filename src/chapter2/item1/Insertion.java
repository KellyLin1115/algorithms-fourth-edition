package chapter2.item1;

import java.util.Random;

public class Insertion {

    private Insertion() {}

    public static void sort(Comparable[] a){
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if(a[j].compareTo(a[j-1]) < 0) exch(a, j, j-1);
                else break;
            }
        }
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[11];
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
