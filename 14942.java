import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] energies = new int[n + 1];
        Graph graph = new Graph(n + 1);

        for (int i = 1; i < n + 1; i++) {
            int energy = Integer.parseInt(br.readLine());
            energies[i] = energy;
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.union(v1, v2, w);
        }

        ReverseTree tree = graph.makeTree();

        for (int node = 1; node < n + 1; node++) {
            System.out.println(tree.moveToParent(node, energies[node]));
        }
    }
}

class Graph {
    private final List<EnergyNode>[] graph;
    boolean[] visited;

    public Graph(int n) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n];
    }

    public void union(int v1, int v2, int w) {
        graph[v1].add(new EnergyNode(v2, w));
        graph[v2].add(new EnergyNode(v1, w));
    }

    public ReverseTree makeTree() {
        visited = new boolean[graph.length];
        ReverseTree tree = new ReverseTree(graph.length);
        makeTree(1, tree);
        return tree;
    }
    private void makeTree(int currNode, ReverseTree tree) {
        visited[currNode] = true;
        for (EnergyNode next : graph[currNode]) {
            if (visited[next.node]) {
                continue;
            }
            tree.setParent(next.node, currNode, next.energy);
            makeTree(next.node, tree);
        }
    }
}

class ReverseTree {
    private final EnergyNode[] tree;

    public ReverseTree(int n) {
        tree = new EnergyNode[n];
    }

    public void setParent(int node, int parent, int energy) {
        if (node > tree.length - 1) {
            return;
        }
        tree[node] = new EnergyNode(parent, energy);
    }

    public int moveToParent(int node, int leftEnergy) {
        if (tree[node] == null || leftEnergy < tree[node].energy) {
            return node;
        }
        int parentNode = tree[node].node;
        int needEnergy = tree[node].energy;
        return moveToParent(parentNode, leftEnergy - needEnergy);
    }
}

class EnergyNode {
    int node;
    int energy;

    public EnergyNode(int node, int energy) {
        this.node = node;
        this.energy = energy;
    }
}
