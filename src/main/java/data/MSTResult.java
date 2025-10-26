package data;

import graph.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for MST results produced by algorithms.
 */
public class MSTResult {
    public List<Edge> mstEdges = new ArrayList<>();
    public double totalCost = 0.0;
    public long operationsCount = 0;
    public double executionTimeMs = 0.0;

    public void addEdge(Edge e) {
        mstEdges.add(e);
        totalCost += e.weight;
    }
}
