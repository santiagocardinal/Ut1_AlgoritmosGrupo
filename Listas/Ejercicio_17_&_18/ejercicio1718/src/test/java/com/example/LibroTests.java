package com.example;
import org.junit.Test;

import com.example.libro;

import static org.junit.Assert.*;   

public class LibroTests 
{

    @Test
    public void testConstructor() 
    {
        libro l = new libro("001", "Java", 29.99, 5);

        assertEquals("001", l.getCodigoIdentificacion());
        assertNotEquals("002", l.getCodigoIdentificacion());
        assertEquals("Java", l.getTitulo());
        assertEquals((Double) 29.99, l.getPrecio());
        assertEquals((Integer) 5, (Integer) l.getCantidadEjemplares());
    }

    @Test
    public void testGetCodigoIdentificacion() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        assertEquals("001", l.getCodigoIdentificacion());
    }

    @Test
    public void testGetTitulo() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        assertEquals("Java", l.getTitulo());
    }

    @Test
    public void testGetPrecio() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        assertEquals((Double) 29.99, l.getPrecio());
    }

    @Test
    public void testGetCantidadEjemplares() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        assertEquals((Integer) 5, (Integer) l.getCantidadEjemplares());
    }

    @Test
    public void testSetTitulo() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        l.setTitulo("Python"); // cambiamos el título
        assertEquals("Python", l.getTitulo()); // debe ser el nuevo título
    }

    @Test
    public void testSetPrecio() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        l.setPrecio(49.99); // cambiamos el precio
        assertEquals((Double) 49.99, l.getPrecio()); // debe ser el nuevo precio
    }

    @Test
    public void testSetCantidadEjemplares() 
    {
        libro l = new libro("001", "Java", 29.99, 5);
        l.setCantidadEjemplares(10); // cambiamos la cantidad
        assertEquals((Integer) 10, (Integer) l.getCantidadEjemplares()); // debe ser la nueva cantidad
    }

    @Test
    public void testCantidadEjemplaresCero() 
    {
        libro l = new libro("001", "Java", 29.99, 0);
        assertEquals((Integer) 0, (Integer) l.getCantidadEjemplares()); // la cantidad puede ser 0
    }
}