package Factory.Production;

public class Dealer extends Thread {
    private long startTime;
    private CarStore carStore;
    private int timeForCar, number;
    private boolean useLog;
    private final MyLogger logger;

    public Dealer(CarStore carStore, int timeForCar, boolean useLog, int number, MyLogger logger) {
        this.carStore = carStore;
        this.timeForCar = timeForCar;
        this.useLog = useLog;
        this.number = number;
        this.logger = logger;

        startTime = System.currentTimeMillis();
    }

    public void setTime(int time) {
        timeForCar = time;
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (this) {
                    sleep(timeForCar * 1000);
                }
                carStore.addWaitingNum();
             //   System.out.print("Dealer add waiting num\n");
                Car car = carStore.getCar();
                long currTime = System.currentTimeMillis();

                if(useLog) {
                    String info = "Time: " + ((currTime - startTime) / 1000) + ", Dealer: " + number + "," + car.toString();

                    synchronized (logger) {
                        logger.getIfo(info);
                    }
                }

          //      System.out.print("Sale a car\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
