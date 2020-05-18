package Factory.Production;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CarStore {
    private BlockingQueue<Car> cars;
    private int allNum;
    private int maxSize, waitingNum, flag = 0;
    private CarStoreController controller = null;

    public CarStore(int size) {
        maxSize = size;
        cars = new ArrayBlockingQueue<>(size);
        waitingNum = 0;
    }

    public synchronized void addCar(Car car) {
        while(cars.size() == maxSize)
            try {
                this.wait();
            } catch(Throwable ignored) {
                Thread.currentThread().interrupt();
                return;
            }
        allNum++;

        cars.offer(car);
        this.notify();
    }

    public synchronized Car getCar() throws InterruptedException {
        waitingNum--;
        controller.setWaitingNum(waitingNum + 1);
        while(cars.size() == 0)
            try {
                this.wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        controller.setWaitingNum(waitingNum);
        Car car = cars.take();
   //     System.out.print("get a car for dealer\n");
        this.notifyAll();
        return car;
    }

    public synchronized int getCurrNum() { return cars.size(); }
    public synchronized int getAllNum() { return allNum; }
    public synchronized void addWaitingNum() {
        waitingNum++;
        controller.setWaitingNum(waitingNum);
    }
    public synchronized void setController(CarStoreController controller) { this.controller = controller; }

}
