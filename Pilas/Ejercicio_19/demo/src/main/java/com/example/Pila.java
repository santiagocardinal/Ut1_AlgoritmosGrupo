package com.example;

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.function.Predicate;

public class Pila<T> implements TDAPila<T> 
{
    ListaEnlazada<T> lista;

    public Pila() 
    {
        lista = new ListaEnlazada<T>();
    }

    //los 3 metodos siguientes son metodos propias de las pilas
    public void mete(T dato) 
    {
        lista.agregar(0, dato);  
    }

    public T saca() 
    {
        if (lista.esVacio()) 
        {
            throw new NoSuchElementException("la pila esta vacia");
        }
        return lista.remover(0);
    }

    public T tope() 
    {
        if (lista.esVacio()) 
        {
            throw new NoSuchElementException("la pila esta vacia");
        }
        return lista.obtener(0);
    }

    //estos metodos son los metodos que se heredaron de TDALista

    public void agregar(T elem) 
    { 
        lista.agregar(elem); 
    }

    public void agregar(int index, T elem) 
    { 
        lista.agregar(index, elem); 
    }

    public T obtener(int index) 
    { 
        return lista.obtener(index); 
    }

    public T remover(int index) 
    { 
        return lista.remover(index); 
    }

    public boolean remover(T elem) 
    { 
        return lista.remover(elem); 
    }
    public boolean contiene(T elem) 
    { 
        return lista.contiene(elem); 
    }

    public int indiceDe(T elem) 
    { 
        return lista.indiceDe(elem); 
    }

    public T buscar(Predicate<T> criterio) 
    { 
        return lista.buscar(criterio); 
    }

    public TDALista<T> ordenar(Comparator<T> comparator) 
    { 
        return lista.ordenar(comparator); 
    }

    public int tamaño() 
    { 
        return lista.tamaño(); 
    }

    public boolean esVacio() 
    { 
        return lista.esVacio(); 
    }

    public void vaciar() 
    { 
        lista.vaciar(); 
    }
}