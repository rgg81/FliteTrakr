package adidas.flitetrakr.graph;

import java.util.*;

// Graph class
public class Graph {
    static record Edge(String src, String dest, int weight) {}
    // node of adjacency list
    public static record Node(String value, int weight) {}

// define adjacency list

    public final Map<String, List<Node>> adjList = new HashMap<>();

    //Graph Constructor
    public Graph(List<Edge> edges) {
        // add edges to the graph
        for (Edge e : edges) {
            if(!adjList.containsKey(e.src)) adjList.put(e.src, new ArrayList<>());
            // allocate new node in adjacency List from src to dest
            adjList.get(e.src).add(new Node(e.dest, e.weight));
        }
    }
    // print adjacency list for the graph
    public void printGraph()  {

        System.out.println("The contents of the graph:");
        this.adjList.forEach((vertex, edges) -> {
            //traverse through the adjacency list and print the edges
            for (Node edge : this.adjList.get(vertex)) {
                System.out.print("Vertex:" + vertex + " ==> " + edge.value +
                        " (" + edge.weight + ")\t");
            }
            System.out.println();
        });
    }
}
