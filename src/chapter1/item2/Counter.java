package chapter1.item2;

import java.util.Random;

public class Counter implements Comparable<Counter> {

    private final String name;
    private int count;

    public Counter(String id) {
        name = id;
    }

    public void increment() {
        count++;
    }

    public int tally() {
        return count;
    }

    @Override
    public int compareTo(Counter that) {
        if(this.count < that.count) return -1;
        else if(this.count > that.count) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        Random rand = new Random();
        int n = rand.nextInt(40);
        System.out.println("N: " + n);

        for (int i = 0; i < n; i++) {
            int number = rand.nextInt(20);
            System.out.println(number + " ");
            if(number > 10) heads.increment();
            else tails.increment();
        }
        System.out.println();

        System.out.println("heads: " + heads.tally() + " tails: " + tails.tally());
    }
}
