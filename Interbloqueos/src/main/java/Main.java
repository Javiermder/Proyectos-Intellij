import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Cuenta cuenta1 = new Cuenta("c1", 5000);
        Cuenta cuenta2 = new Cuenta("c2", 3000);

        Random random = new Random();


        System.out.printf("Salgo inicial cuenta 1 ----" + cuenta1.getSaldo());
        System.out.printf("Salgo inicial cuenta 2 ----" + cuenta2.getSaldo());

        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {

            if (random.nextInt(100) < 50) {

                threads[i] = new Thread(new Cliente(cuenta1, cuenta2, 10));

            } else {

                threads[i] = new Thread(new Cliente(cuenta2, cuenta1, 10));

            }
            threads[i].start();

        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {}
        }


        System.out.println("Saldo final cuenta 1 ----" + cuenta1.getSaldo());
        System.out.println("Saldo final cuenta 2 ----" + cuenta2.getSaldo());

    }
}
