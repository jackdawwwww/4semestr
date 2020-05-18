package Factory.Production;

import Factory.Products.ProductFactory;
import Factory.Products.ProductType;

public class Supplier extends Thread {
    private ProductFactory factory;
    private Store store;
    private int timeForProduct;
    private ProductType type;
    private int num, storeMaxSize, storeCurrNum = 0;


    public Supplier(Store store, int time, ProductType type, int num) {
        this.factory = new ProductFactory();
        this.store = store;
        this.timeForProduct = time;
        this.type = type;
        this.num = num;
        this.storeMaxSize = store.getMaxSize();
        store.setSupplier(this);
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
                    if (flag = (storeMaxSize != storeCurrNum)) {
                 //       System.out.print("Supply a " + type + "\n");
                        store.push(factory.getProduct(type));
                    }
                }

                if(flag)
                    synchronized (this) {
                        wait(1000 * timeForProduct);
                   }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStoreCurrNum(int storeCurrNum) { this.storeCurrNum = storeCurrNum; }
    public void minCurrNum() { storeCurrNum--; }
    public void addCurrNum() { storeCurrNum++; }

}
