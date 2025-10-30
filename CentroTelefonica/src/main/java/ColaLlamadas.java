import java.util.ArrayList;

public class ColaLlamadas<T> {
    int max;
    private ArrayList<T> lista;


    public ColaLlamadas() {
        this.max = 10;
        this.lista = new ArrayList<>();
    }

    //METODO PARA PRODUCTORES
    public void AñadirLlamada(T elemento) {

        synchronized (this) {
            while (this.lista.size() >= this.max) {
                try {
                    wait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            this.lista.add(elemento);
            notifyAll();

        }
    }


    public T CogerLLamadas() {
        synchronized (this) {
            while (this.lista.size() == 0) {
                try {
                    wait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            T elemento = this.lista.removeFirst();
            notifyAll();
            return elemento;
        }
    }

    public void imprimir(){
        synchronized (this) {
            System.out.println(lista.toString());
        }
    }


}
