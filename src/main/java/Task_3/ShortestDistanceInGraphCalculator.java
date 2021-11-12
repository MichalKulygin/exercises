package Task_3;

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
class ShortestDistanceInGraphCalculator {
    private final Node start;
    private final Node end;
    private final Map<Node, Integer> distanceMap;
    private int result = -1;

    private ShortestDistanceInGraphCalculator(Graph graph, Node start, Node end) {
        this.start = start;
        this.end = end;
        this.distanceMap = initializeDistanceMap(graph);
    }

    public static ShortestDistanceInGraphCalculator fromFile(ShortestDistanceInGraphFileReader graphDataFromFile) {
        Graph graph = graphDataFromFile.getGraph();
        Node start = graphDataFromFile.getStartNode();
        Node end = graphDataFromFile.getEndNode();

        return new ShortestDistanceInGraphCalculator(graph, start, end);
    }

    private Map<Node, Integer> initializeDistanceMap(Graph graph) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        for (Node node : graph.getNodeList()) {
            distanceMap.put(node, 0);
        }
        return distanceMap;
    }

    void countShortestDistance() {
        Queue<Node> queue = new LinkedList<>();
        Node startNode = start;

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

    public int getResult() {
        return result;
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
