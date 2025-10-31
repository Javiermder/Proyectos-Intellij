import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ejercicio04 {
    public static void main(String[] args) {

        // Creamos el ProcessBuilder para ejecutar 'nslookup www.google.com'
        ProcessBuilder pb = new ProcessBuilder("nslookup", "www.google.com");
        pb.redirectErrorStream(true); // Redirige errores al flujo de salida estándar

        String ip2 = ""; // Variable para guardar la IP

        try {
            Process p = pb.start(); // Ejecutamos el comando

            // Capturamos la salida del proceso
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;
            boolean ipEncontrada = false;

            // Leemos línea por línea la salida del comando
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Addresses") || linea.contains("Address")) {
                    // Intentamos extraer la IP
                    String[] partes = linea.trim().split(" ");
                    String posibleIp = partes[partes.length - 1].trim();

                    if (posibleIp.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                        System.out.println("La IP encontrada es: " + posibleIp);
                        ip2 = posibleIp;
                        ipEncontrada = true;
                        break;
                    }
                }
            }

            br.close();
            isr.close();
            is.close();

            if (!ipEncontrada) {
                System.out.println("No se pudo encontrar una IP válida en la salida de nslookup.");
                return; // Salimos del programa si no hay IP
            }

        } catch (Exception e) {
            System.out.println("Error al ejecutar el comando nslookup: " + e.getMessage());
            return; // Salimos del programa si hay error
        }

        // Creamos el ProcessBuilder para hacer ping a la IP obtenida
        ProcessBuilder pb2 = new ProcessBuilder("ping", ip2);
        pb2.redirectErrorStream(true);

        try {
            Process p2 = pb2.start(); // Ejecutamos el comando ping

            InputStream is2 = p2.getInputStream();
            InputStreamReader isr2 = new InputStreamReader(is2, Charset.forName("cp850"));
            BufferedReader br2 = new BufferedReader(isr2);

            String linea;
            boolean respuestaRecibida = false;

            // Leemos la salida del ping
            while ((linea = br2.readLine()) != null) {
                System.out.println(linea);
                if (linea.contains("Respuesta desde") || linea.contains("bytes=")) {
                    respuestaRecibida = true;
                }
            }

            br2.close();
            isr2.close();
            is2.close();

            if (!respuestaRecibida) {
                System.out.println("No se recibió respuesta del servidor. Puede haber un problema de red.");
            }

        } catch (Exception e) {
            System.out.println("Error al ejecutar el comando ping: " + e.getMessage());
        }
    }

}
