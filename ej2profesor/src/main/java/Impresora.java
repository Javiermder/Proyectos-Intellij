public class Impresora implements Runnable{

    private ColaImpresion cola;

    public Impresora(ColaImpresion cola){
        this.cola = cola;
    }


    @Override
    public void run() {

        while(true){
            synchronized (cola){
                if(cola.estaVacia()) {
                    try {
                        cola.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                String documento =  cola.sacar();
                System.out.println("Documento: " + documento);

                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                cola.notifyAll();
            }//SYNCRONIZED

        }

    }
}
