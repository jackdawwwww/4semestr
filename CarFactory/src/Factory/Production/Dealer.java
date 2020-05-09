package Factory.Production;

public class Dealer extends Thread {
    CarStore carStore;
    Car car;
    int timeForCar, number;
    boolean useLog;
    private static MyLogger logger;

    public Dealer(CarStore carStore, int timeForCar, boolean useLog, int number) {
        this.carStore = carStore;
        this.timeForCar = timeForCar;
        this.useLog = useLog;
        this.number = number;

        if(useLog) {
            logger = new MyLogger();
        }
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (this) {
                    sleep(timeForCar * 1000);
                }
                carStore.addWaitingNum();
                System.out.print("Dealer add waiting num\n");
                car = carStore.getCar();

                String info = "<Time>: " + ", Dealer: " + number + "," + car.toString();
                logger.getIfo(info);
                System.out.print("Sale a car\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
