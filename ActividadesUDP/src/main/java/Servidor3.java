import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class Servidor3 {

    private static HashMap <String, String> zonaNombres = new HashMap<>();

    public static void main(String[] args) {
        zonaNombres.put("www.ejemplo.com", "192.168.0.58");
        zonaNombres.put("www.prueba.com", "192.168.0.100");
        zonaNombres.put("www.prueba2.com", "192.168.0.101");

        try {
            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("Servidor DNS en puerto 5000");

            byte[] buffer = new byte[4096];

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String consulta = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Consulta recibida: " + consulta);

                String respuestaIP = zonaNombres.getOrDefault(consulta, "No encontrado");

                byte[] datosRespuesta = respuestaIP.getBytes();

                DatagramPacket respuesta = new DatagramPacket(
                        datosRespuesta, datosRespuesta.length,
                        paquete.getAddress(), paquete.getPort()
                );
                socket.send(respuesta);
                System.out.println("Respuesta enviada: " + respuestaIP);
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor DNS: " + e.getMessage());
        }
    }
}