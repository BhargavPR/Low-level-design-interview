package Cache.model;

public class Cache {

    private int capacity;

    private int readTime;

    private int writeTime;

    private CacheStorage cacheStorage = new CacheStorage();

    public Cache(int capacity, int readTime, int writeTime) {
        this.capacity = capacity;
        this.readTime = readTime;
        this.writeTime = writeTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public int getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(int writeTime) {
        this.writeTime = writeTime;
    }

    public CacheStorage getCacheStorage() {
        return cacheStorage;
    }

    public void setCacheStorage(CacheStorage cacheStorage) {
        this.cacheStorage = cacheStorage;
    }

    public boolean isCacheStorageFull() {
        return cacheStorage.getData().size() == capacity;
    }

}
