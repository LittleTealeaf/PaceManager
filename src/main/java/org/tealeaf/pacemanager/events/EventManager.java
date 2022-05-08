package org.tealeaf.pacemanager.events;

import java.util.HashSet;
import java.util.Set;

public class EventManager {


    private final Set<Object> listeners = new HashSet<>();

    public Set<Object> getListeners() {
        return listeners;
    }

    public boolean addListener(Object object) {
        return listeners.add(object);
    }

    public boolean removeListener(Object object) {
        return listeners.remove(object);
    }

    public <T> void runEvent(Class<T> listener, EventAction<T> action) {
        listeners.forEach(item -> {
            if(listener.isInstance(item)) {
                action.execute(listener.cast(item));
            }
        });
    }


    public interface EventAction<T> {
        void execute(T item);
    }
}
