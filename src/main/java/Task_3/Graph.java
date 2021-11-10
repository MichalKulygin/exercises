package Task_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph - model class
 *
 * @author MKgn
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
}
