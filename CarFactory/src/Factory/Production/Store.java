package Factory.Production;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Store<T> {
    private BlockingQueue<T> queue;
    int allNum;
    int currNum;
    int maxSize;

    public Store(int size) {
        maxSize = size;
        currNum = 1;
        allNum = 1;
        queue = new ArrayBlockingQueue<>(size);
    }

    synchronized T take() throws InterruptedException {
        T t = queue.take();
        currNum--;

        return t;
    }

    synchronized void push(T product) {
        queue.add(product);

        if(currNum < maxSize) {
            currNum++;
            allNum++;
        }
    }

    synchronized int getAllNum() { return allNum; }
    synchronized int getCurrNum() { return currNum; }

}
