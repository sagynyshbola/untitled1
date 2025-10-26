package algorithms;

import data.MSTResult;
import graph.Edge;
import graph.Graph;
import utils.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal algorithm implementation returning MSTResult.
 */
public class Kruskal {
    private final Graph graph;

    public Kruskal(Graph graph) {
        this.graph = graph;
    }

    public MSTResult findMST() {
        MSTResult res = new MSTResult();
        long start = System.nanoTime();

        List<Edge> edgeList = new ArrayList<>(graph.edges());
        Collections.sort(edgeList); // sorts by weight

        // rough estimate of sort comparisons (optional)
        long sortEstimate = (long) (edgeList.size() * (Math.log(edgeList.size() + 1) / Math.log(2) + 1));

        DisjointSet ds = new DisjointSet();
        for (String v : graph.nodes()) ds.makeSet(v);

        long ops = sortEstimate;
        for (Edge e : edgeList) {
            ops++; // consider edge
            String u = e.from;
            String v = e.to;
            String ru = ds.find(u); ops++;
            String rv = ds.find(v); ops++;
            if (!ru.equals(rv)) {
                boolean merged = ds.union(u, v);
                ops += (merged ? 1 : 0);
                res.addEdge(e);
            }
            if (res.mstEdges.size() == graph.vertexCount() - 1) break;
        }

        long end = System.nanoTime();
        res.executionTimeMs = (end - start) / 1_000_000.0;
        res.operationsCount = ops + ds.findCalls + ds.unionCalls + ds.comparisons;
        return res;
    }
}
