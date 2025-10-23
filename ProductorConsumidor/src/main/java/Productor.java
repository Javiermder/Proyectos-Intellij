import java.util.Random;

public class Productor implements Runnable {

    private Cola cola;

    public Productor( Cola cola ) {
        this.cola = cola;

    }
    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            try {
                Thread.sleep(rand.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (cola) {

                while(cola.estaLlena()){

                    try {
                        cola.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }

                cola.insertar(rand.nextInt(10));
                cola.imprimir();
                cola.notifyAll();


            }
        }
    }
}
