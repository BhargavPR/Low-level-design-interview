package Cache.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CacheStorage {

    private HashMap<String, String> data;
    private List<String> keys;

    public CacheStorage() {
        this.data = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "CacheStorage{" +
                "data=" + data +
                ", keys=" + keys +
                '}';
    }
}
