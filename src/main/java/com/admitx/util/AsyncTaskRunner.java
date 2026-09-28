package com.admitx.util;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

import javafx.concurrent.Task;

public final class AsyncTaskRunner {

    private AsyncTaskRunner() {
    }

    public static <T> void run(
            Callable<T> backgroundWork,
            Consumer<T> onSuccess,
            Consumer<Throwable> onFailure
    ) {

        Task<T> task = new Task<>() {

            @Override
            protected T call() throws Exception {

                return backgroundWork.call();
            }
        };

        task.setOnSucceeded(event -> {

            T result = task.getValue();

            onSuccess.accept(result);
        });

        task.setOnFailed(event -> {

            Throwable error =
                    task.getException();

            onFailure.accept(error);
        });

        AsyncExecutor.execute(task);
    }
}