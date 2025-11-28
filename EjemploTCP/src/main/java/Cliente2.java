import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    private final static String COD_TEXTO = "UTF-8";
    private final static String HOST = "localhost";
    private final static int PUERTO = 5000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter pw = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream(), COD_TEXTO), true);
             BufferedReader br = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), COD_TEXTO))
        ) {

            System.out.print("Introduce el nombre del archivo que quieres descargar: ");
            String nombreArchivo = sc.nextLine();

            // Enviar nombre del archivo
            pw.println(nombreArchivo);

            // Recibir respuesta
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("ERROR:")) {
                    System.out.println("Servidor: " + linea);
                    break;
                }
                if ("FIN_ARCHIVO".equals(linea)) {
                    System.out.println("\nArchivo recibido completamente.");
                    break;
                }
                System.out.println(linea);
            }

        } catch (IOException ex) {
            System.err.println("Error de conexión: " + ex.getMessage());
        }
    }
}