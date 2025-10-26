package data;

import java.util.List;
import java.util.Map;

/**
 * Matches one graph object in the input JSON.
 */
public class GraphModel {
    public int id;
    public List<String> nodes;
    public List<Map<String, Object>> edges;
}
