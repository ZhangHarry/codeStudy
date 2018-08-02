package toy.json.object;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by zhanghr on 2016/12/3.
 */
public class JsonObject implements JsonBaseObject{
    private HashMap<String, Object> map = new HashMap<>();

    public Object getValue(String key){
        return map.get(key);
    }

    public void addParameter(String key, Object value){
        map.put(key, value);
    }

    public Collection values(){
        return map.values();
    }

    public Collection<String> keys(){
        return map.keySet();
    }
}
