import java.util.ArrayList;
import java.util.List;

public class Shop {

    public static final int PRODUCTION_TIME = 2500;
    public static final int TIME_OF_PURCHASE = 2000;

    List<Car> cars = new ArrayList<>();

    public synchronized void gettingACar() throws InterruptedException {
        Thread.sleep(PRODUCTION_TIME);
        while (cars.size() < 10) {
            cars.add(new Car());
            System.out.println(Thread.currentThread().getName() + " выпустил автомобиль BMW");
            notify();
        }
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " пришел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Продавец: Не могу продать автомобить - автомобиля нет в наличии!");
                wait();
            }
            Thread.sleep(TIME_OF_PURCHASE);
            System.out.println(Thread.currentThread().getName() + " уехал на новом BMW");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cars.remove(0);
    }
}
