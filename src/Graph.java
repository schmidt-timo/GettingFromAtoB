
/**
 * @author: Kenneth Englisch, Timo Schmidt, Tony Schultze
 * @version: 2019-01-09
 * Getting from A to B
 */

import java.util.List;
import java.util.Random;

public class Graph implements WeightedGraph {

	Random rand = new Random();
	adjList arrayVertex[];
	int v;

	public Graph(int vertices, int edges, boolean random) {

		// Create Array of type adjList with the same size as the amount of vertices
		arrayVertex = new adjList[vertices];
		v = vertices;

		// Create another list inside of the Array
		// Set head to null
		for (int i = 0; i < vertices; i++) {
			arrayVertex[i] = new adjList();
			arrayVertex[i].head = null;
		}

		if (random)
			generateRandomGraph();
	}

	public static void main(String[] args) {
		/*
		 * Graph graph = new Graph(3, 5); graph.addEdge('A', 'B', 1); graph.addEdge('A',
		 * 'C', 2); graph.addEdge('C', 'D', 4); graph.printGraph(graph);
		 */

		Graph graph = new Graph(27, 100, true);
		Edge ad = null;
		Vertex source = null;
		while (ad == null)
			ad = graph.arrayVertex[(graph.rand.nextInt((26 - 1) + 1) + 1)].head;
		source = ad.source;
		ad = null;
		Vertex destination = null;
		while (ad == null)
			ad = graph.arrayVertex[(graph.rand.nextInt((26 - 1) + 1) + 1)].head;
		destination = ad.source;

		graph.getCheapestPath(source, destination, graph.arrayVertex);
		// graph.printGraph();
	}

	public void generateRandomGraph() {
		for (int i = 1; i <= v; i++) {
			int randomSrc = rand.nextInt(26 - 1) + 1;
			int randomDest = rand.nextInt(26 - 1) + 1;
			Vertex vSrc = new Vertex(randomSrc);
			Vertex vDest = new Vertex(randomDest);

			// get random value between 1 and 10
			int randomWeight = rand.nextInt(11);
			addEdge(vSrc, vDest, randomWeight);
		}
	}

	public void addEdge(Vertex src, Vertex dest, int weight) {
		// Create a new edge with source/destination/weight
		Edge edge = new Edge(src, dest, weight);

		// add this node to the adjList
		edge.next = arrayVertex[(int) src.data].head;
		arrayVertex[(int) src.data].head = edge;

	}

	public void getCheapestPath(Vertex source, Vertex destination, adjList arrayVertex[]) {
		Dijkstra d = new Dijkstra(source, destination, arrayVertex);
	}

	public List getShortestPath(Vertex source, Vertex destination) {
		return null;
	}

	@Override
	public int getWeight(char vertex) {
		return 0;
	}

	// kopiert von
	// https://algorithms.tutorialhorizon.com/graph-representation-adjacency-matrix-and-adjacency-list/
	public void printGraph() {
		int vertex = v;
		Edge ad;
		for (int i = 0; i < vertex; i++) {
			ad = arrayVertex[i].head;
			if (ad != null) {
				System.out.println(
						"\nNodes connected to Vertex " + ((char) ('A' + ((int) ad.source.data) - 1) + " are :"));
				while (ad != null) {
					System.out.print(
							"   " + ((char) ('A' + ((int) ad.destination.data) - 1) + " (Weight:" + ad.weight + ")"));
					ad = ad.next;
				}
			}
		}
	}
}
