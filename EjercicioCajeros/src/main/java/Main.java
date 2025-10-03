public class Main {
    public static void main(String[] args) {

    Cuenta cuenta = new Cuenta(1000);
    Thread[] Threads = new Thread[10];
        for (int i = 0; i < Threads.length; i++) {
            Threads[i] = new Cajero("Cajero");
        }

    }


}

