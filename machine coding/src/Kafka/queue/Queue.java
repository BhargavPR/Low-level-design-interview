package Kafka.queue;

import Kafka.model.Message;
import Kafka.model.Topic;
import Kafka.subscriber.BaseSubscriber;
import Kafka.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Queue {

    private List<Topic> topics = new ArrayList<>();
    private ExecutorService executorService;

    private static Queue INSTANCE = null;

    public static Queue getInstance(ExecutorService executorService) {
        if (INSTANCE == null) {
            INSTANCE = new Queue(executorService);
        }
        return INSTANCE;
    }

    private Queue(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void createTopic(String topicName) {
        if (getTopicByName(topicName) != null) {
            throw new RuntimeException("Topic already exists");
        }

        topics.add(new Topic(topicName));
    }

    public void subscribeToTopic(Subscriber subscriber, String topicName) {
        Topic topic = getTopicByName(topicName);
        if (topic == null) {
            throw new RuntimeException("Topic do not exist");
        }

        topic.addSubscriber(subscriber);
    }

    public void publishMessage(String topicName, Message message) {
        Topic topic = getTopicByName(topicName);
        if (topic == null) {
            throw new RuntimeException("Topic do not exist");
        }

        topic.addMessage(message);
        for (Subscriber subscriber : topic.getSubscribers()) {
            executorService.submit(() -> {
                subscriber.consumeMessage(message);
            });
        }
    }

    public void resetOffset(String topicName, BaseSubscriber baseSubscriber, int offset) {
        Topic topic = getTopicByName(topicName);
        if (topic == null) {
            throw new RuntimeException("Topic do not exist");
        }

        baseSubscriber.setOffset(offset);
        List<Message> messages = topic.getMessages();

        messages = messages.subList(offset, messages.size());
        messages.forEach(message -> {
            executorService.submit(() -> {
                baseSubscriber.consumeMessage(message);
            });
        });
    }

    private Topic getTopicByName(String topicName) {
        return topics.stream().filter(topic -> topic.getTopicName().equals(topicName)).findAny().orElse(null);
    }
}
