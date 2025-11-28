import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente5 {

    private static final String HOST = "localhost";  // Cambiar a IP del servidor si es otra máquina
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PUERTO);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado al servidor de juegos");

            // Hilo que lee constantemente los mensajes del servidor
            Thread hiloLectura = new Thread(() -> {
                String linea;
                try {
                    while ((linea = in.readLine()) != null) {
                        System.out.println("Servidor: " + linea);
                        if (linea.contains("has adivinado")) {
                            System.out.println("Juego terminado. Cerrando cliente...");
                            break;
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Conexión cerrada: " + ex.getMessage());
                }
            });
            hiloLectura.start();

            // Bucle para enviar números al servidor
            while (true) {
                System.out.print("Introduce un número: ");
                String numero = scanner.nextLine();
                out.println(numero);

                if (!hiloLectura.isAlive()) {
                    break; // si el servidor cerró, salimos
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Servidor desconocido: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}