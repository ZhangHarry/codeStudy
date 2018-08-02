package util.grapic;

import java.util.Optional;

/**
 * Created by Zhanghr on 2016/5/25.
 */
public class Edge<T> {
    private Node strNode;
    private Node endNode;
    private Optional<T> symbol;
    private float length;

    public Node getStrNode() {
        return strNode;
    }

    public void setStrNode(Node strNode) {
        this.strNode = strNode;
    }

    public T getSymbol() {
        return symbol.get();
    }

    public void setSymbol(T symbol) {
        this.symbol = Optional.ofNullable(symbol);
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public Edge(Node start, Node end, T symbol, float length){
        this.strNode = start;
        this.endNode = end;
        this.symbol = Optional.ofNullable(symbol);
        this.length = length;
    }

    public Edge(Node start, Node end, T symbol){
        this.strNode = start;
        this.endNode = end;
        this.symbol = Optional.ofNullable(symbol);
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String toString(){
        if (symbol.isPresent())
            return symbol.get().toString();
        return "";
    }

}
