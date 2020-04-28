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

    synchronized void addCar(Car car) {
        if(currNum < maxSize) {
            currNum++;
            allNum++;
        }

        cars.add(car);
    }

    synchronized Car getCar() throws InterruptedException {
        if(currNum > 0) currNum--;

        Car car = cars.take();
        System.out.print("get a car for dealer\n");
        waitingNum--;
        return car;
    }

    synchronized int getCurrNum() { return currNum; }
    synchronized int getAllNum() { return allNum; }
    synchronized int getWaitingNum() { return waitingNum; }
    synchronized void addWaitingNum() { waitingNum++; }

}
