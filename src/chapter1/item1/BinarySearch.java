package chapter1.item1;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    private BinarySearch() {}

    public static int rank(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(key > a[mid])
                low = mid + 1;
            else if(key < a[mid])
                high = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        Random rand = new Random();
        System.out.println("Before sort :" );
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(20);
            System.out.print(a[i] + " ");
        }
        System.out.println();

        System.out.println("After sort :" );
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        int key = rand.nextInt(20);
        System.out.println("Key " + key + " rank :" + rank(a, key));
    }
}
