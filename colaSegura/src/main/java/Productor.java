import java.util.Random;

public class Productor implements Runnable {
     Cola cola;

     public Productor(Cola cola) {
         this.cola = cola;

     }


    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            try {
                Thread.sleep(rand.nextInt(1000));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cola.insertar(rand.nextInt(1000));
            cola.imprimir();
        }
    }

}
