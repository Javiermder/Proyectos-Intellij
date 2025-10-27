import java.util.ArrayList;
import java.util.Random;

public class BufferStream<T> {

    int max ;
    private ArrayList<T> buffer;

    public BufferStream(int max){
        this.max = max;
        buffer = new ArrayList<>();  
    }

    //ESTE CONSTRUCTOR REALMENTE LLAMA AL CONSTRUCTOR DE ARRIBA
    public BufferStream(){
        this(5);
    }

    //METODO PARA LOS PRODUCTORES
    public void AñadirFragmento(T fragmento){

        Random random = new Random();
        synchronized (this){
            while(buffer.size() == max){
                try{
                    this.wait();

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            buffer.add(fragmento);
            this.notifyAll();
        }
    }

    //METODO PARA LOS CONSUMIDORES (REPRODUCTOR)
    public T obtenerFragmento(){
        Random random = new Random();
        synchronized (this){
            while(buffer.size() == 0){
                try{
                    this.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            T elemento = buffer.removeFirst();
            this.notifyAll();
            return elemento;
        }
    }

    public void  imprimir(){
        synchronized (this){
            System.out.println("Cola : " + buffer.toString());
        }
    }

}
