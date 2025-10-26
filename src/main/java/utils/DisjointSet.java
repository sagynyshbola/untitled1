package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple Disjoint Set / Union-Find with path compression and union by rank.
 * Works with String keys (graph vertex ids).
 * Also tracks basic counters (optional).
 */
public class DisjointSet {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    // counters (for optional metrics)
    public long findCalls = 0;
    public long unionCalls = 0;
    public long comparisons = 0;

    public void makeSet(String v) {
        parent.put(v, v);
        rank.put(v, 0);
    }

    public String find(String v) {
        findCalls++;
        String p = parent.get(v);
        if (p == null) return null;
        if (!p.equals(v)) {
            parent.put(v, find(p));
        }
        return parent.get(v);
    }

    public boolean union(String a, String b) {
        unionCalls++;
        String ra = find(a);
        String rb = find(b);
        comparisons++;
        if (ra == null || rb == null) return false;
        if (ra.equals(rb)) return false;
        int rka = rank.getOrDefault(ra, 0);
        int rkb = rank.getOrDefault(rb, 0);
        comparisons++;
        if (rka < rkb) parent.put(ra, rb);
        else if (rka > rkb) parent.put(rb, ra);
        else {
            parent.put(rb, ra);
            rank.put(ra, rka + 1);
        }
        return true;
    }
}
