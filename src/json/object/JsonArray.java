package json.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class JsonArray implements JsonBaseObject{
    private List<JsonBaseObject> list = new ArrayList<>();

    public JsonBaseObject get(int i){
        return list.get(i);
    }

    public void add(JsonBaseObject obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }
}
