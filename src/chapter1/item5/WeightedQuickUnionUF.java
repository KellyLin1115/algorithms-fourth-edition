package chapter1.item5;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int n){
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
        count = n;
    }

    public int find(int p) {
        validate(p);
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }

    public boolean isConnected(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if(size[rootP] > size[rootQ]) {
            id[rootQ] = rootP;
            size[rootP] = size[rootQ] + size[rootP];
        } else {
            id[rootP] = rootQ;
            size[rootQ] = size[rootQ] + size[rootP];
        }
        count--;
    }

    public int count() {
        return count;
    }

    private void validate(int p){
        if(p < 0 || p >= id.length) throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (id.length - 1));
    }
    public static void main(String[] args) {
        WeightedQuickUnionUF quf = new WeightedQuickUnionUF(30);
        int[] ap = {10, 8, 2, 10, 13, 11, 3, 1, 14, 13, 6, 7, 19, 5, 17};
        int[] aq = {2, 12, 3, 3, 4, 5, 11, 10, 19, 15, 6, 18, 16, 7, 9};
        for (int i = 0; i < ap.length; i++) {
            if(!quf.isConnected(ap[i], aq[i])) {
                System.out.println(ap[i] + " " + aq[i]);
                quf.union(ap[i], aq[i]);
            }
        }
        System.out.println(quf.count());
    }
}
