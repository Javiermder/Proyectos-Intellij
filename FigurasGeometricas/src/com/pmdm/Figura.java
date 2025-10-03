package com.pmdm;

public abstract class Figura {
    String Nombre;

    protected Figura(String Nombre){
        this.Nombre = Nombre;
    }

    public abstract double calcularArea();
    public abstract double calcularPerimetro();
}
