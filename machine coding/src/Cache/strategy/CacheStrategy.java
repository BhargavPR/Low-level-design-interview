package Cache.strategy;

import Cache.model.Cache;
import Cache.model.ReadResponse;
import Cache.model.WriteResponse;

public interface CacheStrategy {

    public static final String LRU = "LRU";

    ReadResponse readKey(Cache cache, String key);

    WriteResponse writeKey(Cache cache, String key, String value);

}
