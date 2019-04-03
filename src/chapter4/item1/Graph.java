package chapter4.item1;

import chapter1.item3.Bag;

import java.util.Stack;

public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        if(V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];
        for (int i = 0; i < V; i++){
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(Graph G){
        this(G.V());
        this.E = G.E();
        Stack<Integer> inverse = new Stack<>();
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj[v])
                inverse.add(w);
            for (int w : inverse)
                adj[v].add(w);
        }

    }


    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    private void validateVertex(int v) {
        if(v < 0 || v >= V) throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int v = 0; v < V; v++){
            result.append("v : ");
            for (int w : adj[v]){
                result.append(w + " ");
            }
            System.out.println();
        }
        return result.toString();
    }

}
