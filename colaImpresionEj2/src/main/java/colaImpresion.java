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


    public void añadirDocumentoCola(T documento) {

        synchronized (colaImpresion) {
            while(colaImpresion.size() == max) {
                try {
                    colaImpresion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            colaImpresion.add(documento);
            System.out.println("Cola: " + colaImpresion.toString());
            colaImpresion.notifyAll();

        }
    }

    public T obterDocumentoCola() {
        synchronized (colaImpresion) {
            while (colaImpresion.size() == 0) {
                try {
                    colaImpresion.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T elemento = colaImpresion.removeFirst();
            return elemento;

        }

    }

    public void imprimir() {
        synchronized (this) {
            System.out.println("Cola " + colaImpresion.toString());
        }
    }
}
