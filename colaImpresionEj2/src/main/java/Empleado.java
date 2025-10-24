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
                Thread.sleep(random.nextInt(1000));
            }catch(Exception e){

            }
            colaImpresion.añadirDocumentoCola("Documento");
            colaImpresion.imprimir();
        }


    }
}
