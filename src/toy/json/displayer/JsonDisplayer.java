package toy.json.displayer;

import toy.json.object.JsonArray;
import toy.json.object.JsonBaseObject;
import toy.json.object.JsonObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by zhanghr on 2016/12/5.
 */
public class JsonDisplayer {

    public static void display(JsonBaseObject object){
        display(object,"", "\t");
    }

    public static void display(JsonBaseObject object, String newSpace,  String space){
        if (object instanceof JsonObject)
            displayJsonObj((JsonObject)object,newSpace, space);
        else if (object instanceof JsonArray)
            displayJsonArray((JsonArray)object,newSpace, space);
    }

    private static void displayJsonObj(JsonObject object, String spaces, String space) {
        System.out.println(spaces+"Json object--------------------");
        String newSpace = spaces+space;
        Collection<String> keys = object.keys();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()){
            String key = it.next();
            System.out.format(newSpace+"%s:%s%n", key, object.getValue(key));
        }
    }

    private static void displayJsonArray(JsonArray array, String spaces, String space) {
        System.out.println(spaces+"Json array");
        String newSpace = spaces+space;
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JsonBaseObject object = array.get(i);
            display(object, newSpace, space);
        }
    }
}
