package com.example;

public class Cabina
{
    private final int capacidadMaxima;
    private int pasajerosActuales;

    public Cabina(int capacidadMaxima) 
    {
        this.capacidadMaxima = capacidadMaxima;   
    }

    public void subirPasajeros(int cantidad) 
    {
        int lugaresLibres = capacidadMaxima - pasajerosActuales;
        int aSubir = Math.min(cantidad, lugaresLibres); 
        pasajerosActuales += aSubir;
    }

    public void bajarPasajeros() 
    {
        pasajerosActuales = 0;
    }

    public int pasajerosActuales()
    {
        return pasajerosActuales;
    }
}

    
