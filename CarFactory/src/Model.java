import Factory.Production.*;
import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;
import Factory.Products.ProductType;
import ThreadPool.CustomThreadPool;

import java.io.IOException;
import java.util.Properties;

public class Model {
    public static void main(String[] args) throws IOException {
        int bSize, eSize, aSize, cSize, wNum, dNum, accNum;
        boolean useLog;

        Store<Body> bodyStore;
        Store<Engine> engineStore;
        Store<Accessory> accessoryStore;
        CarStore carStore;
        CarStoreController carStoreController;
        Supplier bodySupplier, engSupplier, accSupplier;
        Dealer[] dealers;
        Worker[] workers;

        int timeForEngine = 1, timeForBody = 1, timeForAcc = 1, timeForDeal = 3;

        Properties properties = new Properties();
        properties.load(Model.class.getResourceAsStream("Settings.properties"));

        bSize = Integer.parseInt(properties.getProperty("bodyStSize"));
        eSize = Integer.parseInt(properties.getProperty("engStSize"));
        aSize = Integer.parseInt(properties.getProperty("accStSize"));
        cSize = Integer.parseInt(properties.getProperty("carStSize"));
        wNum = Integer.parseInt(properties.getProperty("workersN"));
        dNum = Integer.parseInt(properties.getProperty("dealersN"));
        accNum = Integer.parseInt(properties.getProperty("accN"));
        useLog = Boolean.parseBoolean(properties.getProperty("logSale"));

        bodyStore = new Store<>(bSize);
        engineStore = new Store<>(eSize);
        accessoryStore = new Store<>(aSize);
        carStore = new CarStore(cSize);

        accSupplier = new Supplier(accessoryStore, timeForAcc, ProductType.Accessory, accNum);
        bodySupplier = new Supplier(bodyStore, timeForBody, ProductType.Body, 1);
        engSupplier = new Supplier(engineStore, timeForEngine, ProductType.Engine, 1);

        carStoreController = new CarStoreController(bodyStore, engineStore, accessoryStore, carStore, wNum, accNum);

        dealers = new Dealer[dNum];
        workers = carStoreController.getWorkers();

        for (int i = 0; i < dNum; i++)
            dealers[i] = new Dealer(carStore, timeForDeal);


        CustomThreadPool customThreadPool = new CustomThreadPool(13);

        customThreadPool.execute(carStoreController);
        customThreadPool.execute(accSupplier);
        customThreadPool.execute(bodySupplier);
        customThreadPool.execute(engSupplier);

        for (int i = 0; i < dNum; i++)
            customThreadPool.execute(dealers[i]);

        for (int i = 0; i < wNum; i++)
            customThreadPool.execute(workers[i]);


        //customThreadPool.shutdown();
    }

}
