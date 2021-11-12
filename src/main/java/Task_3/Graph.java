package Task_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph - model class
 *
 * @author MKgn1
 */
class Graph {

    private final List<Node> nodeList;

    Graph() {
        this.nodeList = new ArrayList<>();
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void addNode(Node node) {
        this.nodeList.add(node);
    }

    public void addNodesAndEdges(Node node1, Node node2) {

        Node optionalNode1 = nodeList.stream().filter(n -> n.equals(node1)).findAny().orElse(node1);
        Node optionalNode2 = nodeList.stream().filter(n -> n.equals(node2)).findAny().orElse(node2);

        optionalNode1.addEdge(optionalNode2);

        if (nodeList.stream().noneMatch(n -> n.equals(node1))) {
            this.addNode(optionalNode1);
        }
    }
}
