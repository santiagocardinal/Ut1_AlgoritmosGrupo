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
}