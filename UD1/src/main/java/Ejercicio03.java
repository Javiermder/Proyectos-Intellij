import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ejercicio03 {

    public static void main(String[] args) {

    //Creamos el processbuilder
        ProcessBuilder pb = new ProcessBuilder("nslookup", "www.google.com");
        pb.redirectErrorStream(true);
        String ip2 = "";
        try {
           Process p = pb.start(); //ejecutamos el comando y hay que recoger la salida

            //Vamos a analizar la salida del comando
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Addresses") ) {
                    System.out.println("La ip es: ");
                    String ip = linea.split("  ")[1].trim();
                    System.out.println(ip);
                    ip2 = ip;
                }
                }
            br.close();
            isr.close();
            is.close();

        }catch (Exception e) {
            System.out.println("Error al ejecutar el comando");
        }

        ProcessBuilder pb2 = new ProcessBuilder("ping", ip2);
        pb2.redirectErrorStream(true);
        try {
            Process p2 = pb2.start(); //ejecutamos el comando y hay que recoger la salida

            //Vamos a analizar la salida del comando
            InputStream is2 = p2.getInputStream();
            InputStreamReader isr2 = new InputStreamReader(is2, Charset.forName("cp850"));
            BufferedReader br2 = new BufferedReader(isr2);

            String linea;
            while ((linea = br2.readLine()) != null) {
                    System.out.println(linea);


            }
            br2.close();
            isr2.close();
            is2.close();

        }catch (Exception e) {
            System.out.println("Error al ejecutar el comando");
        }

 }

}
