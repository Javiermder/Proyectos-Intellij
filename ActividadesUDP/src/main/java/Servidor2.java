import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class Servidor2 {

    public static void main(String[] args) {

        Set<InetSocketAddress> clientes = new HashSet<>();
        try (DatagramSocket socket = new DatagramSocket(5000)) {
            System.out.println("Servidor UDP en puerto 5000");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                socket.receive(paquete);

                InetSocketAddress remitente = new InetSocketAddress(paquete.getAddress(), paquete.getPort());

                new Thread(() -> {
                    int contador = 0;
                    while (true) {
                        try {
                            contador++;

                            String mensaje = "FRAME#" + contador;
                            byte[] datos = mensaje.getBytes();
                            DatagramPacket frame = new DatagramPacket(datos, datos.length, remitente.getAddress(), remitente.getPort());
                            socket.send(frame);
                            System.out.println("FRAME#" +contador + "enviado");
                            Thread.sleep(100);
                        } catch (Exception e) {
                            System.out.println("Error al enviar: " + e.getMessage());
                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }


    }

}