import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ejercicio5 {
    public static void main(String[] args) {

        String navegador = "msedge.exe"; // Puedes cambiar esto por otro navegador como "msedge.exe"
        boolean procesoEncontrado = false;

        // Paso 1: Ejecutar 'tasklist' para obtener la lista de procesos activos
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        pb.redirectErrorStream(true); // Redirige errores al flujo de salida estándar

        try {
            Process proceso = pb.start();

            InputStream is = proceso.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                // Comprobamos si la línea contiene el nombre del navegador (ignorando mayúsculas/minúsculas)
                if (linea.toLowerCase().contains(navegador.toLowerCase())) {
                    procesoEncontrado = true;
                    break;
                }
            }

            br.close();
            isr.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al ejecutar 'tasklist': " + e.getMessage());
            return;
        }

        // Paso 2: Si se encontró el proceso, ejecutamos 'taskkill'
        if (procesoEncontrado) {
            System.out.println("Procesos de " + navegador + " encontrados. Intentando cerrarlos...");

            ProcessBuilder pbKill = new ProcessBuilder("taskkill", "/IM", navegador, "/F");
            pbKill.redirectErrorStream(true);

            try {
                Process procesoKill = pbKill.start();

                InputStream is = procesoKill.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
                BufferedReader br = new BufferedReader(isr);

                StringBuilder salida = new StringBuilder();
                String linea;
                while ((linea = br.readLine()) != null) {
                    salida.append(linea).append("\n");
                }

                br.close();
                isr.close();
                is.close();

                // Mostramos la salida del comando taskkill
                System.out.println("Resultado del cierre:");
                System.out.println(salida.toString());

            } catch (Exception e) {
                System.out.println("Error al ejecutar 'taskkill': " + e.getMessage());
            }

        } else {
            System.out.println("No se encontraron procesos de " + navegador + " en ejecución.");
        }
    }

}
