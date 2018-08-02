package toy.json.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanghr on 2016/12/6.
 */
public class JsonDefinition {

    public static boolean isNumber(Byte currByte) {
        if (isNumberHeader(currByte) || currByte-'.' == 0 || currByte-'+' == 0 || currByte-'e' == 0 || currByte-'E' == 0)
            return true;
        return false;
    }

    public static boolean isName(Byte currByte) {
        return isNameHeader(currByte) || isNumberHeader(currByte);
    }

    public static boolean isNumberHeader(Byte currByte) {
        if ( (currByte.compareTo((byte)('0')) >=0 && currByte.compareTo((byte)('9')) <=0 ) || currByte.compareTo((byte)'-') == 0)
            return true;
        return false;
    }

    public static boolean isNameHeader(Byte currByte) {
        if ( (currByte.compareTo((byte)'a') >=0 && currByte.compareTo((byte)('z')) <=0 ) ||
                (currByte.compareTo((byte)('A')) >=0 && currByte.compareTo((byte)('Z')) <=0 ) ||
                (currByte.compareTo((byte)('_'))) == 0 ||(currByte.compareTo((byte)('$'))) == 0)
            return true;
        return false;
    }

    public static boolean isValidJsonNumber(String numberStr) {
        Pattern p = Pattern.compile("(-?)(0|([1-9]\\d*))(\\.\\d*)?((e|E)(\\+|-)?\\d*)?");
        Matcher m = p.matcher(numberStr); // get a matcher object
        if(m.find()){
            String group = m.group();
            System.out.println(group);
            if (group.length() == numberStr.length())
                return true;
        }
        return false;
    }

    public static boolean isTrue(String s) {
        if (s.equals("true"))
            return true;
        else
            return false;
    }

    public static boolean isFalse(String s) {
        if (s.equals("false"))
            return true;
        else
            return false;
    }

    public static boolean isNull(String s) {
        if (s.equals("null"))
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        String s = "-1234.34e1";
        System.out.println(JsonDefinition.isValidJsonNumber(s));
    }

    /**
     * todo : 解析真正的数值，因为包含e|E所以不能直接用Integer、Double的parse
     * @param value
     * @return
     */
    public static Object parseNumber(String value) {
        return value;
    }
}
