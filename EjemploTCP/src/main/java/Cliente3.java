import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente3 {

    private static final String HOST = "localhost";  // Cambiar a IP del servidor si es otra máquina
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PUERTO);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8")
                );
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado al servidor calculadora en " + HOST + ":" + PUERTO);

            // Mostrar mensaje de bienvenida del servidor
            String bienvenida = in.readLine();
            System.out.println("\n" + bienvenida);

            // Hilo para leer respuestas del servidor en tiempo real
            Thread hiloLectura = new Thread(() -> {
                String linea;
                try {
                    while ((linea = in.readLine()) != null) {
                        System.out.println("\n" + linea);
                        System.out.print("» Operación: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConexión perdida con el servidor.");
                }
            });
            hiloLectura.start();

            // Bucle principal del menú
            while (true) {
                System.out.print("» Operación: ");
                String comando = scanner.nextLine().trim();

                if (comando.equalsIgnoreCase("salir")) {
                    out.println("SALIR");
                    break;
                }

                if (!comando.isEmpty()) {
                    out.println(comando);
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Host desconocido: " + HOST);
        } catch (ConnectException e) {
            System.err.println("No se pudo conectar. ¿Está el servidor encendido?");
        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }

        System.out.println("Cliente cerrado.");
    }
}