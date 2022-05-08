package org.tealeaf.pacemanager.threads;

import javafx.application.Platform;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadManager {
    private static final ExecutorService service = Executors.newCachedThreadPool();

    public static <T> void startFunction(Function<T> function, Listener<T> listener) {
        run(() -> {
            T item = function.run();
            schedule(() -> listener.onListen((item)));
        });
    }

    public static void run(Runnable runnable) {
        service.execute(runnable);
    }

    public static void schedule(Runnable runnable) {
        Platform.runLater(runnable);
    }

    public interface Function<T> {
        T run();
    }

    public interface Listener<T> {
        void onListen(T item);
    }
}
