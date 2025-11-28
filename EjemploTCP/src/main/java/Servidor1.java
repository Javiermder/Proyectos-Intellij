import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

public class Servidor1 {

    private static final int PUERTO = 5000;

    // Guardamos todos los clientes conectados (sus escritores)
    private static final ArrayList<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        System.out.println("Servidor de chat PRIVADO iniciado en puerto " + PUERTO);
        System.out.println("Los mensajes entre clientes son privados, pero el servidor los ve todos.\n");

        ServerSocket servidor = new ServerSocket(PUERTO);

        while (true) {
            Socket socketCliente = servidor.accept();

            PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true, Charset.forName("UTF-8"));

            synchronized (clientes) {
                clientes.add(escritor);
            }

            System.out.println("Nuevo cliente conectado. Total conectados: " + clientes.size());

            // Damos un número o nombre simple al cliente para identificarlo
            int id = clientes.size();  // Cliente 1, Cliente 2, etc.

            // Iniciamos su hilo
            Thread hilo = new Thread(new ClienteHandler(socketCliente, escritor, id));
            hilo.start();
        }
    }

    // Envía el mensaje a TODOS LOS DEMÁS clientes (excepto al que lo envió)
    private static void enviarATodosMenosEmisor(String mensaje, PrintWriter emisor) {
        synchronized (clientes) {
            for (PrintWriter pw : clientes) {
                if (pw != emisor) {  // ¡Importante! No enviárselo al que lo escribió
                    pw.println(mensaje);
                }
            }
        }
    }

    // Clase que maneja a cada cliente
    static class ClienteHandler implements Runnable {
        private final Socket socket;
        private final PrintWriter escritor;
        private final int id;

        public ClienteHandler(Socket socket, PrintWriter escritor, int id) {
            this.socket = socket;
            this.escritor = escritor;
            this.id = id;
        }

        @Override
        public void run() {
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "UTF-8"))) {

                String mensaje;
                while ((mensaje = lector.readLine()) != null) {

                    // Mostramos en consola del servidor quién dijo qué
                    System.out.println("Cliente " + id + " dice: " + mensaje);

                    // Enviamos el mensaje a todos los demás clientes
                    enviarATodosMenosEmisor("Cliente " + id + ": " + mensaje, escritor);
                }

            } catch (IOException e) {
                System.out.println("Cliente " + id + " se desconectó.");
            } finally {
                // Eliminar al cliente cuando se vaya
                synchronized (clientes) {
                    clientes.remove(escritor);
                }
                System.out.println("Cliente " + id + " desconectado. Quedan " + clientes.size() + " conectados.\n");
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
}