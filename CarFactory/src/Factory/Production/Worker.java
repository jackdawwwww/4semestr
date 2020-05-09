package Factory.Production;

import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;

import java.util.ArrayList;

public class Worker extends Thread {
    private Store<Body> bodyStore;
    private Store<Engine> engStore;
    private Store<Accessory> accStore;
    private CarStore carStore;
    private int accNum;
    private Car currCar;

    Worker(Store<Body> bodyStore, Store<Engine> engStore, Store<Accessory> accStore, CarStore carStore, int accNum) {
        this.bodyStore = bodyStore;
        this.engStore = engStore;
        this.accStore = accStore;
        this.accNum = accNum;
        this.carStore = carStore;
    }

    public synchronized void makeCar() throws InterruptedException {
        ArrayList<Accessory> acc = new ArrayList<>();
        for(int i = 0; i < accNum; i++) acc.add(accStore.take());

        currCar = new Car(engStore.take(), bodyStore.take(), acc);
        this.notify();
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                carStore.addCar(currCar);

                System.out.print("Made new car\n");
            } finally {

            }
        }
    }

}
