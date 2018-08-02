package json.token;

import json.util.Constant;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class CommaToken extends Token {

    public CommaToken() {
        this.symbol = Constant.COMMA;
        this.label = new String(new byte[]{symbol});
    }
}
