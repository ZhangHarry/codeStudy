package json.util;

import json.token.*;

import java.util.HashMap;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class Constant {
    public static final Byte OPENING_BRACE = '{';
    public static final Byte CLOSING_BRACE = '}';
    public static final Byte OPENING_BRACKET = '[';
    public static final Byte CLOSING_BRACKET = ']';
    public static final Byte COMMA = ',';
    public static final Byte COLON = ':';
    public static final Byte QUOTE = '"';
    public static final Byte SPACE1 = ' ';
    public static final Byte SPACE2 = '\r';
    public static final Byte SPACE3 = '\t';
    public static final Byte RT = '\n';
    public static final String OPENING_BRACE_STR = new String(new byte[]{'{'});
    public static final String CLOSING_BRACE_STR = new String(new byte[]{'}'});
    public static final String OPENING_BRACKET_STR = new String(new byte[]{'['});
    public static final String CLOSING_BRACKET_STR = new String(new byte[]{']'});
    public static final String COMMA_STR = new String(new byte[]{','});
    public static final String COLON_STR = new String(new byte[]{':'});
    public static final String QUOTE_STR = new String(new byte[]{'"'});

    public static HashMap<Byte, Token> map = new HashMap<>();
    static {
        map.put(OPENING_BRACE, new OpeningBraceToken());
        map.put(CLOSING_BRACE, new ClosingBraceToken());
        map.put(OPENING_BRACKET, new OpeningBracketToken());
        map.put(CLOSING_BRACKET, new ClosingBracketToken());
        map.put(COMMA, new CommaToken());
        map.put(COLON, new ColonToken());
        map.put(QUOTE, new QuoteToken());
    }
}
