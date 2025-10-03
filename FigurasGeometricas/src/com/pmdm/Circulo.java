package com.pmdm;

public class Circulo extends Figura{
    double radio;

    public Circulo(String Nombre, double radio){
        super(Nombre);
        this.radio = radio;
    }
    @Override
    public double calcularArea(){
        return 3.14*radio*radio;
    }
    @Override
  public double calcularPerimetro(){
    return 2*3.14*radio;
  }
}
