import java.util.ArrayList;
import java.util.Random;

public class BandejaADN<T> {
    int max;
    private ArrayList<T> cola;

    BandejaADN(int max) {
        this.max = max;
        cola = new ArrayList<>();
    }


    //METODO PRODUCTORES
    public void añadirADN(T elemento) {

        Random rand = new Random();

        synchronized (this){
            while(cola.size() == max){

                try {
                    wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }


            }
            cola.add(elemento);
            notifyAll();
        }

    }

    //METODO PARA CONSUMIDORES
    public T sacarADN(){
        synchronized (this){
            while(cola.size() == 0){
                try {
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T elemento = cola.removeFirst();
            this.notifyAll();
            return elemento;
        }
    }

    public void imprimir(){
        synchronized (this){
            System.out.println(cola.toString());
        }
    }

}
