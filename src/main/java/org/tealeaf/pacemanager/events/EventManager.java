package org.tealeaf.pacemanager.events;

import java.util.HashSet;
import java.util.Set;

public class EventManager implements EventCoordinator {


    private final Set<Object> listeners = new HashSet<>();

    @Override
    public Set<Object> getListeners() {
        return listeners;
    }

    @Override
    public boolean addListener(Object object) {
        return listeners.add(object);
    }

    @Override
    public boolean removeListener(Object object) {
        return listeners.remove(object);
    }

    @Override
    public <T> void runEvent(Class<T> listener, EventAction<T> action) {
        listeners.forEach(item -> {
            if(listener.isInstance(item)) {
                action.execute(listener.cast(item));
            }
        });
    }
}
