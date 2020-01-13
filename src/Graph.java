/*
  @author: Kenneth Englisch, Timo Schmidt, Tony Schultze
 * @version: 2019-01-09
 * Getting from A to B
 */
import java.util.*;

public class Graph implements WeightedGraph {

	Random rand = new Random();
	adjList[] arrayVertex;
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
		Graph graph = new Graph(9, 13, false);
		
		Vertex A = new Vertex(0,0, false);
		Vertex B = new Vertex(1, 0, false);
		Vertex C = new Vertex(2, 0, false);
		Vertex D = new Vertex(3, 0, false);
		Vertex E = new Vertex(4, 0, false);
		Vertex F = new Vertex(5, 0, false);
		Vertex G = new Vertex(6, 0, false);
		Vertex H = new Vertex(7, 0, false);
		Vertex I = new Vertex(8, 0, false);

		graph.addEdge(A, B, 5);
		graph.addEdge(A, C, 2);
		graph.addEdge(A, E, 4);
		
		graph.addEdge(B, D, 3);
		graph.addEdge(C, D, 8);
		
		graph.addEdge(C, B, 1);
		
		graph.addEdge(D, F, 6);
		graph.addEdge(D, G, 4);
		
		graph.addEdge(E, G, 2);
		
		graph.addEdge(G, H, 8);
		graph.addEdge(G, F, 10);
		
		graph.addEdge(H, I, 11);
		graph.addEdge(F, I, 7);

		graph.printGraph();

		graph.getCheapestPath(G, I, graph.arrayVertex);
		//graph.getShortestPath(A, C, graph.arrayVertex);

	}

	public void generateRandomGraph() {
		for (int i = 1; i <= v; i++) {
			int randomSrc = rand.nextInt((26));
			int randomDest = rand.nextInt((26));
			Vertex vSrc = new Vertex(randomSrc, 0, false);
			Vertex vDest = new Vertex(randomDest, 0, false);

			int randomWeight = rand.nextInt(11);
			addEdge(vSrc, vDest, randomWeight);
		}
	}

	public void addEdge(Vertex src, Vertex dest, int weight) {
		// Create a new edge with source/destination/weight
		Edge edge = new Edge(src, dest, weight);

		// add this node to the adjList
		edge.next = arrayVertex[src.data].head;
		arrayVertex[src.data].head = edge;
	}

	public void getCheapestPath(Vertex source, Vertex destination, adjList[] arrayVertex) {
		Dijkstra d = new Dijkstra(source, destination, arrayVertex);
		d.cheapestPath(source, destination);
	}

	public void getShortestPath(Vertex source, Vertex destination, adjList arrayVertex[]) {
		Dijkstra d = new Dijkstra(source, destination, arrayVertex);
	}

	public void printGraph() {
		int vertex = v;
		Edge edge;
		for (int i = 0; i < vertex; i++) {
			edge = arrayVertex[i].head;
			if (edge != null) {
				System.out.println("Edges connected to Vertex " + numberToChar(edge.source.data) + " are :");
				while (edge != null) {
					System.out.print(
							"   " + (numberToChar(edge.destination.data) + " (Weight:" + edge.weight + ")"));
					edge = edge.next;
				}
				System.out.println();
			}
		}
	}

	// This methods returns a 0 for 'A' and 25 for 'Z'
	public int charToNumber(char c) {
		return (int) c - 65;
	}

	// and vice versa
	public char numberToChar(int n) {
		return (char) (n + 65);
	}
}
