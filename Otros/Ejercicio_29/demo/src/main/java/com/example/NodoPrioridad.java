package com.example;

public class NodoPrioridad <T> extends Nodo<T>
{

    private int prioridad;

    public NodoPrioridad (T dato, int prioridad)
    {
        super(dato, null);
        this.prioridad = prioridad;
    }

    public int getPrioridad()
    {
        return this.prioridad;
    }

    public void setPrioridad(int prioridad)
    {
        this.prioridad = prioridad;
    }
}
    
  

