package toy.json.token;

/**
 * Created by zhanghr on 2016/12/4.
 */
public class VarToken extends Token{
    public VarToken(String label){
        this.symbol = '\0';
        this.label = label;
        this.nextTokens.add(CommaToken.class);

    }
}
