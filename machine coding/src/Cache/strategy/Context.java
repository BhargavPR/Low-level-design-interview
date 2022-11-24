package Cache.strategy;

public class Context {

    public CacheStrategy getCacheStrategy(String type) {
        if (type.equals(CacheStrategy.LRU)) {
            return new LRUCacheStrategy();
        }
        return null;
    }
}
