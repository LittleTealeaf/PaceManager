package org.tealeaf.pacemanager.events;

import java.util.Set;

public interface EventCoordinator {
    void addListener(Object object);

    void removeListener(Object object);

    <T> void run(Class<T> listenerClass, Event<T> event);

    Set<Object> getListeners();

    interface Event<T> {
        void run(T listener);
    }
}
