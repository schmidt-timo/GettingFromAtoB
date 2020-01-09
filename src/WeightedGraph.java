import java.util.List;

public interface WeightedGraph {

    void addEdge(char src, char dest, int weight);
    List getCheapestPath(Vertex source, Vertex destination);
    List getShortestPath(Vertex source, Vertex destination);
    String toString();

    int getWeight(char vertex);

}
