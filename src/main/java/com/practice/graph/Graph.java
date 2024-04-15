package main.java.com.practice.graph;

import java.util.*;

public class Graph {
    HashMap<String, ArrayList<String>> adjacencyList = new HashMap<>();

    public boolean addVertex(String value) {
        if (adjacencyList.containsKey(value)) {
            return false;
        }
        adjacencyList.put(value, new ArrayList<>());
        return true;
    }

    public boolean addEdge(String vertex1, String vertex2) {
        if (adjacencyList.get(vertex1) != null && adjacencyList.get(vertex2) != null) {
            adjacencyList.get(vertex1).add(vertex2);
            adjacencyList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertex1, String vertex2) {
        if (adjacencyList.get(vertex1) != null && adjacencyList.get(vertex2) != null) {
            adjacencyList.get(vertex1).remove(vertex2);
            adjacencyList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeVertex(String vertex) {
        if (adjacencyList.get(vertex) != null) {
            for (String otherVertex : adjacencyList.get(vertex)) {
                adjacencyList.get(otherVertex).remove(vertex);
            }
            adjacencyList.remove(vertex);
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////

    public void simpleBFS(String startVertex) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);

        while(!queue.isEmpty()){
            String current = queue.poll();
            for(String neighbor: adjacencyList.get(current)){
                if(!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public void recursiveBFS(String startVertex) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);
        recursiveBFSHelper(queue, visited);
    }

    private void recursiveBFSHelper(Queue<String> queue, Set<String> visited) {
        if (queue.isEmpty()) {
            return;
        }

        String currentVertex = queue.poll();
        System.out.print(currentVertex + " ");

        for (String neighbor : adjacencyList.get(currentVertex)) {
            if (!visited.contains(neighbor)) {
                queue.offer(neighbor);
                visited.add(neighbor);
            }
        }

        recursiveBFSHelper(queue, visited);
    }

    public List<String> shortestPathBFS(String start, String end) {
        if(!adjacencyList.containsKey(start) || !adjacencyList.containsKey(end)){
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        while(!queue.isEmpty()){
            String current = queue.poll();
            if (current.equals(end)) {
                break;
            }

            for(String neighbor: adjacencyList.get(current)){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }

        List<String> shortestPath = new ArrayList<>();
        String current = end;
        while(current!=null){
            shortestPath.add(current);
            current = parent.get(current);
        }

        return shortestPath;
    }

    ///////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////

    public void simpleDFS(String startVertex) {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(startVertex);
        visited.add(startVertex);

        while(!stack.isEmpty()){
            String current = stack.pop();
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public void recursiveDFS(String startVertex) {
        Set<String> visited = new HashSet<>();
        recursiveDFSHelper(startVertex, visited);
    }

    private void recursiveDFSHelper(String currentVertex, Set<String> visited) {
        visited.add(currentVertex);

        for (String neighbor : adjacencyList.get(currentVertex)) {
            if (!visited.contains(neighbor)) {
                recursiveDFSHelper(neighbor, visited);
            }
        }
    }

    public boolean hasLoop(){
        Set<String> visited = new HashSet<>();
        for (String current : adjacencyList.keySet()) {
            if (hasLoopUtil(current, visited, null)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasLoopUtil(String current, Set<String> visited, String parent) {
        if (visited.contains(current)) {
            return true;
        }
        visited.add(current);
        for (String neighbor : adjacencyList.get(current)) {
            if (!neighbor.equals(parent) && hasLoopUtil(neighbor, visited, current)) {
                return true;
            }
        }
        visited.remove(current);
        return false;
    }
}
