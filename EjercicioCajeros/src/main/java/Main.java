public class Main {
    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta(1000);
        Thread[] Cajeros = new Thread[10];

        System.out.printf("Saldo de la cuenta : " + cuenta.getSaldo());

        for (int i = 0; i <= Cajeros.length; i++) {
            Cajeros[i] = new Thread(new Cajero("Cajero" + (i+1), cuenta));
            Cajeros[i].start();
        }

        //
        for (int i = 0; i <= Cajeros.length; i++) {
            try {
                Cajeros[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Saldo de la cuenta : " + cuenta.getSaldo());

    }


}

