package graph;

public class Edge implements Comparable<Edge> {
    public final String from;
    public final String to;
    public final double weight;

    public Edge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String other(String v) {
        if (v.equals(from)) return to;
        if (v.equals(to)) return from;
        throw new IllegalArgumentException("Vertex not part of edge: " + v);
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%s-%s(%.2f)", from, to, weight);
    }
}
