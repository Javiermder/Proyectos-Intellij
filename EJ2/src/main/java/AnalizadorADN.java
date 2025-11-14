import java.util.Random;

public class AnalizadorADN<T> implements Runnable{
    BandejaADN Bandeja;

     AnalizadorADN(BandejaADN bandeja) {
        this.Bandeja = bandeja;
    }

    @Override
    public void run() {
        Random random = new Random();
            while(true){
                try{
                    Thread.sleep(random.nextInt(1000));
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                Object adn = Bandeja.sacarADN();
                System.out.println("[ANALIZADOR] Fragmento recibido : " + adn);
            }



    }
}
