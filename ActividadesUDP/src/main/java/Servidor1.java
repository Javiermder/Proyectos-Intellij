import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class Servidor1 {

    public static void main(String[] args) {
        // Conjunto para almacenar las direcciones de los clientes conectados
        Set<InetSocketAddress> clientes = new HashSet<>();

        // Se crea un socket UDP en el puerto 5000
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            System.out.println("Servidor UDP en puerto 5000");

            // Buffer para recibir datos (tamaño máximo de 1024 bytes)
            byte[] buffer = new byte[1024];

            // Bucle infinito para mantener el servidor escuchando
            while (true) {
                // Se prepara un paquete para recibir datos
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                // Se recibe el paquete desde un cliente
                socket.receive(paquete); // ✅ CORREGIDO: antes estaba socket.send(paquete)

                // Se extrae el mensaje recibido del paquete
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());

                // Se obtiene la dirección del remitente (cliente que envió el mensaje)
                InetSocketAddress remitente = new InetSocketAddress(paquete.getAddress(), paquete.getPort());

                // Se añade el remitente al conjunto de clientes (evita duplicados)
                clientes.add(remitente);


                // Se reenvía el mensaje a todos los clientes excepto al remitente
                for (InetSocketAddress cliente : clientes) {
                    if (!cliente.equals(remitente)) {
                        byte[] datos = mensaje.getBytes();
                        DatagramPacket envio = new DatagramPacket(
                                datos, datos.length, cliente.getAddress(), cliente.getPort()
                        );
                        socket.send(envio); // Se envía el mensaje al cliente
                    }
                }
            }

        } catch (IOException e) {
            // Manejo de errores de entrada/salida
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

}