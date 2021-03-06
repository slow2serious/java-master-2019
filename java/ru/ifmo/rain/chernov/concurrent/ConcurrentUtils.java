package ru.ifmo.rain.chernov.concurrent;

import java.util.List;

public class ConcurrentUtils {
    public static void addAndStart(final List<Thread> workers, final Thread thread) {
        workers.add(thread);
        thread.start();
    }

    public static void joinThreads(final List<Thread> threads) throws InterruptedException {
        InterruptedException exception = null;
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                if (exception == null) {
                    exception = new InterruptedException("Not all threads joined");
                }
                exception.addSuppressed(e);
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    public static void joinThreadsUninterruptedly(final List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void checkThreads(final int threads) throws IllegalArgumentException {
        if (threads <= 0) {
            throw new IllegalArgumentException("Number of threads must be positive");
        }
    }
}
