import java.util.Random;

public class GeneradorADN implements Runnable {

    BandejaADN bandeja;

    public GeneradorADN(BandejaADN bandeja) {
        this.bandeja = bandeja;
    }

    String cadena = "ATCG";
    int indentificador =0;
    @Override
    public void run() {


        Random rand = new Random();
        while(true){
            indentificador++;
            StringBuilder adn = new StringBuilder();

            for (int i = 0; i < 8; i++) {
                Random r = new Random();
                adn.append(cadena.charAt(r.nextInt(4)));
            }
            try {
                Thread.sleep(rand.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }




            bandeja.añadirADN(adn.toString());
            System.out.println("[SECUENCIADOR "+indentificador+"] genrado "+adn.toString());
        }
    }
}
