package toy.json.token;

import toy.json.util.Constant;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class QuoteToken extends Token {

    public QuoteToken() {
        this.nextTokens.add(QuoteToken.class);
        this.symbol = Constant.QUOTE;
        this.label = new String(new byte[]{symbol});
    }
}
