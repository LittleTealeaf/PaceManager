package org.tealeaf.pacemanager.concurrency;

import javafx.application.Platform;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static final ExecutorService service = Executors.newCachedThreadPool();


    public static void schedule(Runnable runnable) {
        Platform.runLater(runnable);
    }

    public static void run(Runnable runnable) {
        service.execute(runnable);
    }
}
