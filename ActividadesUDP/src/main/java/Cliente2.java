import java.net.*;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("192.168.0.82"); // IP del servidor
            int serverPort = 5000;

            // Enviar mensaje inicial al servidor
            String initMessage = "HELLO_SERVER";
            byte[] buffer = initMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverIP, serverPort);
            socket.send(packet);
            System.out.println("Mensaje inicial enviado al servidor.");

            // Escuchar respuestas
            Thread receiverThread = new Thread(() -> {
                byte[] recvBuffer = new byte[256];
                while (true) {
                    try {
                        DatagramPacket recvPacket = new DatagramPacket(recvBuffer, recvBuffer.length);
                        socket.receive(recvPacket);
                        String frame = new String(recvPacket.getData(), 0, recvPacket.getLength());
                        System.out.println("Recibido: " + frame);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            receiverThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}