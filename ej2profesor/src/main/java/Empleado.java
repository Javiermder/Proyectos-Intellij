public class Empleado implements Runnable {

    private ColaImpresion cola;

    public Empleado(ColaImpresion cola) {
        this.cola = cola;
    }



    @Override
    public void run() {
        int contador = 0;

        while (true) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }


            synchronized (cola) {
                while (cola.estaLlena()) {
                    try {
                        cola.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }

                cola.insertar("Documento-" + contador);
                contador++;
                cola.toString();
                cola.notifyAll();

            }
        }


    }
}
