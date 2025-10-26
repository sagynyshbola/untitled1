package graph;

import java.util.*;

/**
 * Graph: undirected weighted graph.
 * Internal representation: adjacency list and an edges list (unique Edge objects).
 * Provides constructors: empty and one that accepts raw JSON input lists (nodes + edges).
 */
public class Graph {
    private final Map<String, List<Edge>> adj = new LinkedHashMap<>();
    private final List<Edge> edges = new ArrayList<>();

    // default constructor
    public Graph() { }

    /**
     * Construct graph from JSON model parts:
     * @param nodes list of node ids (String)
     * @param edgesRaw list of maps with keys: from, to, weight
     */
    public Graph(List<String> nodes, List<Map<String, Object>> edgesRaw) {
        if (nodes != null) {
            for (String n : nodes) addNode(n);
        }
        if (edgesRaw != null) {
            for (Map<String, Object> m : edgesRaw) {
                String from = (String) m.get("from");
                String to = (String) m.get("to");
                double w = ((Number) m.get("weight")).doubleValue();
                addEdge(from, to, w);
            }
        }
    }

    public void addNode(String v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String u, String v, double w) {
        addNode(u);
        addNode(v);
        Edge e = new Edge(u, v, w);
        adj.get(u).add(e);
        adj.get(v).add(e);
        edges.add(e);
    }

    public Set<String> nodes() {
        return Collections.unmodifiableSet(adj.keySet());
    }

    public List<Edge> edges() {
        return Collections.unmodifiableList(edges);
    }

    public List<Edge> neighbors(String v) {
        return Collections.unmodifiableList(adj.getOrDefault(v, Collections.emptyList()));
    }

    public int vertexCount() {
        return adj.size();
    }

    public int edgeCount() {
        return edges.size();
    }

    public boolean isConnected() {
        if (adj.isEmpty()) return true;
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        String start = adj.keySet().iterator().next();
        stack.push(start);
        while (!stack.isEmpty()) {
            String u = stack.pop();
            if (!visited.add(u)) continue;
            for (Edge e : neighbors(u)) {
                String w = e.other(u);
                if (!visited.contains(w)) stack.push(w);
            }
        }
        return visited.size() == adj.size();
    }
}
