package chapter4.item1;

import chapter1.item3.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        validateVertex(s);
        dfp(g, s);
    }

    private void dfp(Graph g, int v){
        marked[v] = true;
        for (int w : g.adj(v)){
            if(!marked[w]) {
                edgeTo[w] = v;
                dfp(g, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        validateVertex(v);
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> paths = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x] ){
            paths.push(x);
        }
        paths.push(s);
        return paths;
    }

}
