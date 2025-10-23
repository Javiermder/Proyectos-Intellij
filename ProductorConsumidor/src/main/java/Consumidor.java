import java.util.Random;

public class Consumidor implements Runnable {

    private Cola cola;

    public Consumidor(Cola cola){
        this.cola = cola;
    }

    @Override
    public void run(){
        Random rand = new Random();

        while(true){
            try {
                Thread.sleep(rand.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (cola){

                while (cola.estaVacia()){
                    try {
                        cola.wait();
                    } catch (java.lang.Exception e) {
                        throw new RuntimeException(e);
                    }


                }
                Integer i = cola.obtener();
                System.out.println("Elemento CONSUMIDO: " + i);
                cola.imprimir();

            }
        }

    }
}
