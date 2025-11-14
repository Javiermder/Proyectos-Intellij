import java.net.*;
import java.util.Scanner;

public class Cliente1 {

    // Variables compartidas entre hilos
    static int x = 100;
    static int y = 100;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("192.168.0.105");
            int port = 5000;
            Scanner sc = new Scanner(System.in);
            String playerId = "PLAYER1";

            new Thread(() -> {
                while (true) {
                    System.out.print("Tecla (WASD): ");
                    String input = sc.nextLine().toLowerCase();

                    switch (input) {
                        case "w": y -= 5; break;
                        case "s": y += 5; break;
                        case "a": x -= 5; break;
                        case "d": x += 5; break;
                        default:
                            System.out.println("Tecla no válida. Usa solo W A S D.");
                    }
                }
            }).start();

            new Thread(() -> {
                while (true) {
                    try {
                        String mensaje = playerId + "#" + x + "#" + y;
                        byte[] datos = mensaje.getBytes();
                        DatagramPacket paquete = new DatagramPacket(datos, datos.length, serverIP, port);
                        socket.send(paquete);
                        System.out.println("Posición enviada: " + x + ", " + y);
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println("Error al enviar: " + e.getMessage());
                    }
                }
            }).start();



        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}