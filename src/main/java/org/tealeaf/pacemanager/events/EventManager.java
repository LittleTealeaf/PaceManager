package org.tealeaf.pacemanager.events;

import java.util.HashSet;
import java.util.Set;

public class EventManager implements EventCoordinator {

    private final Set<Object> listeners = new HashSet<>();

    public EventManager(Object... objects) {
        for (Object object : objects) {
            addListener(object);
        }
    }

    @Override
    public void addListener(Object object) {
        System.out.println("Adding Listener " + object.getClass());
        listeners.add(object);
    }

    @Override
    public void removeListener(Object object) {
        System.out.println("Removing Listener " + object.getClass());
        listeners.remove(object);
    }

    @Override
    public Set<Object> getListeners() {
        return new HashSet<>(listeners);
    }

    @Override
    public <T> void run(Class<T> listenerClass, Event<T> event) {
        getListeners().parallelStream().filter(listenerClass::isInstance).map(listenerClass::cast).sequential().forEach(event::run);
    }
}
