package Factory.Production;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class Store<T> {
    private final Queue<T> queue;
    int allNum, currNum, maxSize;
    Supplier supplier = null;

    public Store(int size) {
        maxSize = size;
        currNum = 0;
        allNum = 0;
        queue = new ArrayBlockingQueue<>(size);
    }

    public synchronized T take() {
        while(currNum == 0)
            try {
                this.wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        T t = queue.poll();
        currNum--;
        supplier.minCurrNum();
        this.notifyAll();

        return t;
    }

    public synchronized void push(T product) {
        while(currNum == maxSize)
            try {
                this.wait();
            } catch(Throwable ignored) {
                Thread.currentThread().interrupt();
                return;
            }

        queue.offer(product);
        currNum++;
        supplier.addCurrNum();
        allNum++;
        this.notify();
    }

    public synchronized int getMaxSize() { return maxSize; }
    public synchronized int getAllNum() { return allNum; }
    public synchronized int getCurrNum() { return queue.size(); }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}
