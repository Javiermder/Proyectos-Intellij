import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ejercicio01 {

    public static void main(String[] args) {

//Creamos el processbuilder
        ProcessBuilder pb = new ProcessBuilder("systeminfo");
        pb.redirectErrorStream(true);
        try {
           Process p = pb.start(); //ejecutamos el comando y hay que recoger la salida

            //Vamos a analizar la salida del comando
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Nombre del sistema operativo") || linea.contains("Memoria física disponible") ){

                    System.out.println(linea.split(":")[1].trim());

                }
                }
            br.close();
            isr.close();
            is.close();

        }catch (Exception e) {
            System.out.println("Error al ejecutar el comando");
        }

 }

}
