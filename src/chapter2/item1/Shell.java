package chapter2.item1;

import java.util.Random;

public class Shell {

    private Shell() {}

    public static void sort(Comparable[] a){
        int n = a.length;
        int h = 1;
        while(h < n/3) h = 3*h + 1;
        while(h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (a[j].compareTo(a[j - h]) < 0) exch(a, j, j - h);
                    else break;
                }
            }
            h = h / 3;
        }

    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[12];
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
