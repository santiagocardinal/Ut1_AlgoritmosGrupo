package com.example;

import com.example.Cabina;

public class RuedaDeLaFortuna
{
    private Nodo<Cabina> actual;

    public RuedaDeLaFortuna(int num, int capacidad) 
    {
        if (num <= 0) 
        {
            throw new IllegalArgumentException("La rueda necesita al menos una cabina");
        }

        Nodo<Cabina> primero = new Nodo<>(new Cabina(capacidad), null);
        Nodo<Cabina> anterior = primero;

        for (int i = 1; i < num; i++) 
        {
            Nodo<Cabina> nuevo = new Nodo<>(new Cabina(capacidad), null);
            anterior.siguiente = nuevo;
            anterior = nuevo;
        }
        anterior.siguiente = primero; 
        actual = primero; 
    }

    public int contadorCabinas()
    {
        if(actual == null) 
        {
            return 0;
        }

        int contador = 0;
        Nodo<Cabina> inicio = actual;
        Nodo<Cabina> nodoRecorriendo = actual;

        do
        {
            contador++;
            nodoRecorriendo = nodoRecorriendo.siguiente;
        }
        while (nodoRecorriendo != inicio);
        return contador;
    }

    public void avanzar()
    {
        if(actual != null)
        {
            actual = actual.siguiente;
        }
    }
}

    
