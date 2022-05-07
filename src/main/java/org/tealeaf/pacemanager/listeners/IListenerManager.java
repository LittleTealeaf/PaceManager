package org.tealeaf.pacemanager.listeners;

import java.util.List;
import java.util.Set;

public interface IListenerManager {

    void registerListener(Object object);
    void unregisterListener(Object object);
    <T> void callListeners(Class<T> tClass, Notification<T> notification);

    Set<Object> getListeners();
    interface Notification<T> {
        void call(T listener);
    }
}
