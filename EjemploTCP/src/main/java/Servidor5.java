import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor5 {

    private final static String COD_TEXTO = "UTF-8";
    private final static int PUERTO_FIJO = 5000;

    public static void main(String[] args) {

        Random aleatorio = new Random();
        int numeroAdivinar = aleatorio.nextInt(100);

        try (ServerSocket socketServidor = new ServerSocket(PUERTO_FIJO)) {

            System.out.println("Servidor Iniciado");
            while(true){
                Socket socketCliente = socketServidor.accept();
                new Thread(() -> {
                    try (
                            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream(), COD_TEXTO));
                            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
                    ) {
                        salida.println("introduce un número para empezar a adivinar");

                        String mensaje;
                        while ((mensaje = entrada.readLine()) != null) {
                            int numeroCliente = Integer.parseInt(mensaje);
                            if (numeroCliente == numeroAdivinar) {
                                salida.println("has adivinado el número");
                                break;
                            } else if (numeroCliente < numeroAdivinar) {
                                salida.println("El número a adivinar es más grande");
                            } else {
                                salida.println("El número a adivinar es más chico");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error con cliente: " + e.getMessage());
                    }
                }).start();
            }
        }catch (Exception e){
            System.out.println("Servidor no encontrado");
        }

        }
}
