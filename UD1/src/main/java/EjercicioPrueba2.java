import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class EjercicioPrueba2 {
    public static void main(String[] args) {

        String[] dominios = {"www.google.com","www.microsoft.com","www.noexiste123.com"};

        for(String dominio : dominios){
            ProcessBuilder pb = new ProcessBuilder("ping", dominio);

            pb.redirectErrorStream(true);

            try {
                Process p = pb.start();
                InputStream is = p.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
                BufferedReader br = new BufferedReader(isr);
                String linea;
                boolean valido = false;
                while ((linea = br.readLine()) != null){
                    if (linea.contains("Respuesta desde")){
                        valido = true;
                    }
                }

                br.close();
                isr.close();
                is.close();

                if (valido){
                    System.out.println("✅ El dominio " + dominio + " está accesible.");
                }else {
                    System.out.println("❌ El dominio " + dominio + " NO está accesible.");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
