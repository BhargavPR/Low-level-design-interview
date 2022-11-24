package Cache.service;

import Cache.database.CacheDatabase;
import Cache.model.Cache;
import Cache.model.CacheStorage;
import Cache.model.ReadResponse;
import Cache.model.WriteResponse;
import Cache.strategy.CacheStrategy;

import java.util.List;

public class CacheService {

    private CacheDatabase cacheDatabase;
    private CacheStrategy cacheStrategy;

    public CacheService(CacheDatabase cacheDatabase, CacheStrategy cacheStrategy) {
        this.cacheDatabase = cacheDatabase;
        this.cacheStrategy = cacheStrategy;
    }

    public void writeCache(String key, String value) {
        int writeTime = 0;
        List<Cache> caches = cacheDatabase.getCaches();

        for (Cache cache : caches) {
            WriteResponse writeResponse = cacheStrategy.writeKey(cache, key, value);
            writeTime = writeTime + writeResponse.getWriteTime();
        }
        System.out.println("Write time " + writeTime);
    }

    public void readCache(String key) {
        int readTime = 0;
        List<Cache> caches = cacheDatabase.getCaches();

        int readIndex = -1;
        String value = null;

        for (Cache cache : caches) {
            ReadResponse readResponse = cacheStrategy.readKey(cache, key);
            readTime = readTime + readResponse.getReadTime();

            if (readResponse.getValue() != null) {
                readIndex = caches.indexOf(cache);
                value = readResponse.getValue();
                break;
            }
        }

        for (int i = 0; i < readIndex; i++) {
            WriteResponse writeResponse = cacheStrategy.writeKey(caches.get(i), key, value);
            readTime = readTime + writeResponse.getWriteTime();
        }
        System.out.println("Read time: " + readTime);
    }

    public void printCache() {
        List<Cache> caches = cacheDatabase.getCaches();

        for (Cache cache : caches) {
            CacheStorage cacheStorage = cache.getCacheStorage();
            System.out.println(cacheStorage);
        }
    }
}
