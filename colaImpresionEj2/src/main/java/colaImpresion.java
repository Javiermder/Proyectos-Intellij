import java.util.ArrayList;

public class colaImpresion<T> {
    //El max para que no haya mas de un maximo de colas
    int max;
    //Array de las colas de impresion
    private ArrayList<T> colaImpresion;


    public colaImpresion(int max) {
    this.max = max;
    this.colaImpresion = new ArrayList<>();
    }

    public colaImpresion() {
        this(5);
    }

    //METODO PARA LOS EMPLEADOS(CONSUMIDORES)
    public void añadirDocumentoCola(T documento) {

        synchronized (this) {
            while(colaImpresion.size() == max) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            colaImpresion.add(documento);
            this.notifyAll();

        }
    }

    //METODO PARA LA IMPRESORA(PRODUCTOR)
    public T obterDocumentoCola() {

        synchronized (this) {
            while (colaImpresion.size() == 0) {
                try {
                    //HACEMOS QUE ESPERE COLA MIENTRAS QUE NO SALGA DEL BUCLE PORQUE LA COLA ESTA VACIA
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //CUANDO SALE DEL BUCLE PORQUE ALGUN PRODUCTOR YA A AÑADIDO ELEMENTOS A LA COLA BORRA EL PRIMERO
            T elemento = colaImpresion.removeFirst();
            //CUANDO LO BORRA AVISA A LOS PRODUCTORES DE QUE A BORRADO POR SI EN CASO DE QUE ESTE LLENA LA COLA PUEDAN PRODUCIR OTRO
            this.notifyAll();
            return elemento;

        }

    }

    public void imprimir(String quiensoy) {
        synchronized (colaImpresion) {
            System.out.println(quiensoy +"Cola " + colaImpresion.toString());
        }
    }
}
