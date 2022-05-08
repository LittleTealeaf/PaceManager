package org.tealeaf.pacemanager.events;

import java.util.Set;

public interface EventCoordinator {

    Set<Object> getListeners();
    boolean addListener(Object object);
    boolean removeListener(Object object);
    <T> void runEvent(Class<T> listener, EventAction<T> action);


    interface EventAction<T> {
        void execute(T item);
    }
}
