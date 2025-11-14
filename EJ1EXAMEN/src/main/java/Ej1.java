import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Ej1 {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("tasklist", "/fo", "csv", "/nh");
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();

            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);
            int contador = 0;
            String linea;
            int mayor = 0;
            String memoria;
            int memoria2;
            String guardarProceso = "";
            while ((linea = br.readLine()) != null) {
                memoria = linea.split(",")[4];
                contador++;

                memoria = memoria.trim();

                memoria = memoria.replace(". ", "").replace("K", "").replace("B","").replace(",", "")
                        .replace("''","").trim();
                memoria = memoria.substring(1, memoria.length() - 2);

                try {
                    memoria2 = Integer.parseInt(memoria);

                    if(memoria2 > mayor){
                        guardarProceso = linea;
                        mayor = memoria2;
                    }
                }catch (NumberFormatException e){

                }

                //System.out.println(linea);

            }


            System.out.println("Total de procesos: " + contador);
            System.out.println("Proceso con mayor consumo de memoria");
            System.out.println(guardarProceso.split(",")[0] + "      PID:"+guardarProceso.split(",")[1] + "     Memoria:"+ mayor);
            br.close();
            isr.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al ejecutar el comando: " + e.getMessage());
        }
    }

}
