package com.pmdm;

public class Rectangulo extends Figura{

    double base;
    double altura;


  public Rectangulo(String nombre, double base, double altura) {

      super(nombre);
      this.base = base;
      this.altura = altura;
  }



    @Override
    public double calcularArea() {
        this.base = base;
        this.altura = altura;
        return base*altura;
    }

    @Override
    public double calcularPerimetro() {
        this.base = base;
        this.altura = altura;
        return (base*2)+(altura*2);    }
}
