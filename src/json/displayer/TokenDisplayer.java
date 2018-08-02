package json.displayer;

import json.token.Token;

import java.util.List;

/**
 * Created by zhanghr on 2016/12/5.
 */
public class TokenDisplayer {
    public static void display(byte[] input, List<Token> tokens) {
        String inputS = new String(input);
        for (int i = 0; i < inputS.length(); i++) {
            System.out.format("%s\t", inputS.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < input.length; i++) {
            System.out.format("%d\t", input[i]);
        }
        System.out.println();
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            System.out.format("%s\t%s\t%d%n", token.getClass(), token.getLabel(), token.getPosition());
        }
    }
}
