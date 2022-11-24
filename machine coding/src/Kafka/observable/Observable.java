package Kafka.observable;

public interface Observable<Listener> {

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);


}
