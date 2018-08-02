package toy.json.token;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class StringToken extends VarToken {
    public StringToken(String label) {
        super(label);
        this.symbol = (byte)label.charAt(0);
        this.nextTokens.add(ColonToken.class);
    }

}
