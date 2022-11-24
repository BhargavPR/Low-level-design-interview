package Kafka.subscriber;

import Kafka.model.Message;

public interface Subscriber {

    void subscribeToTopic(String topicName);

    void consumeMessage(Message message);

}
