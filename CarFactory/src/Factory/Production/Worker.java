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

 //   private int accNum;

    Worker(Store<Body> bodyStore, Store<Engine> engStore, Store<Accessory> accStore, CarStore carStore, int accNum) {
        this.bodyStore = bodyStore;
        this.engStore = engStore;
        this.accStore = accStore;
  //      this.accNum = accNum;
        this.carStore = carStore;
    }

    private Car makeAndGetCar() throws InterruptedException {
 //       ArrayList<Accessory> acc = new ArrayList<>();
 //       for(int i = 0; i < accNum; i++) acc.add(accStore.take());

       return new Car(engStore.take(), bodyStore.take(), accStore.take());
    }

    public void run() {
        try {
            carStore.addCar(makeAndGetCar());
            System.out.print("Made new car\n");
            wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
