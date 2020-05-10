package Factory.Production;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class Store<T> {
    private final Queue<T> queue;
    int allNum, currNum, maxSize;

    public Store(int size) {
        maxSize = size;
        currNum = 1;
        allNum = 1;
        queue = new ArrayBlockingQueue<>(size);
    }

    public synchronized T take() {
        while(getCurrNum() == 0)
            try {
                this.wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        T t = queue.poll();
        currNum--;

        this.notifyAll();

        return t;
    }

    public synchronized void push(T product) {
        while(getCurrNum() == getMaxSize())
            try {
                this.wait();
            } catch(Throwable ignored) {
                Thread.currentThread().interrupt();
                return;
            }

        queue.offer(product);
        currNum++;
        allNum++;
        this.notify();
    }

    public synchronized int getMaxSize() { return maxSize; }
    public synchronized int getAllNum() { return allNum; }
    public synchronized int getCurrNum() { return queue.size(); }
}
