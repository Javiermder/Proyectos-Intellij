package com.pmdm;

public class Triangulo extends Figura{
    double base;
    double altura;
    public Triangulo(String nombre, double base, double altura){
        super(nombre);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea(){
        return (base*altura)/2;
    }
    @Override
    public double calcularPerimetro(){
        return base*3;
    }
}
