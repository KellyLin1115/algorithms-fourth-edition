package chapter2.item4;


public class Heap {

    private Heap(){}

    public static void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n/2 - 1; k >= 0 ; k--) {
            sink(pq, k, n - 1);
        }
        for (int i = n-1; i >= 1 ; i--) {
            exch(pq, 0, i);
            sink(pq, 0, i-1);
        }
    }


    private static void sink(Comparable[] pq, int k, int n) {
       while(2*k < n){
           int j = 2*k + 1;
           if(j < n && less(pq, j,j+1)) j = j+1;
           if(less(pq, j, k)) break;
           exch(pq, j, k);
           k = j;
       }
    }


    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {8, 3, 12, 3, 4, 89, 11, 11, 0};
        Heap.sort(a);
        show(a);
    }

}
