public class Contador {

    private long contador;
    public Contador(long contador) {
        this.contador = contador;
    }

    synchronized public long getContador() {
        return contador;
    }
    synchronized public void setContador(long contador) {
        this.contador = contador;
    }

    synchronized  public void incrementar(){
        contador++;
    }

}
