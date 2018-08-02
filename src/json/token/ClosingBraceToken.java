package json.token;

import json.util.Constant;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class ClosingBraceToken extends Token {

    public ClosingBraceToken() {
        this.symbol = Constant.CLOSING_BRACE;
        this.label = new String(new byte[]{symbol});
    }
}
