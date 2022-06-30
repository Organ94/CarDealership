public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop();

        for (int i = 0; i < 5; i++) {
            new Thread(null, shop::sellCar, "Покупатель-" + i).start();
        }
        new Thread(null, shop::gettingACar, "Произведитель").start();
        new Thread(null, shop::gettingACar, "Произведитель").start();
        new Thread(null, shop::gettingACar, "Произведитель").start();
        new Thread(null, shop::gettingACar, "Произведитель").start();
        new Thread(null, shop::gettingACar, "Произведитель").start();
    }
}
