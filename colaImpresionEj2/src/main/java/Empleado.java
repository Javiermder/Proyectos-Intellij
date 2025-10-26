import java.util.Random;

public class Empleado implements Runnable{

    colaImpresion colaImpresion;

    public Empleado(colaImpresion colaImpresion){
        this.colaImpresion = colaImpresion;
    }


    @Override
    public void run() {
        Random random = new Random();
        while(true){
            try {
                Thread.sleep(random.nextInt(3000));
            }catch(Exception e){

            }
            colaImpresion.añadirDocumentoCola("Documento");
            colaImpresion.imprimir("Soy un empleado y e añadido uno a la cola");
        }


    }
}
