import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args) {

        byte[] buffer = new byte[4096];

        try {

            DatagramSocket socket = new DatagramSocket(5000);
            System.out.println("Servidor iniciado ");

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String nombreArchivo = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Nombre del archivo: " + nombreArchivo);

            FileOutputStream fos = new FileOutputStream("Recibido "+ nombreArchivo);
            while(true){
                DatagramPacket paqueteDatos = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteDatos);

                String recibido = new String(buffer, 0, paqueteDatos.getLength());
                if(recibido.equals("FIN")){
                    System.out.println("Archivo completo");
                    break;
                }
            }

        }catch (Exception e){
            System.out.println("Error");
        }


    }
}
