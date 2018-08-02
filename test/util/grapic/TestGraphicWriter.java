package util.grapic;

/**
 * Created by zhanghr on 2016/10/26.
 */
public class TestGraphicWriter {
    public static void main(String[] args){
        Graph graph = new Graph();

        Node<String> root = new Node<>("root");
        Node<Integer> node1 = new Node<>(10);
        Node<Integer> node2 = new Node<>(15);
        Node<Integer> node3 = new Node<>(12);
        Node<Integer> node4 = new Node<>(9);
        node4.setSymbol(null);
        Node<String> node5 = new Node<>("node 5");

        graph.addNode(root);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        Edge<String> e1 = new Edge<>(root, node1, "a", 0);
        Edge<String> e2 = new Edge<>(node1, node3, "b", 0);
        Edge<String> e3 = new Edge<>(node1, node4, "c", 0);
        Edge<String> e4 = new Edge<>(node4, node5, "d", 0);
        Edge<String> e5 = new Edge<>(node5, node2, "e", 0);
        Edge<String> e6 = new Edge<>(root, node2, "f", 0);

        root.addEdge(e1);
        root.addEdge(e6);
        node1.addEdge(e2);
        node1.addEdge(e3);
        node4.addEdge(e4);
        node5.addEdge(e5);

        GraphWriter.print(graph, "E:/test");

    }
}
