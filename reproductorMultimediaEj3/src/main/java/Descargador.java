import java.nio.Buffer;
import java.util.Random;

public class Descargador implements Runnable{

    BufferStream Buffer;

    public Descargador(BufferStream Buffer){
        this.Buffer = Buffer;
    }

    @Override
    public void run(){
        Random rand = new Random();
        int contador =0;
        while(true){
            try{
                Thread.sleep(rand.nextInt(3000));
            }catch(InterruptedException e){
                System.out.println(e);
            }
            Buffer.AñadirFragmento("Fragmento"+contador);
            contador++;
            Buffer.imprimir();
        }
    }
}
