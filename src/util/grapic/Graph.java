package util.grapic;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xw on 15-10-26.
 */
public class Graph {
    protected Set<Node> nodes = new HashSet<>();
    public void addNode(Node node){
        this.nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}
