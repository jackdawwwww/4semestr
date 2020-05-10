package Factory.Production;

import Factory.Products.ProductFactory;
import Factory.Products.ProductType;

public class Supplier extends Thread {
    private ProductFactory factory;
    private Store store;
    private int timeForProduct;
    private ProductType type;
    private int num;


    public Supplier(Store store, int time, ProductType type, int num) {
        this.factory = new ProductFactory();
        this.store = store;
        this.timeForProduct = time;
        this.type = type;
        this.num = num;
    }

    public void setTime(int time) {
 //       System.out.print("Set time " + time + "\n");
        timeForProduct = time;
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
          //  System.out.println(store.getAllNum() + " was made at all\n" + store.getCurrNum() + " is at Store now\n" );
            try {
                boolean flag = false;
                for(int i = 0; i < num; i++) {
                    if (flag = (store.getMaxSize() != store.getCurrNum())) {
                 //       System.out.print("Supply a " + type + "\n");
                        store.push(factory.getProduct(type));
                    }
                }

                if(flag)
                    synchronized (this) {
                        sleep(1000 * timeForProduct);
                   }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
