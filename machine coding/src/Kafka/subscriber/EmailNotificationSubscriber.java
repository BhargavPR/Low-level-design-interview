package Kafka.subscriber;

import Kafka.model.Message;
import Kafka.queue.Queue;

import java.io.Console;

public class EmailNotificationSubscriber extends BaseSubscriber {

    public EmailNotificationSubscriber(Queue queue) {
        super(queue);
    }

    @Override
    public synchronized void consumeMessage(Message message) {
        System.out.println("EmailNotificationSubscriber: " + Thread.currentThread() + " and message " + message.getPayload());
    }
}
