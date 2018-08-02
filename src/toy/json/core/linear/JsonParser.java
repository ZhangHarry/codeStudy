package toy.json.core.linear;

import toy.json.displayer.TokenDisplayer;
import toy.json.exception.WrongFormatException;
import toy.json.object.JsonArray;
import toy.json.object.JsonBaseObject;
import toy.json.object.JsonObject;
import toy.json.token.*;
import toy.json.util.JsonDefinition;

import java.util.List;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class JsonParser {
    int tokenIndex;
    List<Token> tokens ;

    public JsonBaseObject parse(byte[] input) throws WrongFormatException, IllegalAccessException, InstantiationException {
        tokens = new JsonTokenAnalyser(input).tokenAnalyse();
        TokenDisplayer.display(input, tokens);
        Token token;
        JsonBaseObject json = null;
        if (hasNextToken()) {
            token = getNextToken();
            if (token instanceof OpeningBraceToken){
                json = parseObject();
            }else if (token instanceof OpeningBracketToken){
                json = parseArray();
            }else {
                throw new WrongFormatException(String.format("wrong start character at %d '%s'", token.getPosition(), token.getSymbol()));
            }
        }
        return json;
    }

    private boolean hasNextToken() {
        return tokenIndex < tokens.size();
    }

    private JsonObject parseObject() throws WrongFormatException {
        JsonObject object = new JsonObject();
        Token token = null;
        if (hasNextToken()){
            token = getNextToken();
            if (token instanceof ClosingBraceToken) // finish
                ;
            else if (token instanceof StringToken){ // 一个属性对
                boolean goon = true;
                while (goon) {
                    if (!(token instanceof StringToken)) {// 需要属性对
                        throw new WrongFormatException(String.format("expect %s at %d '%s'", "valid (key:value) pair", token.getPosition(), token.getSymbol()));
                    }
                    readPropertyPair(token.getLabel(), object);
                    if ((token = getNextToken()) instanceof CommaToken) //跳过逗号往前
                        token = getNextToken();
                    else if (token instanceof ClosingBraceToken){ // 结束
                        goon = false;
                    }else {
                        throw new WrongFormatException(String.format("expect '%s' or '%s' at %d '%s'",
                                new CommaToken().getLabel(), new ClosingBraceToken().getLabel(), token.getPosition(), token.getSymbol()));
                    }
                }
            }
        }
        if (token == null || !(token instanceof ClosingBraceToken))
            throw new WrongFormatException("incomplete json string");
        return object;
    }

    private void readPropertyPair(String key, JsonObject object) throws WrongFormatException {
        Token token = getNextToken();
        if (!(token instanceof ColonToken)) // 需要冒号
            throw new WrongFormatException(String.format("expect %s at %d '%s'", new ColonToken().getLabel(), token.getPosition(), token.getSymbol()));
        else {
            token = getNextToken();
            if (token instanceof VarToken) {
                String value = token.getLabel();
                if (token instanceof NumberToken){
                    object.addParameter(key, JsonDefinition.parseNumber(value));
                }else
                    object.addParameter(key, value);
            } else if (token instanceof OpeningBraceToken) {
                object.addParameter(key, parseObject());
            } else if (token instanceof OpeningBracketToken) {
                object.addParameter(key, parseArray());
            } else
                throw new WrongFormatException(String.format("expect %s at %d '%s'", "valid value token", token.getPosition(), token.getSymbol()));
        }
    }

    private JsonArray parseArray() throws WrongFormatException {
        JsonArray array = new JsonArray();
        Token token = null;
        boolean goon = false;
        while (hasNextToken()){
            token = getNextToken();
            if (!goon && token instanceof ClosingBracketToken) // finish
                break;
            else if (token instanceof OpeningBraceToken){
                array.add(parseObject());
                if ((token = getNextToken()) instanceof CommaToken)//继续
                    ;
                else if (token instanceof ClosingBracketToken) // 可以结束
                    break;
                else if (!(token instanceof ClosingBracketToken))
                    throw new WrongFormatException(String.format("expect '%s' at %d '%s'", new CommaToken().getLabel(), token.getPosition(), token.getSymbol()));
            }
            else if (token instanceof OpeningBracketToken){
                array.add(parseArray());
                if ((token = getNextToken()) instanceof CommaToken)//继续
                    ;
                if (token instanceof ClosingBracketToken) // 可以结束
                    break;
                else if (!(token instanceof ClosingBracketToken))
                    throw new WrongFormatException(String.format("expect '%s' at %d '%s'", new CommaToken().getLabel(), token.getPosition(), token.getSymbol()));
            }else {
                throw new WrongFormatException(String.format("expect '%s' at %d '%s'", "array value", token.getPosition(), token.getSymbol()));
            }
        }
        if (token == null || !(token instanceof ClosingBracketToken))
            throw new WrongFormatException("incomplete json string");
        return array;
    }

    public Token getNextToken() {
        Token currToken = tokens.get(tokenIndex);
        tokenIndex++;
        return currToken;
    }
}
