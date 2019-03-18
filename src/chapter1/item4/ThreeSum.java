package chapter1.item4;

import java.util.Random;

public class ThreeSum {
    private ThreeSum(){}

    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if(a[i] + a[j] + a[k] == 0) {
                        count++;
                        System.out.println(a[i] + " " + a[j] + " " + a[k]);
                    }
                }
            }
        }
        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        int[] a = new int[20];
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
