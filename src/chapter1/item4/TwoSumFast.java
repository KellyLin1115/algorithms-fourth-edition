package chapter1.item4;

import chapter1.item1.BinarySearch;

import java.util.Arrays;
import java.util.Random;

public class TwoSumFast {

    private TwoSumFast(){}

    public static int count(int[] a) {
        Arrays.sort(a);
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j = BinarySearch.rank(a, -a[i]);
            if(j > i) {
                count++;
                System.out.println(a[i] + " " + a[j]);
            }
        }
        System.out.println(count);
        return count;
    }
    public static void main(String[] args) {
        int[] a = new int[100];
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(100);
            if(rand.nextDouble() >= 0.5) a[i] = (-1) * a[i];
            System.out.print(a[i] + " ");
            if(i != 0 && i % 10 == 0)
                System.out.println();
        }
        System.out.println();
        count(a);
    }
}
