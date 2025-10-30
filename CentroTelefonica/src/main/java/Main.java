public class Main {

    public static void main(String[] args) {

        ColaLlamadas cola = new ColaLlamadas();

        Thread[] productores = new Thread[3];
        Thread[] consumidores = new Thread[2];

        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Thread(new Clientes(cola));
            productores[i].start();
        }

        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Thread(new Operadores(cola));
            consumidores[i].start();
        }

        for (int i = 0; i < productores.length; i++) {
            try {
                productores[i].join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < consumidores.length; i++) {
            try {
                consumidores[i].join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
