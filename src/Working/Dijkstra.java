package Working;

// Source:
// https://gist.githubusercontent.com/artlovan/a07f29e16ab725f8077157de7abdf125/raw/348f8bae281e75b8f0d7b4dc282f7c78307eb7e7/Dijkstra.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public void computePath(Vertex sourceVertex) {
        sourceVertex.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();

            for (Edge edge : vertex.getEdges()) {
                Vertex v = edge.getTargetVertex();
                double weight = edge.getWeight();
                double minDistance = vertex.getMinDistance() + weight;

                if (minDistance < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPreviousVertex(vertex);
                    v.setMinDistance(minDistance);
                    priorityQueue.add(v);
                }
            }
        }
    }

    public List<Vertex> getCheapestPathTo(Vertex targetVertex) {
        List<Vertex> path = new ArrayList<>();

        for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPreviousVertex()) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }
}
