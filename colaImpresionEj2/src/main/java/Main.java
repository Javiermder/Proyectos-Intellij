public class Main {
    public static void main(String[] args) {

        colaImpresion<String> cola = new colaImpresion<>();

        Thread[] empleados = new Thread[3];
        Thread[] impresora = new Thread[1];

        for (int i = 0; i < empleados.length; i++) {
            empleados[i] = new Thread(new Empleado(cola));
            empleados[i].start();

        }
        for (int i = 0; i < impresora.length; i++) {
            impresora[i] = new Thread(new Impresora(cola));
            impresora[i].start();
        }






    }
}
