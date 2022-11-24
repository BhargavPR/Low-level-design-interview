package Kafka.subscriber;

import Kafka.queue.Queue;

public abstract class BaseSubscriber implements Subscriber {

    private final Queue queue;

    private int offset;

    public BaseSubscriber(Queue queue) {
        this.queue = queue;
        this.offset = 0;
    }

    @Override
    public void subscribeToTopic(String topicName) {
        queue.subscribeToTopic(this, topicName);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
