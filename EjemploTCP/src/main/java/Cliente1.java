import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente1 {
    private final static String COD_TEXTO = "UTF-8";
    private final static int PUERTO_FIJO = 5000; // Mismo puerto que el servidor
    // Host fijo: el cliente siempre intentará conectarse a su propia máquina
    private final static String HOST_FIJO = "192.168.0.102";

    public static void main(String[] args) {
        // Al ser fijos el host y el puerto, no se necesita ningún argumento
        String nomHost = HOST_FIJO;
        int numPuerto = PUERTO_FIJO;


            // try-with-resources principal para gestionar todos los recursos
            try (
                    // 1. Inicializamos la conexión del socket usando el host y puerto fijos
                    Socket socketComunicacion = new Socket(nomHost, numPuerto);

                    // 2. Preparamos la SALIDA al servidor (usando PrintWriter con auto-flush)
                    PrintWriter pwAServidor = new PrintWriter(
                            new OutputStreamWriter(socketComunicacion.getOutputStream(), COD_TEXTO), true
                    );

                    // 3. Preparamos la ENTRADA desde el servidor (usando BufferedReader)
                    BufferedReader brDeServidor = new BufferedReader(
                            new InputStreamReader(socketComunicacion.getInputStream(), COD_TEXTO)
                    )

            ) {
                while (true) {

                    System.out.println("Introduzca un mensaje que mandar o introduzca 'salir' para desconectarse");
                    Scanner sc = new Scanner(System.in);
                    final String mensajeFijo = sc.nextLine();

                    if (mensajeFijo.equals("salir")) {
                        System.exit(1);

                    }

                    System.out.println("Conectado a servidor en " + nomHost + ":" + numPuerto);
                    System.out.println("--- Iniciando comunicación con mensaje personalizado ---");

                    // 1. ENVIAR mensaje fijo
                    System.out.println("Cliente enviando: " + mensajeFijo);
                    pwAServidor.println(mensajeFijo); // Envía el mensaje y hace flush

                    // 2. RECIBIR respuesta
                    String respuesta = brDeServidor.readLine(); //bloqueante

                    if (respuesta == null) {
                        System.out.println("El servidor cerró la conexión inesperadamente después del envío.");
                    } else {
                        System.out.println("Respuesta del servidor : " + respuesta);
                    }
                }


            } catch (UnknownHostException e) {
                // Este error solo ocurriría si 'localhost' no pudiera resolverse (muy raro)
                System.err.println("Host desconocido: " + nomHost);
                System.exit(1);
            } catch (IOException ex) {
                // Este error puede ocurrir si el servidor no está corriendo en 5000
                System.out.println("Excepción de E/S. Asegúrate de que el servidor está corriendo en " + nomHost + ":" + numPuerto);
                ex.printStackTrace();
                System.exit(1);
            }


    }
}