package com.admitx.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class AsyncExecutor {

    private static final ExecutorService EXECUTOR =
            Executors.newFixedThreadPool(4, runnable -> {

                Thread thread = new Thread(runnable);

                thread.setDaemon(true);

                thread.setName(
                        "admitx-background-thread"
                );

                return thread;
            });

    private AsyncExecutor() {
    }

    public static void execute(Runnable task) {

        EXECUTOR.execute(task);
    }

    public static void shutdown() {

        EXECUTOR.shutdown();
    }
}