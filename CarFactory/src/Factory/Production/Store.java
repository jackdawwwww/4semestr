package Factory.Production;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class Store<T> {
    private final Queue<T> queue;
    int allNum, maxSize;

    public Store(int size) {
        maxSize = size;
        allNum = 0;
        queue = new ArrayBlockingQueue<>(size);
    }

    public synchronized T take() {
        while(queue.isEmpty())
            try {
                this.wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        T t = queue.poll();
        this.notifyAll();

        return t;
    }

    public synchronized void push(T product) {
        while(queue.size() == maxSize)
            try {
                this.wait();
            } catch(Throwable ignored) {
                Thread.currentThread().interrupt();
                return;
            }

        queue.offer(product);
        allNum++;
        this.notify();
    }

    public synchronized int getAllNum() { return allNum; }
    public synchronized int getCurrNum() { return queue.size(); }
}
