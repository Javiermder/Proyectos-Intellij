
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ejercicio02 {

    public static void main(String[] args) {
        // Creamos el processbuilder con los argumentos separados
        ProcessBuilder pb = new ProcessBuilder("wmic", "logicaldisk", "get", "size");
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();

            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
            isr.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }
}