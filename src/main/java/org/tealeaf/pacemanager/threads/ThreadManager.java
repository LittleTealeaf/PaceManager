package org.tealeaf.pacemanager.threads;

import javafx.application.Platform;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadManager {
    private static final ExecutorService service = Executors.newCachedThreadPool();

    public static <T> void startFunction(Function<T> function, Listener<T> listener) {
        service.execute(() -> {
            T item = function.run();
            Platform.runLater(() -> listener.onListen((item)));
        });
    }

    public interface Function<T> {
        T run();
    }

    public interface Listener<T> {
        void onListen(T item);
    }
}
