import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {

    private final int PRODUCTION_TIME = 2500;
    private final int TIME_OF_PURCHASE = 2500;

    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    List<Car> cars = new ArrayList<>();

    public void gettingACar() {
        try {
            lock.lock();
            Thread.sleep(PRODUCTION_TIME);
            cars.add(new Car());
            System.out.println(Thread.currentThread().getName() + " выпустил автомобиль BMW");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Car sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " пришел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Продавец: Не могу продать автомобиль - автомобиля нет в наличии!");
                condition.await();
            }
            Thread.sleep(TIME_OF_PURCHASE);
            System.out.println(Thread.currentThread().getName() + " уехал на новом BMW");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return cars.remove(0);
    }
}
