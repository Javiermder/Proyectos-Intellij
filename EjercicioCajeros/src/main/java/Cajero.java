import java.util.Random;

public class Cajero implements Runnable{
    public final String nombre;
        private final Cuenta cuenta;

        public String getNombre() {
            return nombre;
        }

    public Cajero(String nombre, Cuenta cuenta){
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    @Override
    public void run() {

            try{
                int intentos = 1;
                Random tiempoAleatorio = new Random();
                Random saldoAleatorio = new Random();

                while(intentos <= 10){
                    Thread.sleep(tiempoAleatorio.nextInt(20));

                    //Si en el metodo no metemos un sincronice tambien se puede hacer metiendo
                    // un bloque syncroniced aqui

                    float cantidad = tiempoAleatorio.nextInt(251)+50;
                    boolean resultado = cuenta.decrementarSaldo(cantidad);

                    if(!resultado){
                        System.err.println("Saldo insuficiente");
                    }else{
                        System.out.println(getNombre() + " operacion " + intentos + " reatirar" + cantidad);
                    }
                    intentos++;
                }



            }catch(Exception e){
                System.out.println(e);
            }
    }
}
