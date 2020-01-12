package Working;

import java.util.*;

public class Graph implements WeightedGraph {

    private Map<String, Vertex> adjList = new HashMap<>();
    private Random rand = new Random();

    public Graph() {
        // Constructor for manual setup
    }

    // Constructor for Random graphs
    public Graph(int vertices, int edges) {

        adjList = new HashMap<>();

        // Create new List from 0 to 25 (for A to Z), shuffle it and cut it to the desired size
        // (so we have random vertices)
        List<String> allVertices = new ArrayList<>();
        for (char c = 'A'; c < 'Z'; c++)
            allVertices.add(c+"");
        Collections.shuffle(allVertices);
        allVertices.subList(vertices, allVertices.size()).clear();

        // Add all vertices
        for (String s : allVertices)
            addVertex(s);

        HashMap<String, ArrayList> allEdges = new HashMap<>();

        // As long as HashMap does not contain as many elements as edges
        //while (used.size() != edges) {
        int counter = edges;
        while (counter > 0) {
            // Random number between 1 and vertices
            int src = rand.nextInt(vertices);
            int dst = rand.nextInt(vertices);
            int weight = rand.nextInt(10 - 1) + 1;

            String SRC = allVertices.get(src);
            String DST = allVertices.get(dst);

            if (!allEdges.containsKey(SRC)) {
                addEdge(SRC, DST, weight);
                ArrayList a = new ArrayList();
                a.add(DST);
                allEdges.put(SRC, a);
                counter--;
            } else {
                ArrayList b = allEdges.get(SRC);

                if (!b.contains(DST)) {
                    b.add(DST);
                    addEdge(SRC, DST, weight);
                    counter--;
                }
            }
        }
    }

    public void addVertex(String name) {
        Vertex vertex = new Vertex(name);
        adjList.put(name, vertex);
    }

    public void addEdge(String src, String dst, int weight) {
        Vertex source = adjList.get(src);
        Vertex destination = adjList.get(dst);
        source.addNeighbour(new Edge(source, destination, weight));
    }

    public void printGraph() {
        for (String s : adjList.keySet()) {
            System.out.println("Vertex " + s + " is connected to:");
            List<Edge> edges = adjList.get(s).getEdges();
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                Vertex ziel = edge.getTargetVertex();
                // Add Destinations and Weight
                System.out.print("   " + ziel.getName() + " (Weight: " + edge.getWeight() + ")");
            }
            System.out.println();
        }
    }

    public List getCheapestPath(String start, String end) {
        Dijkstra dijkstra = new Dijkstra();
        Vertex START = adjList.get(start);
        Vertex END = adjList.get(end);
        dijkstra.computePath(START);
        return dijkstra.getCheapestPathTo(END);
    }

    // Source: https://raw.githubusercontent.com/zhaohuabing/shortest-path-unweighted-graph-bsf-java/master/src/main/java/com/zhaohuabing/Graph.java
    // changed to fit our version
    @Override
    public List getShortestPath(String start, String end) {
        // key node, value parent
        Map<String, String> parents = new HashMap<String, String>();
        List<Vertex> temp = new ArrayList<Vertex>();

        Vertex startV = adjList.get(start);
        temp.add(startV);
        parents.put(start, null);

        while (temp.size() > 0) {
            Vertex currentNode = temp.get(0);
            List<Edge> neighbors = currentNode.getEdges();

            for (int i = 0; i < neighbors.size(); i++) {
                Vertex neighbor = neighbors.get(i).getTargetVertex();
                String nodeName = neighbor.getName();

                // a node can only be visited once if it has more than one parents
                boolean visited = parents.containsKey(nodeName);
                if (visited) {
                    continue;
                } else {
                    temp.add(neighbor);

                    // parents map can be used to get the path
                    parents.put(nodeName, currentNode.getName());

                    // return the shortest path if end node is reached
                    if (nodeName.equals(end)) {
                        return getPath(parents, end);
                    }
                }
            }
            temp.remove(0);
        }
        return null;
    }

    private List<String> getPath(Map<String, String> parents, String endNodeName) {
        List<String> path = new ArrayList<String>();
        String node = endNodeName;
        while (node != null) {
            path.add(0, node);
            String parent = parents.get(node);
            node = parent;
        }
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "F", 9);
        graph.addEdge("A", "D", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "E", 4);
        graph.addEdge("D", "E", 6);
        graph.addEdge("D", "G", 1);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 2);

        graph.printGraph();
        System.out.println(graph.getCheapestPath("A", "E"));
        System.out.println(graph.getShortestPath("A", "E"));

        System.out.println("--------------------------------");

        Graph randomGraph = new Graph(10, 15);
        randomGraph.printGraph();

    }

/*    public static void main(String[] args) {

        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("A", "E", 4);

        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "D", 8);

        graph.addEdge("C", "B", 1);

        graph.addEdge("D", "F", 6);
        graph.addEdge("D", "G", 4);

        graph.addEdge("E", "G", 2);

        graph.addEdge("G", "H", 8);
        graph.addEdge("G", "F", 10);

        graph.addEdge("H", "I", 11);
        graph.addEdge("F", "I", 7);

        System.out.println(graph.getCheapestPath("A", "I"));
    }*/

}