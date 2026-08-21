package com.example;

import java.util.Comparator;
import java.util.function.Predicate;

public class ListaEnlazada<T> implements TDALista<T> 
{
    Nodo<T> primero;
    int cantidad;

    public void agregar(T elem) 
    {
        agregar(cantidad, elem);
    }

    public void agregar(int index, T elem) 
    {
        if (index < 0 || index > cantidad) 
        {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }

        Nodo<T> nuevoNodo = new Nodo<T>(elem, null);

        if (index == 0) 
        {
            nuevoNodo.siguiente = primero;
            primero = nuevoNodo;
        } 
        else 
        {
            Nodo<T> anterior = primero;
            for (int i = 0; i < index - 1; i++) 
            {
                anterior = anterior.siguiente;
            }
            nuevoNodo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevoNodo;
        }
        cantidad++;
    }

    public T obtener(int index) 
    {
        if (index < 0 || index >= cantidad) 
        {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }
        Nodo<T> actual = primero;
        for (int i = 0; i < index; i++) 
        {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public T remover(int index) 
    {
        if (index < 0 || index >= cantidad) 
        {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }

        T datoRemovido;

        if (index == 0) 
        {
            datoRemovido = primero.dato;
            primero = primero.siguiente;
        } 
        else 
        {
            Nodo<T> anterior = primero;
            for (int i = 0; i < index - 1; i++) 
            {
                anterior = anterior.siguiente;
            }
            Nodo<T> nodoARemover = anterior.siguiente;
            datoRemovido = nodoARemover.dato;
            anterior.siguiente = nodoARemover.siguiente;
        }
        cantidad--;
        return datoRemovido;
    }

    public boolean remover(T elem) 
    {
        int index = indiceDe(elem);
        if (index == -1) 
        {
            return false;
        }
        remover(index);
        return true;
    }

    public boolean contiene(T elem) 
    {
        return indiceDe(elem) != -1;
    }

    public int indiceDe(T elem) 
    {
        Nodo<T> actual = primero;
        int index = 0;
        while (actual != null) 
        {
            if (actual.dato.equals(elem)) 
            {
                return index;
            }
            actual = actual.siguiente;
            index++;
        }
        return -1;
    }

    public T buscar(Predicate<T> criterio) 
    {
        Nodo<T> actual = primero;
        while (actual != null) 
        {
            if (criterio.test(actual.dato)) 
            {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public TDALista<T> ordenar(Comparator<T> comparator) 
    {
        ListaEnlazada<T> resultado = new ListaEnlazada<T>();
        Nodo<T> actual = primero;
        while (actual != null) 
        {
            resultado.agregar(actual.dato);
            actual = actual.siguiente;
        }

        Nodo<T> i = resultado.primero;
        while (i != null) 
        {
            Nodo<T> menor = i;
            Nodo<T> j = i.siguiente;
            while (j != null) 
            {
                if (comparator.compare(j.dato, menor.dato) < 0) 
                {
                    menor = j;
                }
                j = j.siguiente;
            }
            T aux = i.dato;
            i.dato = menor.dato;
            menor.dato = aux;
            i = i.siguiente;
        }
        return resultado;
    }

    public int tamaño() 
    {
        return cantidad;
    }

    public boolean esVacio() 
    {
        return primero == null;
    }

    public void vaciar() 
    {
        primero = null;
        cantidad = 0;
    }
}