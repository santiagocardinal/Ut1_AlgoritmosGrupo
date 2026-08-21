package com.example;
import org.junit.Test;

import com.example.Nodo;
import com.example.libro;

import static org.junit.Assert.*;
public class TDANodoTest 
{

    @Test
    public void testGetDato() 
    {
        Nodo<Integer> nodo = new Nodo<>(5);
        assertEquals((Integer)5, nodo.getDato()); // el dato guardado debe ser 5
    }

    @Test
    public void testSiguienteInicialmenteNull() 
    {
        Nodo<String> nodo = new Nodo<>("Hola");
        assertNull(nodo.getSiguiente()); // el siguiente debe ser null al crear el nodo
    }

    @Test
    public void testSetSiguiente() 
    {
        Nodo<String> nodo1 = new Nodo<>("Hola");
        Nodo<String> nodo2 = new Nodo<>("Mundo");

        nodo1.setSiguiente(nodo2); // enlazamos nodo1 con nodo2

        assertEquals(nodo2, nodo1.getSiguiente()); // el siguiente de nodo1 debe ser nodo2
    }

    @Test
    public void testGetSiguiente() 
    {
        Nodo<Integer> nodo1 = new Nodo<>(1);
        Nodo<Integer> nodo2 = new Nodo<>(2);

        nodo1.setSiguiente(nodo2);

        assertEquals((Integer)2, nodo1.getSiguiente().getDato()); // el dato del siguiente debe ser 2
    }

    @Test
    public void testNodoConLibro() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        Nodo<libro> nodo = new Nodo<>(l);

        assertEquals("001", nodo.getDato().getCodigoIdentificacion()); // verifica el código
        assertEquals("Java", nodo.getDato().getTitulo());               // verifica el título
        assertEquals((Double)29.99, nodo.getDato().getPrecio());                // verifica el precio
        assertEquals(Integer.valueOf(5), Integer.valueOf(nodo.getDato().getCantidadEjemplares()));        // verifica el stock
    }

    @Test
    public void testNodoConDouble() 
    {
        Nodo<Double> nodo = new Nodo<>(3.14);
        assertEquals((Double)3.14, nodo.getDato()); // el dato guardado debe ser 3.14
    }

    @Test
    public void testCadenaDeNodos() 
    {
        Nodo<Integer> nodo1 = new Nodo<>(1);
        Nodo<Integer> nodo2 = new Nodo<>(2);
        Nodo<Integer> nodo3 = new Nodo<>(3);

        nodo1.setSiguiente(nodo2); // 1 -> 2
        nodo2.setSiguiente(nodo3); // 2 -> 3

        // verificamos que la cadena está bien enlazada
        assertEquals((Integer)2, nodo1.getSiguiente().getDato());                 // 1 -> 2
        assertEquals((Integer)3, nodo1.getSiguiente().getSiguiente().getDato());  // 1 -> 2 -> 3
        assertNull(nodo1.getSiguiente().getSiguiente().getSiguiente());  // 3 -> null
    }
}