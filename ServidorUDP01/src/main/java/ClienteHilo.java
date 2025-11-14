import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

public class ClienteHilo implements Runnable{


    private int id;

    ClienteHilo(int Id){
        this.id=Id;
    }

    @Override
    public void run() {
        try {

            DatagramSocket socket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            int port = 5000;

//            System.out.println("Introduzca mensaje cliente1:");
//            Scanner sc = new Scanner(System.in);
//            String msg = sc.nextLine();


            Random random = new Random();
            String abcedario = "abcdefghijklmnopqrstwyz";
            int count = random.nextInt(20)+5;
            StringBuilder sb = new StringBuilder();
            while(count>0){
                sb.append(abcedario.charAt(random.nextInt(abcedario.length())));
                count--;
            }
            String msg = sb.toString();
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), inetAddress, port);
            socket.connect(inetAddress, port);
            socket.send(packet); //Encio de informacion al servidor

            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);
            String receivedMsg = new String(receivePacket.getData(),0,receivePacket.getLength());
            System.out.println("["+id+"]"+msg);


        }catch (Exception e){

        }
    }
}
