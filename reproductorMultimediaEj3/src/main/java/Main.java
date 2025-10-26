public class Main {
    public static void main(String[] args) {

        BufferStream<String> bufferr = new BufferStream<>();

        Thread[] descargador = new Thread[1];
        Thread[] reproductor = new Thread[1];
        descargador[0] = new Thread(new Descargador(bufferr));
        reproductor[0] = new Thread(new Reproductor(bufferr));
        descargador[0].start();
        reproductor[0].start();


    }
}
