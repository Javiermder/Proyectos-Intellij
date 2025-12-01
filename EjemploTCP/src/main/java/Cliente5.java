import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente5 {

    private static final String HOST = "localhost";  // Dirección del servidor (cambiar si está en otra máquina)
    private static final int PUERTO = 5000;          // Puerto del servidor

    public static void main(String[] args) {
        try (
                // Se crea el socket que conecta con el servidor
                Socket socket = new Socket(HOST, PUERTO);

                // Permite enviar mensajes al servidor
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Permite leer mensajes enviados por el servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                // Se usa para leer texto ingresado por el usuario
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Conectado al servidor de juegos");

            // Hilo que se encarga de leer mensajes del servidor sin bloquear el programa principal
            Thread hiloLectura = new Thread(() -> {
                String linea;
                try {
                    // Mientras el servidor envíe líneas, se muestran en consola
                    while ((linea = in.readLine()) != null) {
                        System.out.println("Servidor: " + linea);

                        // Si el servidor envía un mensaje indicando que el juego terminó
                        if (linea.contains("has adivinado")) {
                            System.out.println("Juego terminado. Cerrando cliente...");
                            break;
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Conexión cerrada: " + ex.getMessage());
                }
            });

            hiloLectura.start(); // Inicia el hilo de lectura

            // Bucle principal: el cliente envía números al servidor
            while (true) {
                System.out.print("Introduce un número: ");

                // Leer número introducido por el usuario
                String numero = scanner.nextLine();

                // Enviar número al servidor
                out.println(numero);

                // Si el hilo de lectura terminó, significa que el servidor cerró la conexión
                if (!hiloLectura.isAlive()) {
                    break;
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Servidor desconocido: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
