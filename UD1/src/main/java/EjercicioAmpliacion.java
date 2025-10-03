import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EjercicioAmpliacion {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String cadena = args[0];
        String opcion = args[1];
        String fichero = args[2];

        if (cadena == null ||cadena.trim().equals("")) {
            System.out.println("La cadena es obligatorio");
            return;
        }

        if (opcion != null && opcion.trim().equals("-f")) {
            //Lo proceso a fichero
            if(fichero != null ) {
                ProcessBuilder pb = new ProcessBuilder(
                        "java", "C:\\Users\\nocturno\\IdeaProjects\\UD1\\src\\main\\java\\Encriptar.java", cadena,"-f" , fichero);
                pb.redirectErrorStream(true);
                pb.redirectOutput(new File(fichero));


                try {
                    Process p = pb.start(); //ejecutamos el comando y hay que recoger la salida

                    //Vamos a analizar la salida del comando
                    InputStream is = p.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
                    BufferedReader br = new BufferedReader(isr);



                    br.close();
                    isr.close();
                    is.close();

                }catch (Exception e) {
                    System.out.println("Error al ejecutar el comando");
                }
            }

        }
    }
    private static String sha256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
