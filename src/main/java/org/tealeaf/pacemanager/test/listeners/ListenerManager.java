package org.tealeaf.pacemanager.test.listeners;

import java.util.HashSet;
import java.util.Set;

public class ListenerManager implements IListenerManager {
    private final Set<Object> listeners = new HashSet<>();

    public void registerListener(Object object) {
        listeners.add(object);
    }

    public void unregisterListener(Object object) {
        listeners.remove(object);
    }

    @Override
    public <T> void callListeners(Class<T> tClass, Notification<T> notification) {
        listeners.forEach((item) -> {
            if(tClass.isInstance(item)) {
                notification.call(tClass.cast(item));
            }
        });
    }

    public Set<Object> getListeners() {
        return listeners;
    }


}
