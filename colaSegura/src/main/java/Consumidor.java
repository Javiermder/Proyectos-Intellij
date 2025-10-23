import java.util.Random;

public class Consumidor implements Runnable {

    Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;

    }

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            try {
                Thread.sleep(rand.nextInt(1050));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cola.obtener();
            cola.imprimir();
        }
    }
}
