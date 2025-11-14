import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

        public static void main(String[] args) {

            try {

                DatagramSocket socket = new DatagramSocket();
                InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                int port = 5000;

                System.out.println("Introduzca mensaje cliente1:");
                Scanner sc = new Scanner(System.in);

                String msg = sc.nextLine();

                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), inetAddress, port);
                socket.connect(inetAddress, port);
                socket.send(packet); //Encio de informacion al servidor

                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);
                String receivedMsg = new String(receivePacket.getData(),0,receivePacket.getLength());
                System.out.println("Mensaje del servidor "+msg);


            }catch (Exception e){

            }

        }


}
