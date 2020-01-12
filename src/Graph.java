/*
  @author: Kenneth Englisch, Timo Schmidt, Tony Schultze
 * @version: 2019-01-09
 * Getting from A to B
 */

import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Random;

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

/*	public static void main(String[] args) {
		
		 
		Graph graph = new Graph(26, 100, true);
		Edge ad = null;
		Vertex source = null;
		while (ad == null)
			ad = graph.arrayVertex[(graph.rand.nextInt(26))].head;
		source = ad.source;
		ad = null;
		Vertex destination = null;
		while (ad == null)
			ad = graph.arrayVertex[(graph.rand.nextInt(26))].head;
		destination = ad.source;

		graph.getCheapestPath(source, destination, graph.arrayVertex);
		// graph.printGraph();
		
	}*/

	public static void main(String[] args) {
		Graph graph = new Graph(3, 5, false);

		Vertex A = new Vertex(0);
		Vertex B = new Vertex(1);
		Vertex C = new Vertex(2);

		graph.addEdge(A, B, 4);
		graph.addEdge(A, C, 5);
		graph.addEdge(B, C, 2);
		graph.addEdge(C, B, 2);
		graph.addEdge(C, A, 6);

		graph.printGraph();

		graph.getCheapestPath(A, C, graph.arrayVertex);

	}

	public void generateRandomGraph() {
		for (int i = 1; i <= v; i++) {
			int randomSrc = rand.nextInt((26));
			int randomDest = rand.nextInt((26));
			Vertex vSrc = new Vertex(randomSrc);
			Vertex vDest = new Vertex(randomDest);

			/*
			 * char randomVertex = (char) (rand.nextInt(26) + 'A'); char randomEdge = (char)
			 * (rand.nextInt(26) + 'A');
			 */
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
	}

	public void getShortestPath(Vertex source, Vertex destination, adjList arrayVertex[]) {
		Dijkstra d = new Dijkstra(source, destination, arrayVertex);
		adjList path[] = d.shortestPath(source, destination, arrayVertex);
		for(adjList i : path) {
			System.out.println(path.toString());
		}
	}

	@Override
	public int getWeight(char vertex) {
		return 0;
	}

	public void printGraph() {
		int vertex = v;
		Edge edge;
		for (int i = 0; i < vertex; i++) {
			edge = arrayVertex[i].head;
			if (edge != null) {
				System.out.println("Nodes connected to Vertex " + numberToChar(edge.source.data) + " are :");
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
