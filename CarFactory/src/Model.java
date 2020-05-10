import Factory.Production.*;
import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;
import Factory.Products.ProductType;
import ThreadPool.CustomThreadPool;

import java.io.IOException;
import javax.swing.*;

public class Model {
    private static MyProperties properties;
    private CustomThreadPool customThreadPool;
    private Controller controller;
    private Timer timer;

    private Store<Body> bodyStore;
    private Store<Engine> engineStore;
    private Store<Accessory> accessoryStore;
    private CarStore carStore;
    private CarStoreController carStoreController;
    private Supplier bodySupplier, engSupplier, accSupplier;
    private Dealer[] dealers;
    private Worker[] workers;

    Model(Controller controller) throws Exception {
        this.controller = controller;
        init();
    }

    void init() throws IOException {
       properties = new MyProperties();
        MyLogger logger = new MyLogger();

       bodyStore = new Store<>(properties.bSize());
       engineStore = new Store<>(properties.eSize());
       accessoryStore = new Store<>(properties.aSize());
       carStore = new CarStore(properties.cSize());

       int time = 2;
       accSupplier = new Supplier(accessoryStore, time, ProductType.Accessory, properties.accNum());
       bodySupplier = new Supplier(bodyStore, time, ProductType.Body, 1);
       engSupplier = new Supplier(engineStore, time, ProductType.Engine, 1);

       carStoreController = new CarStoreController(bodyStore, engineStore, accessoryStore,
                                                    carStore, properties.wNum(), properties.accNum());

       dealers = new Dealer[properties.dNum()];
       workers = carStoreController.getWorkers();

        for (int i = 0; i < properties.dNum(); i++)
           dealers[i] = new Dealer(carStore, time, properties.useLog(), i, logger);

        timer = new javax.swing.Timer(100, controller.getView());
    }


    void start() throws Exception {
        customThreadPool = new CustomThreadPool(13);

        customThreadPool.submit(accSupplier);
        customThreadPool.submit(bodySupplier);
        customThreadPool.submit(engSupplier);
        for (int i = 0; i < properties.dNum(); i++)
            customThreadPool.submit(dealers[i]);

        for (int i = 0; i < properties.wNum(); i++)
            customThreadPool.submit(workers[i]);
        customThreadPool.submit(carStoreController);

        timer.start();
    }

    void cancel() {
        System.out.print("exit");
        customThreadPool.shutdownNow();
    }

    void work() {
        controller.setEngValues(engineStore.getAllNum(), engineStore.getCurrNum());
        controller.setBodyValues(bodyStore.getAllNum(), bodyStore.getCurrNum());
        controller.setAccValues(accessoryStore.getAllNum(), accessoryStore.getCurrNum());
        controller.setCarValues(carStore.getAllNum(), carStore.getCurrNum());

        controller.repaint();
    }

    void setTimeForEngine(int time) {
        System.out.print("edit time for " + time + "\n");
        engSupplier.setTime(time);
    }

    void setTimeForBody(int time) {
        System.out.print("edit time for " + time + "\n");
        bodySupplier.setTime(time);
    }

    void setTimeForAcc(int time) {
        System.out.print("edit time for " + time + "\n");
        accSupplier.setTime(time);
    }

    void setTimeForDeal(int time) {
        System.out.print("edit time for " + time + "\n");
        for(Dealer d: dealers) {
            d.setTime(time);
        }
    }
}
