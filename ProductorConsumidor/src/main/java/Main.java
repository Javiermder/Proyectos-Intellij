public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Thread[] consumidores = new Thread[5];

        Thread[] productores = new Thread[5];


        for (int i = 0; i < 5; i++){
            consumidores[i] = new Thread(new Consumidor(cola));
            consumidores[i].start();
            productores[i] = new Thread(new Productor(cola));
            productores[i].start();
        }

        for (int i = 0; i < 5; i++){
            try {
                productores[i].join();
                consumidores[i].join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


}
