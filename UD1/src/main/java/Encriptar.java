import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptar {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        if (args.length < 1) {
            System.out.println("Argumentos incorrectos");
        }
        String texto = args[0];

        String encriptado = sha256(texto);
        System.out.println(encriptado);
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
