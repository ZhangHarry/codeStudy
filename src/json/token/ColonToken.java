package json.token;

import json.util.Constant;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class ColonToken extends Token {

    public ColonToken() {
        this.nextTokens.add(StringToken.class);
        this.symbol = Constant.COLON;
        this.label = new String(new byte[]{symbol});
    }
}
