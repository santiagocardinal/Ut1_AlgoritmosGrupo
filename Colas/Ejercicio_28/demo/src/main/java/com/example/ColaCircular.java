package com.example;

import java.util.NoSuchElementException;

public class ColaCircular<T> implements TDACola<T>
{
    private Object[] vector;
    private int frente;
    private int fin;
    private int capacidad;

    public ColaCircular(int capacidad)
    {
        this.capacidad = capacidad + 1; 
        this.vector = new Object[this.capacidad];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poneEnCola(T dato)
    {
        if ((fin + 1) % capacidad == frente)
        {
            throw new IllegalStateException("la cola esta llena");
        }
        vector[fin] = dato;
        fin = (fin + 1) % capacidad;
        return true;
    }
 
    public T quitaDeCola()
    {
        if (frente == fin)
        {
            throw new NoSuchElementException("la cola esta vacia");
        }
        T dato = (T) vector[frente];
        vector[frente] = null; 
        frente = (frente + 1) % capacidad;
        return dato;
    }
 
    public T frente()
    {
        if (esVacio())
        {
            throw new NoSuchElementException("la cola esta vacia");
        }
        return (T) vector[frente];
    }
 
    public boolean esVacio()
    {
        return frente == fin;
    }
}