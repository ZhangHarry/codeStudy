package util.grapic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Zhanghr on 2016/5/25.
 */
public class Node<S> {
    private Collection<Edge> edges = new ArrayList<>();

    private Optional<S> symbol;

    public Node(S symbol){
        this.symbol = Optional.ofNullable(symbol);
    }

    public Collection<Edge> getEdges() {
        return edges;
    }

    public S getSymbol() {
        return symbol.get();
    }

    public void setSymbol(S symbol) {
        this.symbol = Optional.ofNullable(symbol);
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge){
        edges.remove(edge);
    }

    public String toString(){
        if (symbol.isPresent())
            return symbol.get().toString();
        return "";
    }
}
