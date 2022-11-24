package Kafka;

import Kafka.constants.Constants;
import Kafka.model.Message;
import Kafka.model.Publisher;
import Kafka.model.Topic;
import Kafka.queue.Queue;
import Kafka.subscriber.BackgroundWorkSubscriber;
import Kafka.subscriber.EmailNotificationSubscriber;
import Kafka.subscriber.InAppNotificationSubscriber;

import java.util.concurrent.Executors;

public class KafkaApplication {

    public static void main(String[] args) throws InterruptedException {
        Queue queue = Queue.getInstance(Executors.newFixedThreadPool(5));
        Publisher publisher = new Publisher("publisher");

        EmailNotificationSubscriber emailNotificationSubscriber = new EmailNotificationSubscriber(queue);
        InAppNotificationSubscriber inAppNotificationSubscriber = new InAppNotificationSubscriber(queue);
        BackgroundWorkSubscriber backgroundWorkSubscriber = new BackgroundWorkSubscriber(queue);

        queue.createTopic(Constants.NOTIFICATION_TOPIC);
        queue.createTopic(Constants.BACKGROUND_TOPIC);

        emailNotificationSubscriber.subscribeToTopic(Constants.NOTIFICATION_TOPIC);
        inAppNotificationSubscriber.subscribeToTopic(Constants.NOTIFICATION_TOPIC);

        backgroundWorkSubscriber.subscribeToTopic(Constants.BACKGROUND_TOPIC);


        queue.publishMessage(Constants.NOTIFICATION_TOPIC, new Message("please send notification 1"));
        queue.publishMessage(Constants.BACKGROUND_TOPIC, new Message("update the DB"));

        queue.publishMessage(Constants.NOTIFICATION_TOPIC, new Message("please send notification 2"));
        queue.publishMessage(Constants.NOTIFICATION_TOPIC, new Message("please send notification 3"));

        Thread.sleep(1000);
        System.out.println("resetting offset");
        queue.resetOffset(Constants.NOTIFICATION_TOPIC, emailNotificationSubscriber, 1);

    }
}
