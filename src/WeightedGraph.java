import java.util.List;

public interface WeightedGraph {

    void addEdge(Vertex src, Vertex dest, int weight);
    void getCheapestPath(Vertex source, Vertex destination, adjList arrayVertex[]);
    void getShortestPath(Vertex source, Vertex destination, adjList arrayVertex[]);
    String toString();

    int getWeight(char vertex);

}
