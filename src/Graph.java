
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

/*	public static void main(String[] args) {
		*//*
		 * Graph graph = new Graph(3, 5); graph.addEdge('A', 'B', 1); graph.addEdge('A',
		 * 'C', 2); graph.addEdge('C', 'D', 4); graph.printGraph(graph);
		 *//*

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
	}*/

	public static void main(String[] args) {
		Graph graph = new Graph(3, 4, false);

		Vertex A = new Vertex('A');
		Vertex B = new Vertex('B');
		Vertex C = new Vertex('C');

		graph.addEdge(A, B, 4);
		graph.addEdge(B, C, 2);
		graph.addEdge(C, B, 2);
		graph.addEdge(C, A, 6);

		graph.printGraph();
	}

	public void generateRandomGraph() {
		for (int i = 1; i <= v; i++) {
			int randomSrc = rand.nextInt(26 - 1) + 1;	// get random value between 1 and 26 (A to Z)
			int randomDest = rand.nextInt(26 - 1) + 1;	// same here
			Vertex vSrc = new Vertex(numberToChar(randomSrc));
			Vertex vDest = new Vertex(numberToChar(randomDest));

			int randomWeight = rand.nextInt(11);			// get random value between 1 and 10
			addEdge(vSrc, vDest, randomWeight);
		}
	}

	public void addEdge(Vertex src, Vertex dest, int weight) {
		// Create a new edge with source/destination/weight
		Edge edge = new Edge(src, dest, weight);
		int n = charToNumber((char) src.data);

		// add this node to the adjList
		edge.next = arrayVertex[n].head;
		arrayVertex[n].head = edge;

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
						"\nNodes connected to Vertex " + ad.source.data + " are :");
				while (ad != null) {
					System.out.print(
							"   " + ad.destination.data + " (Weight:" + ad.weight + ")");
					ad = ad.next;
				}
			}
		}
	}

	// This methods returns a 1 for 'A' and 26 for 'Z'
	public int charToNumber(char c) {
		return (int) c - 65;
	}

	// and vice versa
	public char numberToChar(int n) {
		return (char) (n + 65);
	}
}
