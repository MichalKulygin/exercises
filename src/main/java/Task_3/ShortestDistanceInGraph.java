package Task_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * ShortestDistanceInGraph
 * <p>
 * Counts shortest path from start node to end node.
 * Assumptions:
 * file is not broken xD
 * file contains at least 3 lines -> 1st with parameters (num of nodes, num of edges), next line(s) contain information about edges in graph
 * last line tells the path co calculate (start node, end node)
 *
 * @author MKgn
 */
class ShortestDistanceInGraph {
    private final Graph graph;
    private final Node start;
    private final Node end;
    private final Map<Node, Integer> distanceMap;
    private int result = -1;

    ShortestDistanceInGraph(Graph graph, Node start, Node end, Map<Node, Integer> dm) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        distanceMap = dm;
    }

    static ShortestDistanceInGraph shortestDistanceInGraphFromFile(String filePath) {
        Graph graph = new Graph();
        Node start;
        Node end;
        int numOfEdges;

        try (BufferedReader br = Files.newBufferedReader(Path.of(filePath))) {

            String[] parameters = br.readLine().split(" ");
            numOfEdges = Integer.parseInt(parameters[1]);

            for (int i = 0; i < numOfEdges; i++) {
                String[] line = br.readLine().split(" ");

                Node node1 = new Node(line[0]);
                Node node2 = new Node(line[1]);

                addNodesAndEdges(graph, node1, node2);
                addNodesAndEdges(graph, node2, node1);
            }

            String[] lastLine = br.readLine().split(" ");

            Node startNode = new Node(lastLine[0]);
            start = graph.getNodeList().get(graph.getNodeList().indexOf(startNode));
            Node endNode = new Node(lastLine[1]);
            end = graph.getNodeList().get(graph.getNodeList().indexOf(endNode));

            Map<Node, Integer> distanceMap = initializeDistanceMap(graph);
            return new ShortestDistanceInGraph(graph, start, end, distanceMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("file interrupted");
    }

    private static Map<Node, Integer> initializeDistanceMap(Graph graph) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        for (Node node : graph.getNodeList()) {
            distanceMap.put(node, 0);
        }
        return distanceMap;
    }

    private static void addNodesAndEdges(Graph graph, Node node1, Node node2) {

        Node optionalNode1 = graph.getNodeList().stream().filter(n -> n.equals(node1)).findAny().orElse(node1);
        Node optionalNode2 = graph.getNodeList().stream().filter(n -> n.equals(node2)).findAny().orElse(node2);

        optionalNode1.addEdge(optionalNode2);

        if (graph.getNodeList().stream().noneMatch(n -> n.equals(node1))) {

            graph.addNode(optionalNode1);
        }
    }

    public void countShortestDistance() {
        Queue<Node> queue = new LinkedList<>();
        Node startNode = this.start;

        startNode.setVisited();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentAnalysed = queue.poll();
            for (Node node : currentAnalysed.getEdges()) {
                if (!node.isVisited()) {
                    int newDistance = distanceMap.get(currentAnalysed) + 1;
                    distanceMap.put(node, newDistance);
                    node.setVisited();
                    queue.add(node);
                    if (node.equals(end)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        if (distanceMap.get(end) != 0) {
            result = distanceMap.get(end);
        }
    }

    void displayResults() {

        if (start.equals(end)) {
            System.out.println("Start node is the same one as end node. Path length = 0");
        } else {
            countShortestDistance();
            if (result == -1) {
                System.out.println("Impossible to reach end node. Start node and end node are not connected.");
            } else {
                System.out.println("Counted path length between node: " + start.getName() + " and node: " + end.getName() + " = " + result);
            }
        }
    }
}
