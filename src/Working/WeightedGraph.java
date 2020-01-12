package Working;

import java.util.List;

public interface WeightedGraph {

    void addVertex(String name);

    void addEdge(String src, String dst, int weight);

    void printGraph();

    List getCheapestPath(String start, String end);

    List getShortestPath(String start, String end);

}

