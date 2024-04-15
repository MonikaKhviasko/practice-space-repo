package main.java.com.practice.graph;

import java.util.*;

public class WGraph {
    HashMap<String, Map<String, Integer>> adjacencyList = new HashMap<>();

    public boolean addVertex(String value) {
        if (adjacencyList.containsKey(value)) {
            return false;
        }
        adjacencyList.put(value, new HashMap<>());
        return true;
    }

    public boolean addEdge(String vertex1, String vertex2, int weight) {
        if (adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
            adjacencyList.get(vertex1).put(vertex2, weight);
            adjacencyList.get(vertex2).put(vertex1, weight);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertex1, String vertex2) {
        if (adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
            adjacencyList.get(vertex1).remove(vertex2);
            adjacencyList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeVertex(String vertex) {
        if (adjacencyList.containsKey(vertex)) {
            for (String adjacentVertex : adjacencyList.get(vertex).keySet()) {
                adjacencyList.get(adjacentVertex).remove(vertex);
            }
            adjacencyList.remove(vertex);
            return true;
        }
        return false;
    }

    class VertexDistance {
        String vertex;
        int distance;

        public VertexDistance(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public Map<String, Integer> dijkstra(String start, String end) {
        // Initialize distances to all vertices as infinity, except the start vertex
        Map<String, Integer> distances = new HashMap<>();
        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // Priority queue to store vertices based on their distances
        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(vertexDistance -> vertexDistance.distance));
        priorityQueue.offer(new VertexDistance(start, 0));

        // Set to keep track of visited vertices
        Set<String> visited = new HashSet<>();

        // Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            VertexDistance current = priorityQueue.poll();
            String currentVertex = current.vertex;

            if (currentVertex.equals(end)) {
                break; // Exit loop if end vertex is reached
            }

            if (visited.contains(currentVertex)) {
                continue; // Skip if already visited
            }

            visited.add(currentVertex);

            // Update distances to adjacent vertices
            for (Map.Entry<String, Integer> neighborEntry : adjacencyList.get(currentVertex).entrySet()) {
                String neighbor = neighborEntry.getKey();
                int distanceToNeighbor = neighborEntry.getValue();

                int newDistance = distances.get(currentVertex) + distanceToNeighbor;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    priorityQueue.offer(new VertexDistance(neighbor, newDistance));
                }
            }
        }
        return distances;
    }

    public Map<String, Integer> dijkstra(String start) {
        // Initialize distances to all vertices as infinity, except the start vertex
        Map<String, Integer> distances = new HashMap<>();
        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // Priority queue to store vertices based on their distances
        PriorityQueue<VertexDistance> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(vertexDistance -> vertexDistance.distance));
        priorityQueue.offer(new VertexDistance(start, 0));

        // Set to keep track of visited vertices
        Set<String> visited = new HashSet<>();

        // Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            VertexDistance current = priorityQueue.poll();
            String currentVertex = current.vertex;

            if (visited.contains(currentVertex)) {
                continue; // Skip if already visited
            }

            visited.add(currentVertex);

            // Update distances to adjacent vertices
            for (Map.Entry<String, Integer> neighborEntry : adjacencyList.get(currentVertex).entrySet()) {
                String neighbor = neighborEntry.getKey();
                int distanceToNeighbor = neighborEntry.getValue();

                int newDistance = distances.get(currentVertex) + distanceToNeighbor;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    priorityQueue.offer(new VertexDistance(neighbor, newDistance));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        WGraph graph = new WGraph();

        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Add edges with weights
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("B", "D", 10);
        graph.addEdge("C", "D", 3);
        graph.addEdge("C", "E", 8);
        graph.addEdge("D", "E", 7);

        // Perform Dijkstra's algorithm from vertex "A"
        String startVertex = "A";
        Map<String, Integer> distances = graph.dijkstra(startVertex, "D");

        // Output the distances from startVertex to other vertices
        System.out.println("Shortest distances from vertex " + startVertex + ":");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
