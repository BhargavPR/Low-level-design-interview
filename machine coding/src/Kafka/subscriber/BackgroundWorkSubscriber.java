package Kafka.subscriber;

import Kafka.model.Message;
import Kafka.queue.Queue;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BackgroundWorkSubscriber extends BaseSubscriber {

    public BackgroundWorkSubscriber(Queue queue) {
        super(queue);
    }

    @Override
    public synchronized void consumeMessage(Message message) {
        System.out.println("BackgroundWorkSubscriber: " + Thread.currentThread() + " and message " + message.getPayload());
    }

}
