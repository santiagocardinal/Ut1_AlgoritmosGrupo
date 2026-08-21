package ucu.edu.aed.tda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Casos de prueba para union() e interseccion() de Conjuntos<TAlumno>,
 * de acuerdo a los casos especificados en la Parte 1 (Ejercicio 24).
 */
public class ConjuntoTest 
{

    private TAlumno a1;
    private TAlumno a2;
    private TAlumno a3;
    private TAlumno a4;
    private TAlumno a5;
    private Conjuntos<TAlumno> aed1;
    private Conjuntos<TAlumno> pf;

    @BeforeEach
    void setUp() 
    {
        a1 = new TAlumno("1001", "Ana", "Perez");
        a2 = new TAlumno("1002", "Bruno", "Gomez");
        a3 = new TAlumno("1003", "Carla", "Diaz");
        a4 = new TAlumno("1004", "Diego", "Suarez");
        a5 = new TAlumno("1005", "Elena", "Rios");

        aed1 = new Conjuntos<>();
        pf = new Conjuntos<>();
    }

    /** Inserta manteniendo el orden por cédula, precondición de union/interseccion. */
    private void insertar(Conjuntos<TAlumno> c, TAlumno... alumnos) 
    {
        for (TAlumno al : alumnos) 
        {
            int i = 0;
            while (i < c.tamaño() && c.obtener(i).compareTo(al) < 0) 
            {
                i++;
            }
            c.agregar(i, al);
        }
    }

    @Test
    void unionSinSolapamiento() 
    {
        insertar(aed1, a1, a2);
        insertar(pf, a3, a4);

        TDAConjunto<TAlumno> resultado = aed1.union(pf);

        assertEquals(4, resultado.tamaño());
        assertTrue(resultado.contiene(a1));
        assertTrue(resultado.contiene(a2));
        assertTrue(resultado.contiene(a3));
        assertTrue(resultado.contiene(a4));
    }

    @Test
    void unionConSolapamientoNoDuplicaElementos() 
    {
        insertar(aed1, a1, a3, a4);
        insertar(pf, a3, a4, a5);

        TDAConjunto<TAlumno> resultado = aed1.union(pf);

        assertEquals(4, resultado.tamaño()); 
        assertTrue(resultado.contiene(a1));
        assertTrue(resultado.contiene(a3));
        assertTrue(resultado.contiene(a4));
        assertTrue(resultado.contiene(a5));
    }

    @Test
    void unionConConjuntoVacio() 
    {
        insertar(aed1, a1, a2);

        TDAConjunto<TAlumno> resultado = aed1.union(pf); 

        assertEquals(2, resultado.tamaño());
        assertTrue(resultado.contiene(a1));
        assertTrue(resultado.contiene(a2));
    }

    @Test
    void unionDeDosConjuntosVacios() 
    {
        TDAConjunto<TAlumno> resultado = aed1.union(pf);
        assertTrue(resultado.esVacio());
    }

    @Test
    void unionMantieneElOrden() 
    {
        insertar(aed1, a1, a3);
        insertar(pf, a2, a4);

        TDAConjunto<TAlumno> resultado = aed1.union(pf);

        assertEquals(a1, resultado.obtener(0));
        assertEquals(a2, resultado.obtener(1));
        assertEquals(a3, resultado.obtener(2));
        assertEquals(a4, resultado.obtener(3));
    }


    @Test
    void interseccionSinElementosComunes() 
    {
        insertar(aed1, a1, a2);
        insertar(pf, a3, a4);

        TDAConjunto<TAlumno> resultado = aed1.interseccion(pf);

        assertTrue(resultado.esVacio());
    }

    @Test
    void interseccionConElementosComunes() 
    {
        insertar(aed1, a1, a3, a4);
        insertar(pf, a3, a4, a5);

        TDAConjunto<TAlumno> resultado = aed1.interseccion(pf);

        assertEquals(2, resultado.tamaño());
        assertTrue(resultado.contiene(a3));
        assertTrue(resultado.contiene(a4));
        assertFalse(resultado.contiene(a1));
        assertFalse(resultado.contiene(a5));
    }

    @Test
    void interseccionConConjuntoVacio() 
    {
        insertar(aed1, a1, a2);

        TDAConjunto<TAlumno> resultado = aed1.interseccion(pf); // pf vacío

        assertTrue(resultado.esVacio());
    }

    @Test
    void interseccionDeDosConjuntosVacios() 
    {
        TDAConjunto<TAlumno> resultado = aed1.interseccion(pf);
        assertTrue(resultado.esVacio());
    }

    @Test
    void todoElementoDeLaInterseccionPerteneceAAmbosConjuntos() 
    {
        insertar(aed1, a1, a2, a3);
        insertar(pf, a2, a3, a4);

        TDAConjunto<TAlumno> resultado = aed1.interseccion(pf);

        for (int i = 0; i < resultado.tamaño(); i++) {
            TAlumno al = resultado.obtener(i);
            assertTrue(aed1.contiene(al));
            assertTrue(pf.contiene(al));
        }
    }

    @Test
    void conjuntoConsigoMismo() 
    {
        insertar(aed1, a1, a2, a3);

        TDAConjunto<TAlumno> union = aed1.union(aed1);
        TDAConjunto<TAlumno> interseccion = aed1.interseccion(aed1);

        assertEquals(3, union.tamaño());
        assertEquals(3, interseccion.tamaño());
    }
}