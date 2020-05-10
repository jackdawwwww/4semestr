package ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPool {
    private BlockingQueue<Runnable> tasks;
    private PoolWorker[] workers;

    private AtomicBoolean isRunning = new AtomicBoolean(true);
    private AtomicBoolean isShutdown = new AtomicBoolean(false);
    private AtomicInteger activeWorkersCount = new AtomicInteger();

    private final Object awaitLock = new Object();

    public CustomThreadPool(int numThreads) throws Exception {
        if (numThreads <= 0) {
            throw new Exception("Number of threads less than or equal to zero was passed");
        }

        tasks = new LinkedBlockingQueue<>();
        workers = new PoolWorker[numThreads];
        activeWorkersCount.set(numThreads);

        for (int threadIndex = 0; threadIndex < numThreads; threadIndex++) {
            workers[threadIndex] = new PoolWorker();
            workers[threadIndex].start();
        }
    }

    public void submit(Runnable task) throws Exception {
        if (isShutdown.get()) {
            throw new Exception("Thread pool was terminated");
        }

        if (task == null) {
            throw new Exception("Task is null");
        }

        boolean isSubmitted = tasks.offer(task);
        if (!isSubmitted) {
            throw new Exception("Not enough space in the task queue");
        }
    }

    private void shutdown() {
        isShutdown.set(true);
    }

    public void shutdownNow() {
        shutdown();

        List<Runnable> taskList = new ArrayList<>(tasks.size());
        tasks.drainTo(taskList);

        for (PoolWorker worker: workers) {
            worker.finishWork();
            worker.interrupt();
        }

    }

    public boolean isShutdown() {
        return isShutdown.get();
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Runnable task = isShutdown() ? tasks.poll() : tasks.take();
                    if (task == null) {
                        break;
                    }
                    if(!isShutdown())
                    task.run();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (RuntimeException e) {
                    System.err.println("Task has failed:");
                    e.printStackTrace();
                }
            }

            finishWork();
        }

        private void finishWork() {
            if (activeWorkersCount.decrementAndGet() == 0) {
                synchronized (awaitLock) {
                    isRunning.set(false);
                    awaitLock.notifyAll();
                }
            }
        }
    }
}