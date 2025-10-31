import java.io.BufferedReader; // Permite leer texto de una entrada de manera eficiente
import java.io.InputStream; // Representa una secuencia de entrada de bytes
import java.io.InputStreamReader; // Convierte bytes en caracteres usando un charset
import java.nio.charset.Charset; // Permite especificar el conjunto de caracteres para la conversión

public class Ejercicio03 {

    public static void main(String[] args) {

        // Creamos un ProcessBuilder para ejecutar el comando 'nslookup www.google.com'
        ProcessBuilder pb = new ProcessBuilder("nslookup", "www.google.com");

        // Redirigimos el flujo de error al flujo de salida estándar
        pb.redirectErrorStream(true);

        String ip2 = ""; // Variable para almacenar la IP obtenida

        try {
            // Iniciamos el proceso que ejecuta el comando
            Process p = pb.start();

            // Obtenemos el flujo de salida del proceso
            InputStream is = p.getInputStream();

            // Convertimos el flujo de bytes en caracteres usando el charset cp850 (común en Windows)
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));

            // Usamos BufferedReader para leer línea por línea de forma eficiente
            BufferedReader br = new BufferedReader(isr);

            String linea;

            // Leemos cada línea de la salida del comando
            while ((linea = br.readLine()) != null) {
                // Buscamos la línea que contiene la palabra "Addresses"
                if (linea.contains("Addresses")) {
                    System.out.println("La ip es: ");
                    // Extraemos la IP dividiendo la línea por doble espacio y tomando la segunda parte
                    String ip = linea.split("  ")[1].trim();
                    System.out.println(ip);
                    ip2 = ip; // Guardamos la IP para usarla en el siguiente comando
                }
            }

            // Cerramos los flujos para liberar recursos
            br.close();
            isr.close();
            is.close();

        } catch (Exception e) {
            // En caso de error al ejecutar el comando, mostramos un mensaje
            System.out.println("Error al ejecutar el comando");
        }

        // Creamos otro ProcessBuilder para ejecutar el comando 'ping' con la IP obtenida
        ProcessBuilder pb2 = new ProcessBuilder("ping", ip2);

        // Redirigimos el flujo de error al flujo de salida estándar
        pb2.redirectErrorStream(true);

        try {
            // Iniciamos el proceso que ejecuta el comando
            Process p2 = pb2.start();

            // Obtenemos el flujo de salida del proceso
            InputStream is2 = p2.getInputStream();

            // Convertimos el flujo de bytes en caracteres usando el charset cp850
            InputStreamReader isr2 = new InputStreamReader(is2, Charset.forName("cp850"));

            // Usamos BufferedReader para leer línea por línea
            BufferedReader br2 = new BufferedReader(isr2);

            String linea;

            // Leemos y mostramos cada línea de la salida del comando 'ping'
            while ((linea = br2.readLine()) != null) {
                System.out.println(linea);
            }

            // Cerramos los flujos para liberar recursos
            br2.close();
            isr2.close();
            is2.close();

        } catch (Exception e) {
            // En caso de error al ejecutar el comando, mostramos un mensaje
            System.out.println("Error al ejecutar el comando");
        }

    }

}