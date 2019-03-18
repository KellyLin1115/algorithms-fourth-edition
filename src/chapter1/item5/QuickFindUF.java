package chapter1.item5;

public class QuickFindUF {
    private int[] id;
    //Number of components
    private int count;
    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        count = n;
    }

    public boolean isConnected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }
    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if(pID == qID) return;
        for (int i = 0; i < id.length; i++) {
            if(id[i] == qID) id[i] = pID;
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
        QuickFindUF quf = new QuickFindUF(30);
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
