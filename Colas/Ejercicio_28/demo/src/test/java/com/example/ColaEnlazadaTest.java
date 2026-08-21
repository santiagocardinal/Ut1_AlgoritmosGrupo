package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class ColaEnlazadaTest 
{

    private ColaEnlazada<Integer> cola;

    @BeforeEach
    void setUp() 
    {
        cola = new ColaEnlazada<>();
    }

    @Test
    void colaNuevaEstaVacia() 
    {
        assertTrue(cola.esVacio());
    }

    @Test
    void ponerEnColaDejaDeEstarVacia() 
    {
        cola.poneEnCola(10);
        assertFalse(cola.esVacio());
    }

    @Test
    void frenteDevuelvePrimerElementoSinQuitarlo() 
    {
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        assertEquals(1, cola.frente());
        assertEquals(1, cola.frente()); // llamarlo de nuevo no debe cambiar nada
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
    void quitarDeColaVaciaLanzaExcepcion() 
    {
        try 
        {
            cola.quitaDeCola();
            fail("Se esperaba NoSuchElementException");
        } 
        catch (NoSuchElementException e) 
        {
            // excepción esperada, el test pasa
        }
    }

    @Test
    void frenteDeColaVaciaLanzaExcepcion() 
    {
        try 
        {
            cola.frente();
            fail("Se esperaba NoSuchElementException");
        } 
        catch (NoSuchElementException e) 
        {
            // excepción esperada, el test pasa
        }
    }

    @Test
    void puedeReutilizarseDespuesDeVaciarse() 
    {
        cola.poneEnCola(1);
        cola.quitaDeCola();
        assertTrue(cola.esVacio());

        cola.poneEnCola(99);
        assertEquals(99, cola.frente());
        assertFalse(cola.esVacio());
    }
}