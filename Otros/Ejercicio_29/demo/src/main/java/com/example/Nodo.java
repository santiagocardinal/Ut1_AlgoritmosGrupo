package com.example;

public class Nodo<T>
{
    T dato;
    Nodo<T> siguiente;

    public Nodo(T dato, Nodo<T> siguiente) 
    {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    public T getDato()
    {
        return dato;
    }

    public Nodo<T> getSiguiente()
    {
        return siguiente;
    }

    public void setDato(T dato)
    {
        this.dato = dato;
    }

    public void setSiguiente(Nodo<T> siguiente)
    {
        this.siguiente = siguiente;
    }
}