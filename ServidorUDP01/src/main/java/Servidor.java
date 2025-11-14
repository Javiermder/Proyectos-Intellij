import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Servidor {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(PUERTO);

            System.out.println("Conectadoo");

            while(true){
                System.out.printf("Servidor escuchando...");

                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, 1024);

                socket.receive(packet);
                Thread t = new Thread(() -> {

                    String msg = new String(packet.getData(), 0, packet.getLength());

                    //Preparar la respuesta al cliente
                    InetAddress ip = packet.getAddress();
                    int port = packet.getPort();
                    System.out.println(ip+":"+port);

                    int longitud = msg.length();
                    String s = String.valueOf(longitud);

                    DatagramPacket sendPacket = new DatagramPacket(s.getBytes(), s.length(), ip, port);

                    Random rand = new Random();
                    try {

                        Thread.sleep(rand.nextInt(1000));
                    }catch (Exception e){}

                    try {
                        socket.send(sendPacket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
                t.start();



            }
        }catch (Exception e){

        }}
}


