import Factory.Production.*;
import Factory.Products.Accessory;
import Factory.Products.Body;
import Factory.Products.Engine;
import Factory.Products.ProductType;
import ThreadPool.CustomThreadPool;

import java.util.Properties;


public class Model {
    private static Properties properties;
    private static int bSize, eSize, aSize, cSize, wNum, dNum, accNum;
    private static boolean useLog;

    private static Store<Body> bodyStore;
    private static Store<Engine> engineStore;
    private static Store<Accessory> accessoryStore;
    private static CarStore carStore;
    private static CarStoreController carStoreController;
    private static Supplier bodySupplier, engSupplier, accSupplier;
    private static Dealer[] dealers;
    private static Worker[] workers;
    private static int timeForEngine = 1, timeForBody = 1, timeForAcc = 1, timeForDeal = 1;

    static void parseProperties() {
        bSize = Integer.parseInt(properties.getProperty("bodyStSize"));
        eSize = Integer.parseInt(properties.getProperty("engStSize"));
        aSize = Integer.parseInt(properties.getProperty("accStSize"));
        cSize = Integer.parseInt(properties.getProperty("carStSize"));
        wNum = Integer.parseInt(properties.getProperty("workersN"));
        dNum = Integer.parseInt(properties.getProperty("dealersN"));
        accNum = Integer.parseInt(properties.getProperty("accN"));
        useLog = Boolean.parseBoolean(properties.getProperty("logSale"));
    }

    static void init() {
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
            dealers[i] = new Dealer(carStore, timeForDeal, useLog, i);
    }

    public static void main(String[] args) throws Exception {
        properties = new Properties();
        properties.load(Model.class.getResourceAsStream("Settings.properties"));

        parseProperties();
        init();

        CustomThreadPool customThreadPool = new CustomThreadPool(13);

        customThreadPool.submit(accSupplier);
        customThreadPool.submit(bodySupplier);
        customThreadPool.submit(engSupplier);
        for (int i = 0; i < dNum; i++)
            customThreadPool.submit(dealers[i]);

        for (int i = 0; i < wNum; i++)
            customThreadPool.submit(workers[i]);
        customThreadPool.submit(carStoreController);


       // customThreadPool.shutdownNow();
    }

}
