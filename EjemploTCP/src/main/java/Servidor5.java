import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor5 {

    private final static String COD_TEXTO = "UTF-8";   // Codificación usada para los mensajes
    private final static int PUERTO_FIJO = 5000;        // Puerto donde escucha el servidor

    public static void main(String[] args) {

        Random aleatorio = new Random();
        int numeroAdivinar = aleatorio.nextInt(100);    // Número secreto entre 0 y 99

        try (ServerSocket socketServidor = new ServerSocket(PUERTO_FIJO)) {

            System.out.println("Servidor Iniciado");

            // El servidor queda en espera constante de nuevos clientes
            while(true){

                // Espera a que un cliente se conecte
                Socket socketCliente = socketServidor.accept();

                // Se crea un hilo para atender a cada cliente sin bloquear a otros
                new Thread(() -> {
                    try (
                            // Flujo de entrada: lo que envía el cliente
                            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream(), COD_TEXTO));

                            // Flujo de salida: mensajes que el servidor envía al cliente
                            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
                    ) {

                        // Mensaje inicial para el cliente
                        salida.println("introduce un número para empezar a adivinar");

                        String mensaje;

                        // Bucle para procesar todos los mensajes enviados por el cliente
                        while ((mensaje = entrada.readLine()) != null) {

                            // Convertir el texto recibido a número entero
                            int numeroCliente = Integer.parseInt(mensaje);

                            // Comparación del número del cliente con el número secreto
                            if (numeroCliente == numeroAdivinar) {
                                salida.println("has adivinado el número"); // El cliente ganó
                                break;                                      // Se termina el juego
                            } else if (numeroCliente < numeroAdivinar) {
                                salida.println("El número a adivinar es más grande");
                            } else {
                                salida.println("El número a adivinar es más chico");
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Error con cliente: " + e.getMessage());
                    }
                }).start(); // Iniciar el hilo del cliente
            }

        } catch (Exception e) {
            System.out.println("Servidor no encontrado");
        }
    }
}
