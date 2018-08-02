package toy.json.token;

import toy.json.util.Constant;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class OpeningBracketToken extends Token {
    public OpeningBracketToken() {
        this.nextTokens.add(ClosingBracketToken.class);
        this.nextTokens.add(OpeningBraceToken.class);
        this.symbol = Constant.OPENING_BRACKET;
        this.label = new String(new byte[]{symbol});
    }
}
