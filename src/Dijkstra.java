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
		//select the edges from the wanted vertex out of the array
		neighbour = v[(int) source.data].head;
		
		/*
		 * loop iterates through every edge from the selected vertex
		 * source.data = int of the vertex that gets cast to char
		 * neighbour.destination.data = the destination vertex from the edge that has the selected vertex as source
		 * neighbour.weight = weight of the edge
		 * neighbour becomes null when there are no more entries in the adjList of the vertex
		 */
		while (neighbour != null) {
			System.out.print((char) ('A' + ((int) source.data) - 1) + " -> " + ((char) ('A' + ((int) neighbour.destination.data) - 1) + " (Weight:" + neighbour.weight + ")\n"));
			//previous is null at the first run or when we only have one edge so there is nothing to compare
			if (previous != null) {
				/*
				 * Integer.compare() has three outs: 
				 * 	-1 when the left value is smaller than the right value
				 * 	 0 when both values are equal
				 * 	 1 when the left value is greater than the right value
				 * We check the return of compare()
				 * 	 true when it is -1 since -1 is smaller than 0
				 *   false when it is 0 or 1
				 * If true we safe the left edge value as the smallest weight
				 * else we safe the right edge value as the smallest weight and make sure it becomes previous
				 * so for the next run the smallest weigth gets compared with the next edge
				 */
				if (Integer.compare(previous.weight, neighbour.weight) < 0) {
					smallest = previous;
				} else {
					smallest = neighbour;
					previous = neighbour;
				}
			//if we had no previous edge the first edge becomes the previous edge	
			} else
				previous = neighbour;
			//get the next edge from the adjList
			neighbour = neighbour.next;
		}
		/*
		 * smallest could be null when we only had one edge
		 * since then nothing got compared
		 * if this happens we return the only selected edge which is stored in previous
		 */
		if (smallest != null)
			return smallest.destination;
		else
			return previous.destination;
	}

	public void cheapestPath(Vertex source, Vertex destination) {
	}

}
