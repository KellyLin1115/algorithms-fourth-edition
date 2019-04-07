package chapter4.item1;

public class DepthFirstSearch {
    private boolean marked[];
    private int count;

    public DepthFirstSearch(Graph g, int s){
        marked = new boolean[g.V()];
        validateVertex(s);
        count = 0;
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        count++;
        marked[v] = true;
        for (int w: g.adj(v)){
            if(!marked[w])  dfs(g, w);
        }
    }

    public int count(){
        return count;
    }

    public boolean marked(int v){
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
