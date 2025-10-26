import java.util.Random;

public class Reproductor implements Runnable {

    BufferStream Buffer;

    Reproductor(BufferStream Buffer){
        this.Buffer = Buffer;
    }

    @Override
    public void run(){
        Random rand = new Random();

        while(true){
            try {
                Thread.sleep(rand.nextInt(2500));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Buffer.obtenerFragmento();
            Buffer.imprimir();
        }

    }
}
