package chapter4.item1;

public class CC {
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    public CC(Graph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        size = new int[g.V()];
        for (int v = 0; v < g.V(); v++){
            if(!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w);
            }
        }
    }

    public int id(int v){
        validateVertex(v);
        return id[v];
    }

    public int count() {
        return count;
    }

    public int size(int v){
        validateVertex(v);
        return size[id[v]];
    }

    public boolean connected(int w, int v){
        validateVertex(w);
        validateVertex(v);
        return id[w] == id[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
