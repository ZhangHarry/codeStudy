package util.grapic.example;

import util.grapic.Edge;
import util.grapic.Graph;
import util.grapic.GraphWriter;
import util.grapic.Node;

/**
 * Created by zhanghr on 2016/12/6.
 */
public class GraphicExample {
    public static void main(String[] args){
        Graph graph = new Graph();
        Node<String> root = new Node("root");
        Node<String> tail = new Node("tail");
        graph.addNode(root);
        graph.addNode(tail);
        Edge<String> edge = new Edge<>(root, tail, "edge");
        root.addEdge(edge);
        GraphWriter.print(graph, "output/test.dot");
    }
}
