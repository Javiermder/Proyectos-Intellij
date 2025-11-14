public class ProbadorClientes {
    public static void main(String[] args) {
        Thread[] clientes = new Thread[1000];
        for(int i = 0; i < clientes.length; i++){
            clientes[i] = new Thread(new ClienteHilo(i));
            clientes[i].start();
        }


    }
}
