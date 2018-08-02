package json.token;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhanghr on 2016/12/3.
 */
public abstract class Token {
    List<Class> nextTokens = new LinkedList<>();
    Token closeToken;
    Byte symbol;
    int position;
    String label;
    public String getLabel() {
        return label;
    }

    public List<Class> getNextTokens() {
        return nextTokens;
    }

    public Token getCloseToken() {
        return closeToken;
    }

    public Byte getByte() {
        return symbol;
    }

    public int compareTo(Token s){
        return symbol-s.getByte();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSymbol(){
        return new String(new byte[]{symbol});
    }
}
