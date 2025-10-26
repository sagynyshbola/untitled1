package data;

import java.util.List;

/**
 * Represents the entire JSON input file structure.
 * Example input JSON:
 * {
 *   "graphs": [
 *     {
 *       "id": 1,
 *       "nodes": ["A","B","C"],
 *       "edges": [
 *         {"from": "A", "to": "B", "weight": 1.0},
 *         {"from": "B", "to": "C", "weight": 2.0}
 *       ]
 *     }
 *   ]
 * }
 */
public class InputFile {
    public List<GraphModel> graphs;
}
