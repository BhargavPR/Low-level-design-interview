package Cache.strategy;

import Cache.model.Cache;
import Cache.model.ReadResponse;
import Cache.model.WriteResponse;

import java.util.HashMap;
import java.util.List;

public class LRUCacheStrategy implements CacheStrategy {

    @Override
    public ReadResponse readKey(Cache cache, String key) {
        int readTime = readAndUpdateKey(cache, key);
        return new ReadResponse(readTime, key, cache.getCacheStorage().getData().get(key));
    }

    @Override
    public WriteResponse writeKey(Cache cache, String key, String value) {
        int writeTime = 0;

        boolean isKeyExists = isKeyExists(cache, key);
        writeTime = writeTime + cache.getReadTime();

        if (isKeyExists) {
            writeTime = writeTime + updateKey(cache, key, value);
        } else {
            if (cache.isCacheStorageFull()) {
                writeTime = writeTime + removeLeastUsedKey(cache);
            }
            writeTime = writeTime + insertNewKey(cache, key, value);
        }
        return new WriteResponse(writeTime, key, value);
    }

    private int readAndUpdateKey(Cache cache, String key) {
        HashMap<String, String> cacheData = cache.getCacheStorage().getData();
        List<String> keys = cache.getCacheStorage().getKeys();

        int readTime = 0;
        boolean isKeyExists = isKeyExists(cache, key);
        readTime = readTime + cache.getReadTime();

        if (isKeyExists) {
            keys.remove(key);
            keys.add(0, key);
            readTime = readTime + cache.getWriteTime();
        }

        return readTime;
    }

    private int removeLeastUsedKey(Cache cache) {
        HashMap<String, String> cacheData = cache.getCacheStorage().getData();
        List<String> keys = cache.getCacheStorage().getKeys();

        String key = keys.get(keys.size() - 1);
        cacheData.remove(key);
        keys.remove(keys.size() - 1);
        return cache.getWriteTime();
    }

    private int updateKey(Cache cache, String key, String value) {
        HashMap<String, String> cacheData = cache.getCacheStorage().getData();
        List<String> keys = cache.getCacheStorage().getKeys();

        if (!cacheData.containsKey(key) || !keys.contains(key)) {
            return 0;
        }

        keys.remove(key);
        keys.add(0, key);

        String cacheValue = cacheData.get(key);

        if (cacheValue.equals(value)) {
            return 0;
        } else {
            cacheData.replace(key, value);
            return cache.getWriteTime();
        }
    }

    private int insertNewKey(Cache cache, String key, String value) {
        HashMap<String, String> cacheData = cache.getCacheStorage().getData();
        List<String> keys = cache.getCacheStorage().getKeys();

        cacheData.put(key, value);
        keys.add(0, key);
        return cache.getWriteTime();
    }

    private boolean isKeyExists(Cache cache, String key) {
        HashMap<String, String> cacheData = cache.getCacheStorage().getData();
        List<String> keys = cache.getCacheStorage().getKeys();

        return cacheData.containsKey(key) && keys.contains(key);
    }
}
