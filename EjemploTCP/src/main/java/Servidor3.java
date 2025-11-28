import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor3 {

    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("=== SERVIDOR CALCULADORA TCP ===");
        System.out.println("Escuchando en puerto " + PUERTO + "...\n");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress());

                // Un hilo por cada cliente → conexión persistente y multicliente
                Thread hiloCliente = new Thread(new ManejadorCliente(socketCliente));
                hiloCliente.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

// Clase que atiende a un cliente durante toda su sesión
class ManejadorCliente implements Runnable {
    private final Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8")
                )
        ) {
            out.println("¡Bienvenido a la Calculadora TCP! Usa: SUMA#5#3 | RESTA#10#4 | MULTIPLICACION#6#7 | DIVISION#15#3 | SALIR");

            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                System.out.println("Recibido de " + socket.getInetAddress() + ": " + mensaje);

                if (mensaje.trim().equalsIgnoreCase("SALIR")) {
                    out.println("¡Adiós! Conexión cerrada.");
                    break;
                }

                String resultado = procesarOperacion(mensaje.trim());
                out.println("Resultado: " + resultado);
            }

        } catch (IOException e) {
            System.out.println("Cliente desconectado bruscamente.");
        } finally {
            try { socket.close(); }
            catch (IOException e) {}
            System.out.println("Cliente " + socket.getInetAddress() + " desconectado.\n");
        }
    }

    private String procesarOperacion(String comando) {
        String[] partes = comando.split("#");

        if (partes.length != 3) {
            return "ERROR: Formato inválido. Usa: OPERACION#A#B";
        }

        String operacion = partes[0].toUpperCase();
        double a, b;

        try {
            a = Double.parseDouble(partes[1]);
            b = Double.parseDouble(partes[2]);
        } catch (NumberFormatException e) {
            return "ERROR: Los números deben ser válidos";
        }

        return switch (operacion) {
            case "SUMA"           -> String.valueOf(a + b);
            case "RESTA"          -> String.valueOf(a - b);
            case "MULTIPLICACION" -> String.valueOf(a * b);
            case "DIVISION" -> {
                if (b == 0) yield "ERROR: División por cero";
                else yield String.format("%.4f", a / b);
            }
            default -> "ERROR: Operación no soportada. Usa SUMA/RESTA/MULTIPLICACION/DIVISION";
        };
    }
}