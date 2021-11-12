package Task_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ShortestDistanceInGraphFileReader
 *
 * @author MKgn
 */
class ShortestDistanceInGraphFileReader {
    private final Graph graph;
    private final Node startNode;
    private final Node endNode;

    private ShortestDistanceInGraphFileReader(Graph graph, Node start, Node end) {
        this.graph = graph;
        this.startNode = start;
        this.endNode = end;
    }

    public static ShortestDistanceInGraphFileReader readFile(String filePath) {
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

                graph.addNodesAndEdges(node1, node2);
                graph.addNodesAndEdges(node2, node1);
            }

            String[] lastLine = br.readLine().split(" ");

            Node startNode = new Node(lastLine[0]);
            start = graph.getNodeList().get(graph.getNodeList().indexOf(startNode));
            Node endNode = new Node(lastLine[1]);
            end = graph.getNodeList().get(graph.getNodeList().indexOf(endNode));

            return new ShortestDistanceInGraphFileReader(graph, start, end);

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("file interrupted");
    }

    public Graph getGraph() {
        return graph;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }
}
