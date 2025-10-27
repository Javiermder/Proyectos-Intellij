public class Main {
    public static void main(String[] args) {

        ColaImpresion cola = new ColaImpresion();

        Thread[] empleados = new Thread[3];
        Thread impresoras = new Thread(new Impresora(cola));
        impresoras.start();
        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Thread(new Empleado(cola));
            empleados[i].start();
        }
        for (int i = 0; i < empleados.length; i++) {

            try {

                empleados[i].join();
            }catch(Exception e){
                System.out.println(e);
            }
        }

        try {
            impresoras.join();
        }catch(Exception e){
            System.out.println(e);
        }


    }
}
