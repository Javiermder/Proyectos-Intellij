import java.util.ArrayList;

public class Cola<T> {

    private int max;
    private ArrayList<T> cola;

    public Cola(int max) {
        this.max = max;
        this.cola = new ArrayList<>();
    }

    public Cola() {
        this(10);

    }

    //inserta un elemento de la cola
    public void insertar(T elemento) {

        synchronized (this) {

            while(cola.size() == max) {
                try {

                    this.wait();

                }catch(Exception e) {
                    System.out.println(e);
                }
            }

            cola.add(elemento);
            System.out.println("COla " + cola.toString());
            this.notifyAll();

        }

    }

    public  T obtener() {
        synchronized (this) {
            while(cola.size() == 0) {
                try {
                    this.wait();
                }catch(Exception e) {
                    System.out.println(e);
                }
            }
            T elemento = cola.removeFirst();

            System.out.println("COla " + elemento.toString());
            this.notifyAll();
            return elemento;
        }
    }

    public void imprimir() {
        synchronized (this) {
            System.out.println("Cola " + cola.toString());
        }
    }

}
