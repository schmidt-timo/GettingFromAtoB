package Working;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WeightedGraph {
    Map<String, Vertex> adjList = new HashMap<>();

    void addVertex(String name);
    void addEdge(String src, String dst, int weight);

    void printGraph();

    List getCheapestPath(String start, String end);
    List getShortestPath(String start, String end);
}

