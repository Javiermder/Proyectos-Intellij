import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {

            DatagramSocket socket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            System.out.print("Nombre del fichero: ");
            String fichero = sc.nextLine();
            File file = new File(fichero);

            if(!file.exists()){
                System.out.println("Fichero no encontrado");
                return;
            }

            //el fichero existe
            byte[] nombre = file.getName().getBytes();
            DatagramPacket paqueteNombre = new DatagramPacket(nombre, nombre.length, inetAddress, 5000);
            socket.send(paqueteNombre);

            FileInputStream fis = new FileInputStream(file);
            int bytesLeidos = 0;
            int count = 0;
            byte[] buffer = new byte[4096];
            while((bytesLeidos = fis.read(buffer))!=-1 ){

                DatagramPacket dp = new DatagramPacket(buffer, bytesLeidos, inetAddress,5000);
                socket.send(dp);

                System.out.println("Paquete " + count++ + " enviado");
            }
            fis.close();

            byte[] fin = "FIN".getBytes();

            DatagramPacket dp = new DatagramPacket(fin, fin.length, inetAddress, 5000);
            socket.send(dp);

            System.out.println("Fichero enviado");

        }catch (Exception e){

        }
    }
}
