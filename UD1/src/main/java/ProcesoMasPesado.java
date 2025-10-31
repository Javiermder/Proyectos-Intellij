import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class ProcesoMasPesado {
    public static void main(String[] args) {

        String procesoMasPesado = "";
        int memoriaMaxima = 1500000;

        ProcessBuilder pb = new ProcessBuilder("tasklist");
        pb.redirectErrorStream(true);

        try {
            Process p = pb.start();
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.forName("cp850"));
            BufferedReader br = new BufferedReader(isr);

            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                // Saltamos la cabecera
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // Dividimos por espacios múltiples
                String[] partes = linea.trim().split("\\s+");

                if (partes.length < 5) continue;

                String nombreProceso = partes[0];
                String memoriaTexto = partes[partes.length - 2]; // penúltimo campo suele ser memoria

                // Limpiamos la memoria: "123.456 K" → "123456"
                memoriaTexto = memoriaTexto.replace(".", "").replace("K", "").replace(",", "").trim();

                try {
                    int memoria = Integer.parseInt(memoriaTexto);
                    if (memoria > memoriaMaxima && !nombreProceso.equalsIgnoreCase("System") && !nombreProceso.equalsIgnoreCase("Idle")) {
                        memoriaMaxima = memoria;
                        procesoMasPesado = nombreProceso;
                    }
                } catch (NumberFormatException e) {
                    // Ignoramos si no se puede convertir
                }
            }

            br.close();
            isr.close();
            is.close();

        } catch (Exception e) {
            System.out.println("Error al ejecutar tasklist: " + e.getMessage());
            return;
        }

        if (!procesoMasPesado.isEmpty()) {
            System.out.println("🧠 Proceso más pesado: " + procesoMasPesado + " con " + memoriaMaxima + " KB");

            // Ejecutamos taskkill
            ProcessBuilder pbKill = new ProcessBuilder("taskkill", "/IM", procesoMasPesado, "/F");
            pbKill.redirectErrorStream(true);

            try {
                Process pKill = pbKill.start();
                BufferedReader brKill = new BufferedReader(new InputStreamReader(pKill.getInputStream(), Charset.forName("cp850")));

                String linea;
                while ((linea = brKill.readLine()) != null) {
                    System.out.println(linea);
                }

                brKill.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el proceso: " + e.getMessage());
            }

        } else {
            System.out.println("No se encontró ningún proceso válido para cerrar.");
        }
    }

}
