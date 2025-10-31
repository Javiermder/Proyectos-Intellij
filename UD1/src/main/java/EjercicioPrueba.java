import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class EjercicioPrueba {

    public static void main(String[] args) {


        //PARA IMPRIMIR VARIABLES DE ENTORNO HAY QUE PONER CMD /C
        ProcessBuilder pb = new ProcessBuilder("cmd","/c","echo", "%DATE%");
        pb.redirectErrorStream(true);

        try {
            Process p = pb.start();
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;

            while ((linea = br.readLine()) != null) System.out.println(linea);


            br.close();
            isr.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
