package Kafka.model;

import java.util.UUID;

public class Publisher {

    private String id;
    private String name;

    public Publisher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publisher(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
