import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el texto que quieras encriptar: ");
        String texto = sc.nextLine();

        //Creamos el processbuilder
        ProcessBuilder pb = new ProcessBuilder("java", "C:\\Users\\nocturno\\IdeaProjects\\UD1\\src\\main\\java\\Encriptar.java", texto);
        pb.redirectErrorStream(true);

        try {
            Process p = pb.start(); //ejecutamos el comando y hay que recoger la salida
            if (args[2].equals("-f")) {
                pb.redirectOutput(new File("fichero.txt"));
            }
            //Vamos a analizar la salida del comando
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            System.out.println(br.readLine());



            br.close();
            isr.close();
            is.close();

        }catch (Exception e) {
            System.out.println("Error al ejecutar el comando");
        }
    }

}
