package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class ColaCircularTest 
{

    private ColaCircular<Integer> cola;

    @BeforeEach
    void setUp() 
    {
        cola = new ColaCircular<>(4);
    }

    @Test
    void colaNuevaEstaVacia() 
    {
        assertTrue(cola.esVacio());
    }

    @Test
    void quitarDeColaVaciaLanzaExcepcion() 
    {
        try 
        {
            cola.quitaDeCola();
            fail("Se esperaba NoSuchElementException");
        } 
        catch (NoSuchElementException e) 
        {}
    }

    @Test
    void frenteDeColaVaciaLanzaExcepcion() 
    {
        try 
        {
            cola.frente();
            fail("Se esperaba NoSuchElementException");
        } catch (NoSuchElementException e) 
        {}
    }

    @Test
    void colaLlenaLanzaExcepcionAlAgregar() 
    {
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3); 

        try 
        {
            cola.poneEnCola(4);
            fail("Se esperaba IllegalStateException");
        } 
        catch (IllegalStateException e) 
        {}
    }

    @Test
    void respetaOrdenFIFO() 
    {
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);
        assertEquals(1, cola.quitaDeCola());
        assertEquals(2, cola.quitaDeCola());
        assertEquals(3, cola.quitaDeCola());
        assertTrue(cola.esVacio());
    }

    @Test
    void wraparoundDelVectorFuncionaCorrectamente() 
    {
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        cola.poneEnCola(3);
        assertEquals(1, cola.quitaDeCola()); 
        assertEquals(2, cola.quitaDeCola());
        cola.poneEnCola(4);
        cola.poneEnCola(5); 
        assertEquals(3, cola.quitaDeCola());
        assertEquals(4, cola.quitaDeCola());
        assertEquals(5, cola.quitaDeCola());
        assertTrue(cola.esVacio());
    }
}