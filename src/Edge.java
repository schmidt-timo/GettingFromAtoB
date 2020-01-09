public class Edge {

    char source;
    char destination;
    int weight;
    Edge next;

    public Edge(char source, char destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}
