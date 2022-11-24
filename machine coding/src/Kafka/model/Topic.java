package Kafka.model;

import Kafka.subscriber.Subscriber;

import java.util.*;

public class Topic {

    private String id;
    private String topicName;
    private List<Message> messages;
    private Set<Subscriber> subscribers;

    public Topic(String id, String topicName) {
        this.id = id;
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.subscribers = new HashSet<>();
    }

    public Topic(String topicName) {
        this(UUID.randomUUID().toString(), topicName);
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public Set<Subscriber> getSubscribers() {
        return Collections.unmodifiableSet(subscribers);
    }
}
