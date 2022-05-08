package org.tealeaf.pacemanager.events;

import org.tealeaf.pacemanager.threads.ThreadManager;

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

        new HashSet<> (listeners).parallelStream() //Using Parallelization to improve performance
                 .filter(listener::isInstance) //Filter to items that are instance of the listener
                 .map(listener::cast) //Maps to the listener type
                 .sequential() //Go back to sequential (on this thread)
                 .forEach(action::execute); //Execute the action
    }
}
