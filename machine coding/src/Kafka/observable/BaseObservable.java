package Kafka.observable;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservable<Listener> implements Observable<Listener> {

    private final Set<Listener> listeners = new HashSet<>();

    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    public Set<Listener> getListeners() {
        return listeners;
    }
}
