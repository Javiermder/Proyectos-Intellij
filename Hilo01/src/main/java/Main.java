import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Thread h1 = new Thread(new Hilo ("h1"));
        Thread h2 = new Thread(new Hilo ("h2"));
        Thread h3 = new Thread(new Hilo ("h3"));

        h1.start();
        h2.start();
        h3.start();

        try {
            h1.join();
            h2.join();
            h3.join();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.printf("Mensaje final hilo principal");

    }
}




class Hilo implements Runnable {

    private String nombre;

    public Hilo(){}

    public Hilo(String nombre){
        this.nombre = nombre;
    }
    @Override
    public void run() {
        long n = 0;
        while(n < 10000){
            System.out.println(this.nombre);
            n++;
            try {
                Random r = new Random();
                long rand = r.nextLong(5000);
                Thread.sleep(rand);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("Hilo finalizado " + nombre + "\n");

    }

}