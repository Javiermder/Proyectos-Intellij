import java.util.Random;

public class Cliente implements Runnable{

    private Cuenta origen;
    private Cuenta destino;
    private float cantidad;


    public Cliente(Cuenta origen, Cuenta destino, float cantidad) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
    }




    @Override
    public void run() {

        Random rand = new Random();

        try {

            Thread.sleep(rand.nextInt(100));




        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        Cuenta c1;
        Cuenta c2;

        if(origen.getNombre().equals("c1")){

            c1 = origen;
            c2 = destino;

        }else{
            c1 = destino;
            c2 = origen;
        }



        //Tenemos que bloquear la cuenta y como tenemos bloqueada la primera y esa ya es nuestra bloqueamos la segunda para que no nos la quiten

        System.out.println("Vamos a bloquear las cuentas .....................");
        synchronized (c1) {
            System.out.println("CUENTA ORIGEN BLOQUEADA .....................");

            synchronized (c2) {
                System.out.println("CUENTA DESTINO BLOQUEADA .....................");

                //Si entra es que hay suficiente dinero en la cuenta de origen por lo que puedo hacer la trasaccion
                if(origen.getSaldo() > cantidad){
                    origen.setSaldo(origen.getSaldo() - cantidad);
                    destino.setSaldo(destino.getSaldo() + cantidad);
                    System.out.println("TRANSFERENCIA DESDE " + origen.getNombre() );

                }



            }

        }

    }
}
