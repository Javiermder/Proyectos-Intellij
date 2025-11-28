import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    private final static String COD_TEXTO = "UTF-8";
    private final static int PUERTO_FIJO = 5000;

    // Carpeta donde el servidor buscará los archivos (puedes cambiarla)
    private final static String CARPETA_ARCHIVOS = "archivos_servidor";

    public static void main(String[] args) {
        // Crear la carpeta si no existe
        new File(CARPETA_ARCHIVOS).mkdirs();

        try (ServerSocket socketServidor = new ServerSocket(PUERTO_FIJO)) {
            System.out.printf("Servidor de transferencia de archivos iniciado en puerto %d.\n", PUERTO_FIJO);
            System.out.println("Esperando conexiones de clientes...");

            while (true) {
                try (
                        Socket socketCliente = socketServidor.accept();

                        BufferedReader brDelCliente = new BufferedReader(
                                new InputStreamReader(socketCliente.getInputStream(), COD_TEXTO)
                        );

                        PrintWriter pwAlCliente = new PrintWriter(
                                new OutputStreamWriter(socketCliente.getOutputStream(), COD_TEXTO), true
                        )
                ) {
                    System.out.printf("Cliente conectado desde %s:%d\n",
                            socketCliente.getInetAddress().getHostAddress(),
                            socketCliente.getPort());

                    // 1. Leer el nombre del archivo solicitado
                    String nombreArchivo = brDelCliente.readLine();

                    if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
                        pwAlCliente.println("ERROR: Nombre de archivo vacío.");
                        System.out.println("Cliente envió nombre vacío.");
                        continue;
                    }

                    System.out.println("Cliente solicita el archivo: " + nombreArchivo);
                    File archivo = new File(CARPETA_ARCHIVOS, nombreArchivo);

                    // 2. Verificar si el archivo existe
                    if (!archivo.exists() || !archivo.isFile()) {
                        pwAlCliente.println("ERROR: Archivo no encontrado.");
                        System.out.println("Archivo no encontrado: " + archivo.getAbsolutePath());
                        continue;
                    }

                    // 3. Enviar el contenido línea a línea
                    System.out.println("Enviando archivo: " + archivo.getName() + " (" + archivo.length() + " bytes)");

                    try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = lectorArchivo.readLine()) != null) {
                            pwAlCliente.println(linea);  // Envía cada línea con salto de línea
                        }
                    }

                    // 4. Al terminar el archivo, enviamos una línea especial para indicar fin
                    pwAlCliente.println("FIN_ARCHIVO");
                    System.out.println("Archivo enviado completamente.");

                } catch (IOException ex) {
                    System.err.println("Error al atender al cliente: " + ex.getMessage());
                }
            }

        } catch (IOException ex) {
            System.err.println("No se pudo iniciar el servidor en el puerto " + PUERTO_FIJO);
            ex.printStackTrace();
        }
    }
}