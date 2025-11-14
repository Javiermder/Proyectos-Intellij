import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Como de larga quieres que sea la cola?");
        int largo = sc.nextInt();
        BandejaADN<String> Bandeja = new BandejaADN<>(largo);

        Thread consumidor = new Thread(new AnalizadorADN(Bandeja));
        consumidor.start();
        Thread[] productor = new Thread[3];

        for (int i = 0; i < productor.length; i++) {
            productor[i] = new Thread(new GeneradorADN(Bandeja));
            productor[i].start();
        }

        for (int i = 0; i < productor.length; i++) {
            try {
                productor[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            consumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
