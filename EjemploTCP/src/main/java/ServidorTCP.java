import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    private final static String COD_TEXTO = "UTF-8";
    // Puerto fijo para el servidor
    private final static int PUERTO_FIJO = 5000;

    public static void main(String[] args) {
        // Se ha fijado el puerto a 5000, eliminando la necesidad de argumentos
        int numPuerto = PUERTO_FIJO;

        // Utilizamos try-with-resources para el ServerSocket, asegurando que se cierra.
        try (ServerSocket socketServidor = new ServerSocket(numPuerto)) {
            System.out.printf("Creado socket de servidor en puerto %d. Esperando conexiones de clientes.\n", numPuerto);

            while (true) { // Acepta una conexión de cliente tras otra

                // try-with-resources: Declara el Socket, BufferedReader y PrintWriter aquí.
                // Se cerrarán automáticamente al salir del bloque try, sin necesidad de finally.
                try (
                        Socket socketComunicacion = socketServidor.accept();

                        // Entrada: Usamos BufferedReader para leer líneas de texto del cliente
                        BufferedReader brDelCliente = new BufferedReader(
                                new InputStreamReader(socketComunicacion.getInputStream(), COD_TEXTO)
                        );

                        // Salida: Usamos PrintWriter para enviar texto al cliente.
                        // El 'true' en el constructor activa el auto-flush, simplificando el envío.
                        PrintWriter pwAlCliente = new PrintWriter(
                                new OutputStreamWriter(socketComunicacion.getOutputStream(), COD_TEXTO), true
                        )
                ) {
                    System.out.printf("Cliente conectado desde %s:%d.\n",
                            socketComunicacion.getInetAddress().getHostAddress(),
                            socketComunicacion.getPort());

                    // -----------------------------------------------------
                    // MODIFICACIÓN: Leer solo una línea, ya que el cliente
                    // solo envía un mensaje fijo y luego se cierra.
                    // -----------------------------------------------------
                    String lineaRecibida = brDelCliente.readLine();

                    if (lineaRecibida != null && !lineaRecibida.isEmpty()) {
                        System.out.println("Servidor recibiendo: " + lineaRecibida);

                        // Usamos println(), que escribe la cadena + salto de línea.
                        // El auto-flush envía los datos inmediatamente.
                        pwAlCliente.println("#" + lineaRecibida + "#");
                        System.out.println("Respuesta de eco enviada.");
                    } else {
                        System.out.println("Cliente conectado pero no envió datos o envió una línea vacía.");
                    }

                    System.out.println("Cliente desconectado.");
                    // Los recursos se cierran automáticamente aquí por try-with-resources.

                } catch (IOException ex) {
                    // Captura errores específicos de la gestión de la comunicación con el cliente
                    System.err.println("Excepción durante la gestión del cliente: " + ex.getMessage());
                }
            }

        } catch (IOException ex) {
            // Captura errores al crear el ServerSocket (ej. puerto ocupado)
            System.out.println("Excepción fatal al crear el socket de servidor:");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
