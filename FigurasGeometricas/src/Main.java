import com.pmdm.Circulo;
import com.pmdm.Rectangulo;
import com.pmdm.Triangulo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Circulo circulo = new Circulo("Circulo", 5);
    System.out.println(circulo.calcularArea());
    System.out.println(circulo.calcularPerimetro());

    Rectangulo rectangulo = new Rectangulo("Rectangulo", 4, 6);
    System.out.println(rectangulo.calcularArea());
    System.out.println(rectangulo.calcularPerimetro());

    Triangulo triangulo = new Triangulo("Triangulo", 4, 6);
    System.out.println(triangulo.calcularArea());
    System.out.println(triangulo.calcularPerimetro());

    }
}