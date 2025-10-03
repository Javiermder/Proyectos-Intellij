public class Main {

    public static void main(String[] args) {
        Contador contador = new Contador(0);
        Thread[] Threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Threads[i] = new Thread(new Hilo("h" + (i + 1), contador));
        }

        for (int i = 0; i < 10; i++) {
            Threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                Threads[i].join();
            } catch (InterruptedException e) {
            }
        }
        System.out.printf("Contador: %d\n", contador.getContador());
    }
}


class Hilo implements Runnable {
    public String nombre;
    private Contador contador;

    public Hilo(String nombre, Contador contador) {
        this.nombre = nombre;
        this.contador = contador;
    }

    @Override
    public void run() {
        int n = 0;
        while (n < 1000) {
            n++;
            //contador.incrementar();

            //Forma alternativa a incrementar el valor
            //contador.setContador(contador.getContador() + 1);
            synchronized (contador) {
                long temp = contador.getContador();
                temp += 1;
                contador.setContador(temp);

            }
        }
    }
}