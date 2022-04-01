package adidas.flitetrakr.graph.search;

import adidas.flitetrakr.graph.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class GraphSearch {

    private static final String SEPARATOR_ANSWER = "-";

    public static Optional<Integer> sumWeightPath(LinkedList<String> path, Graph graph) {
        int totalCost = 0;
        LinkedList<String> queue = new LinkedList<>();
        String rootNode = path.pollFirst();
        queue.add(rootNode);

        while (queue.size() != 0 && path.size() != 0) {
            // deque an entry from queue and process it
            String visitedNode = queue.poll();
            // get all adjacent nodes of current node and process
            for (Graph.Node aNode : graph.adjList.getOrDefault(visitedNode, List.of())) {
                if (aNode.value().equals(path.getFirst())) {
                    path.pollFirst();
                    queue.add(aNode.value());
                    totalCost += aNode.weight();
                }
            }
        }
        if (path.size() == 0) return Optional.of(totalCost);
        else return Optional.empty();
    }

    public static Optional<String> cheapestConnection(String source, String destination, Graph graph) {

        record NodeSearch(String node, Set<String> visited, Integer currentCost, String path) {
        }
        Optional<Integer> minimumCost = Optional.empty();
        Optional<String> resultPath = Optional.empty();
        // BFS queue
        LinkedList<NodeSearch> queue = new LinkedList<>();
        queue.add(new NodeSearch(source, new HashSet<>(), 0, source));
        while (queue.size() != 0) {
            // deque an entry from queue and process it
            NodeSearch visitedNode = queue.poll();
            if (visitedNode.node.equals(destination)) {
                if (minimumCost.isEmpty() || visitedNode.currentCost < minimumCost.get()
                        || minimumCost.get() == 0) {
                    minimumCost = Optional.of(visitedNode.currentCost);
                    resultPath = Optional.of(visitedNode.path);
                }
            }
            // get all adjacent nodes of current node and process
            for (Graph.Node aNode : graph.adjList.getOrDefault(visitedNode.node, List.of())) {
                if (!visitedNode.visited.contains(aNode.value())) {
                    Set<String> visited = new HashSet<>(visitedNode.visited);
                    Integer currentCost = visitedNode.currentCost;
                    String path = visitedNode.path + SEPARATOR_ANSWER + aNode.value();
                    currentCost += aNode.weight();
                    visited.add(aNode.value());
                    queue.add(new NodeSearch(aNode.value(), visited, currentCost, path));
                }
            }
        }
        if (minimumCost.isPresent())
            return Optional.of(resultPath.get() + SEPARATOR_ANSWER + minimumCost.get());
        else return Optional.empty();
    }

    public enum FunctionSearch {
        MAX, MIN, EQUAL
    }

    public static int searchConnectionsStop(String source,
                                            String destination,
                                            Graph graph,
                                            int threshold,
                                            FunctionSearch function) {
        record NodeSearch(String node, Set<String> visited, int stops, String path) {
        }
        int searchCount = 0;
        // BFS queue
        LinkedList<NodeSearch> queue = new LinkedList<>();
        Set<String> uniqueRoutes = new HashSet<>();
        queue.add(new NodeSearch(source, new HashSet<>(), 0, source));
        while (queue.size() != 0) {
            // deque an entry from queue and process it
            NodeSearch visitedNode = queue.poll();

            if (visitedNode.node.equals(destination)) {
                switch (function) {
                    case MAX -> {
                        if (visitedNode.stops - 1 <= threshold) searchCount++;
                    }
                    case MIN -> {
                        if (visitedNode.stops - 1 >= threshold) searchCount++;
                    }
                    case EQUAL -> {
                        if (threshold == visitedNode.stops - 1) searchCount++;
                    }
                }
            }
            // get all adjacent nodes of current node and process
            for (Graph.Node aNode : graph.adjList.getOrDefault(visitedNode.node, List.of())) {
                Set<String> visited = new HashSet<>(visitedNode.visited);
                int currentStops = visitedNode.stops;
                currentStops += 1;
                String path = visitedNode.path + SEPARATOR_ANSWER + aNode.value();
                if (visitedNode.visited.contains(aNode.value())) {
                    // interrupt cycle
                    if (uniqueRoutes.contains(path)) continue;
                    else {
                        uniqueRoutes.add(path);
                        path = visitedNode.node + SEPARATOR_ANSWER + aNode.value();
                    }
                }
                visited.add(aNode.value());
                queue.add(new NodeSearch(aNode.value(), visited, currentStops, path));
            }
        }
        return searchCount;
    }


    public static Optional<String> searchConnectionsBelowPrice(String source,
                                                           String destination,
                                                           Graph graph,
                                                           int threshold) {
        record NodeSearch(String node, Set<String> visited, String completePath, String path, int cost) {
        }
        record PathCost(String completePath, int cost){}
        String responseDelimiter = ", ";

        List<PathCost> result = new ArrayList<>();
        // BFS queue
        LinkedList<NodeSearch> queue = new LinkedList<>();
        Set<String> uniqueRoutes = new HashSet<>();
        queue.add(new NodeSearch(source, new HashSet<>(), source, source, 0));
        while (queue.size() != 0) {
            // deque an entry from queue and process it
            NodeSearch visitedNode = queue.poll();
            if (visitedNode.node.equals(destination)) {
                if (visitedNode.cost < threshold)
                    result.add(new PathCost(visitedNode.completePath
                            + SEPARATOR_ANSWER + visitedNode.cost, visitedNode.cost));
            }
            // get all adjacent nodes of current node and process
            for (Graph.Node aNode : graph.adjList.getOrDefault(visitedNode.node, List.of())) {
                Set<String> visited = new HashSet<>(visitedNode.visited);
                int currentCost = visitedNode.cost;
                currentCost += aNode.weight();
                String path = visitedNode.path + SEPARATOR_ANSWER + aNode.value();
                String completePath = visitedNode.completePath + SEPARATOR_ANSWER + aNode.value();
                if (visitedNode.visited.contains(aNode.value())) {
                    // interrupt cycle
                    if (uniqueRoutes.contains(path)) continue;
                    else {
                        uniqueRoutes.add(path);
                        path = visitedNode.node + SEPARATOR_ANSWER + aNode.value();
                    }
                }
                visited.add(aNode.value());
                queue.add(new NodeSearch(aNode.value(), visited,
                        completePath, path, currentCost));
            }
        }
        if(result.size() == 0) return Optional.empty();
        else return Optional.of(result.stream()
                .sorted(Comparator.comparing(PathCost::cost))
                .map(it -> it.completePath)
                .collect(Collectors.joining(responseDelimiter)));
    }

}
