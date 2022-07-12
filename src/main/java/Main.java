public class Main {

    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();

        for (int i = 0; i < 10; i++) {
            new Thread(null, shop::sellCar, "Покупатель-" + i).start();
        }
        new Thread(null, () -> {
            try {
                shop.gettingACar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Произведитель").start();
    }
}
