import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Cliente1 {
    private static final String HOST = "192.168.0.100";
    private static final int PUERTO = 5000;
    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PUERTO);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8")
                )
        ) {
            System.out.println("Conectado al servidor de chat en " + HOST + ":" + PUERTO);
            System.out.println("Escribe mensajes. Escribe 'salir' para desconectarte.\n");
            // Hilo que se encarga de LEER todo lo que llega del servidor
            Thread hiloLectura = new Thread(() -> {
                String mensaje;
                try {
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println("\n" + mensaje);  // Muestra mensaje recibido
                        System.out.print("Tú: ");  // Prompt para seguir escribiendo
                    }
                } catch (IOException e) {
                    System.out.println("\nConexión cerrada por el servidor.");
                }
            });
            hiloLectura.start();
            // Bucle principal: solo envía lo que el usuario escribe
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Tú: ");
                String texto = scanner.nextLine();
                if ("salir".equalsIgnoreCase(texto.trim())) {
                    salida.println("salir");
                    break;
                }
                salida.println(texto);  // Envía al servidor
            }
        } catch (UnknownHostException e) {
            System.err.println("No se pudo encontrar el host: " + HOST);
        } catch (IOException e) {
            System.err.println("Error de conexión. ¿Está el servidor encendido?");
            e.printStackTrace();
        }
        System.out.println("Cliente desconectado.");
    }
}