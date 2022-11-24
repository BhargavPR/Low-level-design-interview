package Cache.model;

public class WriteResponse {

    private int writeTime;
    private String key;
    private String value;

    public WriteResponse(int writeTime, String key, String value) {
        this.writeTime = writeTime;
        this.key = key;
        this.value = value;
    }

    public int getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(int writeTime) {
        this.writeTime = writeTime;
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
