
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
		Graph graph = new Graph(26, 100, false);
		Edge ad = null;
		
		/*while (ad == null)
			ad = graph.arrayVertex[(graph.rand.nextInt(26))].head;
		source = ad.source;
		ad = null;
		while (ad == null)
		ad = graph.arrayVertex[(graph.rand.nextInt(26))].head;
		destination = ad.source;*/
		
		Vertex a = new Vertex (0);
		Vertex b = new Vertex(1);
		Vertex c = new Vertex(2);
		Vertex d = new Vertex(3);
		Vertex e = new Vertex(4);
		Vertex f = new Vertex(5);
		
		graph.addEdge(a, b, 1);
		graph.addEdge(b, e, 1);
		graph.addEdge(e, f, 1);
		graph.addEdge(b, c,1);
		graph.addEdge(c, d, 1);

		//graph.getCheapestPath(a, f, graph.arrayVertex);
		graph.getShortestPath(a, e, graph.arrayVertex);
		//graph.printGraph();
		
	}

	public void generateRandomGraph() {
		for (int i = 1; i <= v; i++) {
			int randomSrc = rand.nextInt((26));
			int randomDest = rand.nextInt((26));
			Vertex vSrc = new Vertex(randomSrc);
			Vertex vDest = new Vertex(randomDest);
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

	// kopiert von
	// https://algorithms.tutorialhorizon.com/graph-representation-adjacency-matrix-and-adjacency-list/
	public void printGraph() {
		int vertex = v;
		Edge ad;
		for (int i = 0; i < vertex; i++) {
			ad = arrayVertex[i].head;
			if (ad != null) {
				System.out.println(
						"\nNodes connected to Vertex " + ((char) ('A' + ((int) ad.source.data)) + " are :"));
				while (ad != null) {
					System.out.print(
							"   " + ((char) ('A' + ((int) ad.destination.data)) + " (Weight:" + ad.weight + ")"));
					ad = ad.next;
				}
			}
		}
	}
}
