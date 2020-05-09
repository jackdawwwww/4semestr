package Factory.Production;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CarStore {
    private BlockingQueue<Car> cars;
    private int currNum;
    private int allNum;
    private int maxSize;
    private int waitingNum;

    public CarStore(int size) {
        maxSize = size;
        cars = new ArrayBlockingQueue<>(size);
        waitingNum = 0;
    }

    public synchronized void addCar(Car car) {
        while(getCurrNum() == maxSize)
            try {
                this.wait();
            } catch(Throwable ignored) {
                Thread.currentThread().interrupt();
                return;
            }
        currNum++;
        allNum++;

        cars.offer(car);
        this.notify();
    }

    public synchronized Car getCar() throws InterruptedException {
        waitingNum--;
        while(getCurrNum() == 0)
            try {
                this.wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }

        Car car = cars.take();
        System.out.print("get a car for dealer\n");
        this.notifyAll();
        return car;
    }

    public synchronized int getCurrNum() { return cars.size(); }
    public synchronized int getAllNum() { return allNum; }
    public synchronized int getWaitingNum() { return waitingNum; }
    public synchronized void addWaitingNum() { waitingNum++; }
    public synchronized void subWaitingNum() { waitingNum--; }

}
