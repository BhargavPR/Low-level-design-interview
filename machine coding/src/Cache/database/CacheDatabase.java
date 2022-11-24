package Cache.database;

import Cache.model.Cache;

import java.util.ArrayList;
import java.util.List;

public class CacheDatabase {

    private List<Cache> caches = new ArrayList<>();

    private static CacheDatabase INSTANCE = null;

    public static CacheDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheDatabase();
        }
        return INSTANCE;
    }

    public void setCaches(List<Cache> cacheList) {
        caches.clear();
        caches.addAll(cacheList);
    }

    public void insertCache(int capacity, int readTime, int writeTime) {
        caches.add(new Cache(capacity, readTime, writeTime));
    }

    public Cache getLeveledCache(int level) {
        if (level < 0 || level >= caches.size()) {
            throw new RuntimeException("Level does not exists");
        }
        return caches.get(level);
    }

    public List<Cache> getCaches() {
        return caches;
    }
}
