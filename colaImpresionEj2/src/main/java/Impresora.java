import java.util.Random;

public class Impresora implements Runnable{
    colaImpresion colaImpresion;

    public Impresora(colaImpresion colaImpresion) {
        this.colaImpresion = colaImpresion;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(random.nextInt(2500));
            }catch (InterruptedException e){
                System.out.println(e);
            }
            colaImpresion.obterDocumentoCola();
            colaImpresion.imprimir("Soy la impresora y e quitado un documento de la cola");
        }
    }
}
