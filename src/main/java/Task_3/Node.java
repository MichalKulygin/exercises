package Task_3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Node - model class
 *
 * @author MKgn
 */
class Node {

    private final String name;
    private final Set<Node> edges;
    private boolean visited;

    public Node(String name) {
        this.name = name;
        this.edges = new HashSet<>();
    }

    public void addEdge(Node edge) {
        this.edges.add(edge);
    }

    public String getName() {
        return name;
    }

    public Set<Node> getEdges() {
        return edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
