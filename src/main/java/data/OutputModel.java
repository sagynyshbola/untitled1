package data;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Output container. We accumulate results in a list of maps matching your required output JSON format.
 */
public class OutputModel {
    public List<Map<String, Object>> results = new ArrayList<>();

    public void addResult(int graphId, Graph g, MSTResult prim, MSTResult kruskal) {
        Map<String, Object> one = new LinkedHashMap<>();
        one.put("graph_id", graphId);

        Map<String, Integer> istats = new LinkedHashMap<>();
        istats.put("vertices", g.vertexCount());
        istats.put("edges", g.edgeCount());
        one.put("input_stats", istats);

        one.put("prim", resultToMap(prim));
        one.put("kruskal", resultToMap(kruskal));

        results.add(one);
    }

    private Map<String, Object> resultToMap(MSTResult r) {
        Map<String, Object> m = new LinkedHashMap<>();
        List<Map<String, Object>> edges = new ArrayList<>();
        for (Edge e : r.mstEdges) {
            Map<String, Object> em = new LinkedHashMap<>();
            em.put("from", e.from);
            em.put("to", e.to);
            em.put("weight", e.weight);
            edges.add(em);
        }
        m.put("mst_edges", edges);
        m.put("total_cost", r.totalCost);
        m.put("operations_count", r.operationsCount);
        m.put("execution_time_ms", r.executionTimeMs);
        return m;
    }
}
