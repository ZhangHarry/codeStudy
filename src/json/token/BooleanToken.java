package json.token;

/**
 * Created by zhanghr on 2016/12/11.
 */
public class BooleanToken extends VarToken {
    private boolean flag;
    public BooleanToken(boolean bl){
        super(""+bl);
        this.flag = bl;
    }

    public boolean getFlag() {
        return flag;
    }
}
