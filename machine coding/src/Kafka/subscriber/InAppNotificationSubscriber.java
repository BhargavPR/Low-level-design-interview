package Kafka.subscriber;

import Kafka.model.Message;
import Kafka.queue.Queue;

public class InAppNotificationSubscriber extends BaseSubscriber {

    public InAppNotificationSubscriber(Queue queue) {
        super(queue);
    }

    @Override
    public synchronized void consumeMessage(Message message) {
        System.out.println("InAppNotificationSubscriber: " + Thread.currentThread() + " and message " + message.getPayload());
    }
}
