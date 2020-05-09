package Factory.Production;

public class Dealer extends Thread {
    CarStore carStore;
    Car car;
    int timeForCar;

    public Dealer(CarStore carStore, int timeForCar) {
        this.carStore = carStore;
        this.timeForCar = timeForCar;
    }

    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                sleep(timeForCar * 1000);
                carStore.addWaitingNum();
                System.out.print("Dealer add waiting num\n");
                car = carStore.getCar();

                System.out.print("Sale a car\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
