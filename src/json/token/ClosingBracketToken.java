package json.token;

import json.util.Constant;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class ClosingBracketToken extends Token {
    public ClosingBracketToken() {
        this.symbol = Constant.CLOSING_BRACKET;
        this.label = new String(new byte[]{symbol});
    }
}
