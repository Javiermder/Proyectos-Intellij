import java.util.ArrayList;

public class ColaImpresion {

    private final int CAPACIDAD = 5;
    private ArrayList<String> cola;

    public ColaImpresion() {
        cola = new ArrayList<>();
    }


    public void insertar(String elemento) {
        cola.add(elemento);
    }

    public String sacar() {
        return cola.removeFirst();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public boolean estaLlena() {
        return cola.size() == CAPACIDAD;
    }

    @Override
    public String toString() {
        return cola.toString();
    }

}
