package algorithms;

import data.MSTResult;
import graph.Edge;
import graph.Graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Prim algorithm implementation using a min-heap (PriorityQueue).
 */
public class Prim {
    private final Graph graph;

    public Prim(Graph graph) {
        this.graph = graph;
    }

    public MSTResult findMST() {
        MSTResult res = new MSTResult();
        if (graph.vertexCount() == 0) return res;

        long start = System.nanoTime();
        long ops = 0;

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String startV = graph.nodes().iterator().next();
        visited.add(startV);
        for (Edge e : graph.neighbors(startV)) {
            pq.add(e);
            ops++;
        }

        while (!pq.isEmpty() && res.mstEdges.size() < graph.vertexCount() - 1) {
            Edge e = pq.poll(); ops++;
            String a = e.from;
            String b = e.to;
            String next = null;
            if (visited.contains(a) && !visited.contains(b)) next = b;
            else if (visited.contains(b) && !visited.contains(a)) next = a;
            ops++;
            if (next == null) continue;
            res.addEdge(e);
            visited.add(next);
            for (Edge ne : graph.neighbors(next)) {
                String other = ne.other(next);
                ops++;
                if (!visited.contains(other)) {
                    pq.add(ne);
                    ops++;
                }
            }
        }

        long end = System.nanoTime();
        res.executionTimeMs = (end - start) / 1_000_000.0;
        res.operationsCount = ops;
        return res;
    }
}
