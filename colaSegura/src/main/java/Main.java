public class Main {

    public static void main(String[] args) {

        Cola<Integer> cola = new Cola<>();

        Thread[] productores = new Thread[10];
        Thread[] consumidores = new Thread[10];

        for (int i = 0; i < 10; i++) {
            productores[i] = new Thread(new Productor(cola));
            consumidores[i] = new Thread(new Consumidor(cola));
            productores[i].start();
            consumidores[i].start();
        }

        for (int i = 0; i < 10; i++) {
            try{
                productores[i].join();
                consumidores[i].join();

            }catch (Exception e){
                e.printStackTrace();
            }
            }

    }
}
