package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PilaEnlazadaTest 
{
    ListaEnlazada<Libro> lista;
    ListaEnlazada<Libro> vacia;

    @BeforeEach
    //este método lo hice porque en P2 lo usaba para inicializar todo en una parte y no tener que hacerlo en cada método de un test
    public void setUp() 
    {
        lista = new ListaEnlazada<Libro>();
        vacia = new ListaEnlazada<Libro>();
        lista.insertar(new Libro("Cien años de soledad", "001", 1500.0, 3));
        lista.insertar(new Libro("1984", "002", 1200.0, 5));
        lista.insertar(new Libro("El principito", "003", 900.0, 2));

    }

    @Test
    //Este es el test sobre un elemento existente
    public void testQuitarElementoExistente()
    {
        Libro libroQuitado = lista.quitar("002");
        assertEquals("1984", libroQuitado.getTitulo());
        assertEquals("002", libroQuitado.getCodigo());
    }

    @Test
    //Este es el test sobre un elemento existente
    public void testEliminarElementoExistente()
    {
        Libro libroEliminado = lista.eliminar("002");
        //eliminar no devuelve nada, entonces se verifica que ya no este en la lista
        assertNull(lista.buscar("002"));
        //se verifica que los demas sigan estando
        assertNotNull(lista.buscar("001"));
        assertNotNull(lista.buscar("003"));
    }

    @Test
    //este es el test sobre un elemento inexistente
    public void testQuitarElementoInexistente() 
    {
        Libro libroQuitado = lista.quitar("999"); 
        assertNull(libroQuitado);
    }

    @Test
    //Este es el test sobre un elemento inexistente
    public void testEliminarElementoInexistente()
    {
        //al no existir no devuelve nada
        Libro libroEliminado = lista.eliminar("999");
        //se busca los libros que si existen para descartar problemas
        assertNotNull(lista.buscar("001"));
        assertNotNull(lista.buscar("002"));
        assertNotNull(lista.buscar("003"));
    }

    @Test
    //este es el test sobre una lista vacia
    public void testQuitarElementoEnListaVacia() 
    {
        Libro libroQuitado = vacia.quitar("001"); 
        assertNull(libroQuitado);
    }

    @Test
    //este es el test sobre una lista vacia
    public void testEliminarElementoEnListaVacia() 
    {
        vacia.eliminar("001"); 
        //se verifica que la lista este vacia
        assertTrue(vacia.esVacia());
    }
}