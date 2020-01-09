/**
 * @author: Timo Schmidt, Tony Schultze, Kenneth Englisch
 * @version: 2019-01-09
 */

import java.util.List;
import java.util.Random;

public class Graph implements WeightedGraph {

    Random rand = new Random();

    adjList array[];
    int V;

    Graph(int vertices, int edges, boolean random) {

        // Create a new list
        array = new adjList[vertices];
        V = vertices;

        // Create another list inside of the Array
        // Set head to null
        for (int i = 0; i < vertices; i++) {
            array[i] = new adjList();
            array[i].head = null;
        }

        if (random)
            generateRandomGraph();
    }

    public void generateRandomGraph() {
        for (int i = 1; i <= V; i++) {
            char randomVertex  = (char)(rand.nextInt(26) + 'A');
            char randomEdge = (char)(rand.nextInt(26) + 'A');
            int randomWeight = rand.nextInt(20);
            addEdge(randomVertex, randomEdge, randomWeight);
        }
    }

    public void addEdge(char src, char dest, int weight) {
        // Create a new edge with source/destination/weight
        Edge edge = new Edge(src, dest, weight);

        //  add this node to the adjList
        int source = (int) src - 65;
        edge.next = array[source].head;
        array[source].head = edge;

    }

    public List getCheapestPath(Vertex source, Vertex destination) {
        return null;
    }

    public List getShortestPath(Vertex source, Vertex destination) {
        return null;
    }

    @Override
    public int getWeight(char vertex) {
        return 0;
    }

    public static void main(String[] args) {
/*        Graph graph = new Graph(3, 5);
        graph.addEdge('A', 'B', 1);
        graph.addEdge('A', 'C', 2);
        graph.addEdge('C', 'D', 4);
        graph.printGraph(graph);*/

        Graph graph = new Graph(26, 100, true);
        graph.printGraph();
    }

    // kopiert von
    // https://algorithms.tutorialhorizon.com/graph-representation-adjacency-matrix-and-adjacency-list/
    public void printGraph() {
        int vertex = V;
        Edge ad;
        for (int i = 0; i < vertex; i++) {
            ad = array[i].head;
            if (ad != null) {
                System.out.println("\nNodes connected to Vertex " + ad.source
                        + " are :");
                while (ad != null) {
                    System.out.print("   " + ad.destination + " (Weight:" + ad.weight + ")");
                    ad = ad.next;
                }
            }


        }
    }

    class adjList {
        Edge head;
    }
}
