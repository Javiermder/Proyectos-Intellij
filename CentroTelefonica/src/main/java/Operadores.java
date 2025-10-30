import java.util.Random;

public class Operadores implements Runnable {

    ColaLlamadas colaLlamadas = new ColaLlamadas();

    public Operadores (ColaLlamadas colaLlamadas) {
        this.colaLlamadas = colaLlamadas;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            colaLlamadas.CogerLLamadas();
            colaLlamadas.imprimir();

        }
    }
}
