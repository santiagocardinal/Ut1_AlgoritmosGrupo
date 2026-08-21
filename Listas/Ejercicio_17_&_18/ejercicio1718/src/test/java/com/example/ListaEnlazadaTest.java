package com.example;
import org.junit.Test;

import com.example.ListaEnlazada;
import com.example.libro;

import static org.junit.Assert.*;
public class ListaEnlazadaTest 
{

    @Test
    public void testListaVaciaAlCrear() 
    {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertTrue(lista.esVacio()); // la lista debe estar vacía al crearla
    }

    @Test
    public void testAgregarUnElemento() 
    {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(5);
        assertFalse(lista.esVacio()); // la lista no debe estar vacía después de agregar
        assertEquals((Integer) 1, (Integer) lista.tamano()); // el tamano debe ser 1 después de agregar un elemento
    }

    @Test
    public void testAgregarDevuelveTrue() 
    {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(5);
        assertFalse(lista.esVacio()); // agregar debe insertar el elemento
    }

    @Test
    public void testTamanoAumentaAlAgregar() 
    {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        assertEquals((Integer) 3, (Integer) lista.tamano()); // el tamano debe ser 3
    }

    @Test
    public void testObtenerPrimerElemento() 
    {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("Hola");
        lista.agregar("Mundo");
        assertEquals("Hola", lista.obtener(0)); // el primer elemento debe ser "Hola"
        assertEquals((Integer) 2, (Integer) lista.tamano()); // el tamano debe ser 2            
    }

    @Test
    public void testObtenerUltimoElemento() 
    {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("Hola");
        lista.agregar("Mundo");
        assertEquals("Mundo", lista.obtener(1)); // el último elemento debe ser "Mundo"
    }


    @Test
    public void testEliminarPrimerElementoPorIndice() 
    {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("Hola");
        lista.agregar("Mundo");

        String eliminado = lista.remover(0);

        assertEquals("Hola", eliminado);          // debe devolver el elemento eliminado
        assertEquals("Mundo", lista.obtener(0));  // ahora el primero debe ser "Mundo"
    }

    @Test
    public void testEliminarElementoMedioPorIndice() 
    {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");

        String eliminado = lista.remover(1); // eliminamos "B"

        assertEquals("B", eliminado);         // debe devolver "B"
        assertEquals((Integer) 2, (Integer) lista.tamano()); // el tamano debe ser 2
        assertEquals("A", lista.obtener(0));  // el primero debe ser "A"
        assertEquals("C", lista.obtener(1));  // el segundo debe ser "C"
    }

 
    @Test
    public void testEliminarElementoExistente() 
    {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("Hola");
        lista.agregar("Mundo");

        assertTrue(lista.remover("Hola")); // debe devolver true al encontrar el elemento
    }

    @Test
    public void testEliminarElementoNoExistente() 
    {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregar("Hola");

        assertFalse(lista.remover("Chau")); // debe devolver false si no existe
    }

    @Test
    public void testTamanoListaVacia() 
    {
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        assertEquals((Integer) 0, (Integer) lista.tamano()); // el tamano debe ser 0
    }

    @Test
    public void testListaConLibros() 
    {
        ListaEnlazada<libro> lista = new ListaEnlazada<>();
        libro l1 = new libro("001", "Java", 29.99, 5);
        libro l2 = new libro("002", "Python", 19.99, 3);

        lista.agregar(l1);
        lista.agregar(l2);

        assertEquals((Integer) 2, (Integer) lista.tamano());                          // el tamano debe ser 2
        assertEquals("001", lista.obtener(0).getCodigoIdentificacion()); // el primero debe ser l1
        assertEquals("002", lista.obtener(1).getCodigoIdentificacion()); // el segundo debe ser l2
    }
    @Test
    public void ordenarListaporTitulo()
    {
        ListaEnlazada<libro> lista = new ListaEnlazada<>();
        libro l1 = new libro("001", "Java", 29.99, 5);
        libro l2 = new libro("002", "Python", 19.99, 3);
        libro l3 = new libro("003", "C++", 39.99, 2);

        lista.agregar(l1);
        lista.agregar(l2);
        lista.agregar(l3);

        lista.ordenar((a, b) -> a.getTitulo().compareTo(b.getTitulo()));

        assertEquals("Java", lista.obtener(0).getTitulo()); // el primero debe ser "Java"
        assertEquals("Python", lista.obtener(1).getTitulo()); // el segundo debe ser "Python"
        assertEquals("C++", lista.obtener(2).getTitulo()); // el tercero debe ser "C++"}
    }
}