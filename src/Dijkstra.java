import java.util.ArrayList;

public class Dijkstra {

	private Vertex source;
	private Vertex destination;
	private Edge previous;
	private Edge neighbour;
	private Edge smallest;
	private adjList v[];
	private adjList path[];

	public Dijkstra(Vertex source, Vertex destination, adjList vertices[]) {
		this.source = source;
		this.destination = destination;
		this.v = vertices;
		path = new adjList[vertices.length];
		
		//cheapestPath(this.source, this.destination);
		System.out.println((char) ('A' + ((int) getCheapestNeighbour(this.source).data) - 1));
	}

	public static void main(String[] args) {

	}

	public Vertex getCheapestNeighbour(Vertex source) {		
		neighbour = v[(int) source.data].head;
		while (neighbour != null) {
			System.out.print((char) ('A' + ((int) source.data) - 1) + " -> " + ((char) ('A' + ((int) neighbour.destination.data) - 1) + " (Weight:" + neighbour.weight + ")\n"));
			if (previous != null) {
				if (Integer.compare(previous.weight, neighbour.weight) < 0) {
					smallest = previous;
				} else {
					smallest = neighbour;
					previous = neighbour;
				}
			} else
				previous = neighbour;
			neighbour = neighbour.next;
		}
		if (smallest != null)
			return smallest.destination;
		else
			return previous.destination;
	}

	public void cheapestPath(Vertex source, Vertex destination) {
	}

}
