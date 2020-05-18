package Factory.Production;

import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;

public class CarStoreController extends Thread {
    private Store<Body> bodyStore;
    private Store<Engine> engStore;
    private Store<Accessory> accStore;
    private CarStore store;
    private Worker[] workers;
    private int currWorker;
    private int num;
    private int waitingNum = 0;

    public CarStoreController(Store<Body> bS, Store<Engine> eS, Store<Accessory> aS, CarStore store, int num, int accNum) {
        this.bodyStore = bS;
        this.engStore = eS;
        this.accStore = aS;
        this.store = store;
        this.num = num;
        currWorker = -1;

        workers = new Worker[num];
        for (int i = 0; i < num; i++) {
            workers[i] = new Worker(bodyStore, engStore, accStore, store, accNum);
        }
        store.setController(this);
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            if(waitingNum > 0) {
                currWorker++;
                if(currWorker == num) currWorker = 0;

            //    System.out.print("asking for a car\n");
                workers[currWorker].makeCar();
                waitingNum--;
            }
        }

    }

    public void setWaitingNum(int waitingNum) { this.waitingNum = waitingNum; }

}
