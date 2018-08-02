package util.grapic;

import util.CmdExecutor;
import util.FileProcesser;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xw on 15-10-26.
 */
public class GraphWriter {
    public static void print(Graph g, String filePath){
        if (g == null || filePath == null){
            return;
        }
        FileProcesser.createFileParent(new File(filePath));
        PrintWriter out = null;
        try {
            out = new PrintWriter(filePath);
            StringBuilder sb= new StringBuilder();
            sb.append("digraph callGraph {\n");
            sb.append("\tnode [shape=rectangle]\n");
            for (Node node : g.nodes){
                sb.append("\t" + node.hashCode() + "  [label=\""
//                        + node.hashCode()+"\n"
                        +node.toString().replace("\"", "\\\"") + "\"]\n");
            }
            for (Node node : g.nodes){
                for (Edge edge : (List<Edge>)node.getEdges()){
                    StringBuilder contextLabel = new StringBuilder();
                    contextLabel.append(edge.toString() + "\n");

                    sb.append("\t" + edge.getStrNode().hashCode() + " -> "
                            + edge.getEndNode().hashCode() + "[ label=\""
                            + contextLabel.toString().replace("\"", "\\\"") + "\" ]" + "\n");
                }
            }
            sb.append("}");
            out.print(sb);
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (out != null){
                out.close();
            }
            CmdExecutor.exec("dot -Tpng " + filePath + " -o " + filePath + ".png");
        }
    }
}
