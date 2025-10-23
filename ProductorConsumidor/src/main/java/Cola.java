import java.util.ArrayList;

public class Cola {
    private ArrayList<Integer> cola;

    public Cola() {
        cola = new ArrayList<>();
    }
    public void insertar(int e) {
        cola.add(e);
    }

    public Integer obtener() {
         return cola.removeFirst();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public void imprimir() {
        System.out.println(cola.toString());
    }

    public boolean estaLlena(){
        return cola.size() == 10;
    }
}
