package chapter4.item1;

import chapter1.item3.Queue;

import java.util.Stack;

public class BreadthFirstPaths {
    private static final int INIFITY = Integer.MAX_VALUE;
    private int[] edgeTo;
    private int[] distTo;
    private boolean[] marked;

    public BreadthFirstPaths(Graph g, int s){
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        marked = new boolean[g.V()];
        validateVertex(s);
        bfp(g, s);
    }

    public BreadthFirstPaths(Graph g, Iterable<Integer> sources){
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        marked = new boolean[g.V()];
        validateVertices(sources);
        bfg(g, sources);
    }

    private void bfp(Graph g, int s){
        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < g.V(); i++) {
            distTo[i] = INIFITY;
        }

        distTo[s] = 0;
        marked[s] = true;
        queue.enqueue(s);

        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for (int w: g.adj(v)) {
                if (!marked[v]){
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    private void bfg(Graph g, Iterable<Integer> sources){
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < g.V(); i++) {
            distTo[i] = INIFITY;
        }
        for (int s : sources) {
            distTo[s] = 0;
            marked[s] = true;
            queue.enqueue(s);
        }

        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }

    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0 ; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(x);
        return stack;
    }

    private void validateVertex(int v){
        int n = marked.length;
        if(v < 0 || v >= n) throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (n-1));
    }

    private void validateVertices(Iterable<Integer> vertices){
        if(vertices == null) throw new IllegalArgumentException("Argument is null");
        int n = marked.length;
        for (int v : vertices)
            if(v < 0 || v >= n) throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (n-1));
    }
}
