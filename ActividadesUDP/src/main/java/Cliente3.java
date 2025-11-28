import java.io.IOException;
import java.net.*;

public class Cliente3 {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            InetAddress serverIP = InetAddress.getByName("192.168.0.109");
            int port = 5000;

            String mensajeDominio = "www.ejemplo.com";
            String mensajeIp = "192.168.0.58";

            byte[] datos = mensajeIp.getBytes();
            DatagramPacket paquete = new DatagramPacket(datos, datos.length, serverIP, port);
            socket.send(paquete);
            System.out.println("Mensaje enviado preguntando por " + mensajeIp);

            byte[] buffer = new byte[4096];

            DatagramPacket devulto = new DatagramPacket(buffer, buffer.length);

            socket.receive(devulto);

            String mensaje = new String(devulto.getData(), 0, devulto.getLength());
            System.out.println("Paquete recibido: " + mensaje);

        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
