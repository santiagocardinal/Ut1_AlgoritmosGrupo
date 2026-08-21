package com.example;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ColaEnlazada<T> implements TDACola<T> 
{
    TDALista<T> lista = new ListaEnlazada<>();

    public T frente()
    {
        if (lista.esVacio()) 
        {
            throw new NoSuchElementException("la cola esta vacia");
        }
        return lista.obtener(0);
    }

    public boolean poneEnCola(T dato)
    {
        lista.agregar(dato);
        return true;
    }

    public T quitaDeCola()
    {
        if (lista.esVacio()) 
        {
            throw new NoSuchElementException("la pila esta vacia");
        }
        return lista.remover(0);

    }

    public boolean esVacio() 
    { 
        return lista.esVacio(); 
    }
}



    
