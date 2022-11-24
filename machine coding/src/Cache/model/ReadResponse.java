package Cache.model;

public class ReadResponse {

    private int readTime;
    private String key;
    private String value;

    public ReadResponse(int readTime, String key, String value) {
        this.readTime = readTime;
        this.key = key;
        this.value = value;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
