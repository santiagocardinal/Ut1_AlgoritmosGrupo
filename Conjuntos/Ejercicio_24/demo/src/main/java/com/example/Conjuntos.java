package ucu.edu.aed.tda;

import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.function.Predicate;
import ucu.edu.aed.tda.TDALista;

public class Conjuntos<T> extends ListaEnlazada<T> implements TDAConjunto<T> 
{
    //los 3 metodos siguientes son metodos propias de las pilas
    @Override
    public TDAConjunto<T> union(TDAConjunto<T> otro) 
    {
        int i = 0;
        int j = 0;
        Conjuntos<T> lista3 = new Conjuntos<>();

        while (i < this.tamaño() && j < otro.tamaño()) 
        {
            T elem1 = this.obtener(i);
            T elem2 = otro.obtener(j);
            int comparacion = ((Comparable<T>) elem1).compareTo(elem2);

            if (comparacion == 0) 
            { 
                lista3.agregar(elem1);
                i++;
                j++;
            } 
            else if (comparacion < 0) 
            { 
                lista3.agregar(elem1);
                i++;
            } 
            else 
            { 
                lista3.agregar(elem2);
                j++;
            }
        }

        while (i < this.tamaño()) {
            lista3.agregar(this.obtener(i));
            i++;
        }
        while (j < otro.tamaño()) 
        {
            lista3.agregar(otro.obtener(j));
            j++;
        }

        return lista3;
    }

    @Override
    public TDAConjunto<T> interseccion(TDAConjunto<T> otro)
    {
        int i = 0;
        int j = 0;
        Conjuntos<T> lista3 = new Conjuntos<>();

        while (i < this.tamaño() && j < otro.tamaño()) 
        {
            T elem1 = this.obtener(i);
            T elem2 = otro.obtener(j);
            int comparacion = ((Comparable<T>) elem1).compareTo(elem2);

            if (comparacion == 0) 
            { 
                lista3.agregar(elem1);
                i++;
                j++;
            } 
            else if (comparacion < 0) 
            { 
                i++;
            } 
            else 
            { 
                j++;
            }
        }
        return lista3;
    }
}