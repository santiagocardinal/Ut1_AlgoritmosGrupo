package com.example;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

import com.example.ListaEnlazada;
import com.example.biblioteca;
import com.example.libro;
public class bibliotecaTest 
{
    //creo archivos para probar si fuincionan los métodos de la clase biblioteca
 
    private static final String ARCHIVO_ADQUISICIONES = "src/test/java/com/example/adquisicionesTest.txt";
    private static final String ARCHIVO_PRESTAMOS     = "src/test/java/com/example/prestamosTest.txt";

 
    @Test
    public void crearArchivos() throws IOException 
    {
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("002, Python, 19.99, 3\n");
        adquisiciones.write("003, C++, 39.99, 2\n");
        adquisiciones.close();
 
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, PRESTAMO, 2\n");
        prestamos.write("002, DEVOLUCION, 1\n");
        prestamos.close();
    }
 
    // ─────────────────────────────────────────────
    //  CARGA DE ADQUISICIONES
    // ─────────────────────────────────────────────
 
    @Test
    public void testCargarAdquisiciones()  throws IOException
    {
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("002, Python, 19.99, 3\n");
        adquisiciones.write("003, C++, 39.99, 2\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        assertNotNull(b.buscarPorCodigo("001")); // el libro 001 debe existir
        assertNotNull(b.buscarPorCodigo("002")); // el libro 002 debe existir
        assertNotNull(b.buscarPorCodigo("003")); // el libro 003 debe existir
    }
 
    @Test
    public void testAdquisicionDuplicadaSumaEjemplares() throws IOException 
    {
        // mismo código dos veces: debe sumar los ejemplares
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("001, Java, 29.99, 3\n");
        adquisiciones.close();
 
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        assertEquals((Integer) 8, (Integer) b.mostrarCantidadEjemplares("001")); // 5 + 3 = 8
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("002")); // el código 002 no se cargó
    }
 
    // ─────────────────────────────────────────────
    //  BUSCAR POR CÓDIGO
    // ─────────────────────────────────────────────
 
    @Test
    public void testBuscarPorCodigoExistente() 
    {
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        libro l = b.buscarPorCodigo("001");
 
        assertNotNull(l);//primero consulto que no sea nulo y luego verifico que el título sea correcto
        assertEquals("Java", l.getTitulo());
    }
 
    @Test
    public void testBuscarPorCodigoNoExistente() 
    {
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        assertNull(b.buscarPorCodigo("999")); // código inexistente → por ende debe devolver null
    }
 
    // ─────────────────────────────────────────────
    //  MOSTRAR CANTIDAD DE EJEMPLARES
    // ─────────────────────────────────────────────
 
    @Test
    public void testMostrarCantidadEjemplares() throws IOException
    {
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("002, Python, 19.99, 3\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        assertEquals((Integer) 5, (Integer) b.mostrarCantidadEjemplares("001")); 
        assertEquals((Integer) 3, (Integer) b.mostrarCantidadEjemplares("002"));
    }
 
    @Test
    public void testMostrarCantidadEjemplaresNoExistente() 
    {
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("999")); // si no existe devuelve 0
    }
 
    // ─────────────────────────────────────────────
    //  CAMBIAR STOCK
    // ─────────────────────────────────────────────
 
    @Test
    public void testChangeStock() 
    {
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.changeStock("001", 10);
        assertEquals((Integer) 10, (Integer) b.mostrarCantidadEjemplares("001"));
    }
 
    @Test
    public void testChangeStockACero() 
    {
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.changeStock("001", 0);
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("001")); // stock puede ser 0
    }
 
    @Test
    public void testChangeStockNoExistente() 
    {
        // cambiar stock de un código que no existe no debe lanzar excepción
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.changeStock("999", 5); // no debe lanzar excepción
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("999")); // sigue sin existir
    }
 
    // ─────────────────────────────────────────────
    //  RETIRAR LIBRO
    // ─────────────────────────────────────────────
 
    @Test
    public void testLibrosRetirados() throws IOException
    {
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("002, Python, 19.99, 3\n");
        adquisiciones.write("003, C++, 39.99, 2\n");
        adquisiciones.close();
        
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        
        b.retirarLibro("001");
        b.retirarLibro("002");
        b.ordenarLibrosretirados();
        
        ListaEnlazada<libro> retirados = b.getLibrosRetirados();
        assertEquals(2, retirados.tamano());  
        assertEquals("001", retirados.obtener(0).getCodigoIdentificacion()); // Java debe estar primero por orden alfabético
        assertEquals("002", retirados.obtener(1).getCodigoIdentificacion());
   }

    @Test
        
    public void testRetirarLibroNoAfectaOtros() throws IOException 
    {
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("002, Python, 19.99, 3\n");
        adquisiciones.write("003, C++, 39.99, 2\n");
        adquisiciones.close();
        
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        
        b.retirarLibro("001");
        assertNull(b.buscarPorCodigo("001"));
        assertNotNull("El libro 002 debería seguir existiendo", b.buscarPorCodigo("002"));
        assertNotNull("El libro 003 debería seguir existiendo", b.buscarPorCodigo("003"));
        
        ListaEnlazada<libro> retirados = b.getLibrosRetirados();
        assertEquals(1, retirados.tamano());
        assertEquals("001", retirados.obtener(0).getCodigoIdentificacion());
    }


    // ─────────────────────────────────────────────
    //  PRÉSTAMOS Y DEVOLUCIONES
    // ─────────────────────────────────────────────
 
    @Test
    public void testPrestamoReduceStock() throws IOException 
    {
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, PRESTAMO, 2\n");
        prestamos.close();
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();

        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.mostrarPrestamos(ARCHIVO_PRESTAMOS);
 
        assertEquals((Integer) 3, (Integer) b.mostrarCantidadEjemplares("001")); // 5 - 2 = 3
    }
 
    @Test
    public void testDevolucionAumentaStock() throws IOException 
    {
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, DEVOLUCION, 3\n");
        prestamos.close();
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.mostrarPrestamos(ARCHIVO_PRESTAMOS);
 
        assertEquals((Integer) 8, (Integer) b.mostrarCantidadEjemplares("001")); // 5 + 3 = 8
    }
 
    @Test
    public void testPrestamoParcial() throws IOException //REVISARLO DE NUEVO
    {
        // se piden más ejemplares de los disponibles: se prestan todos y el stock queda en 0
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, PRESTAMO, 99\n");
        prestamos.close();
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.mostrarPrestamos(ARCHIVO_PRESTAMOS);
 
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("001")); // stock = 0
    }
 
    @Test
    public void testPrestamoLibroSinStock() throws IOException 
    {
        // intentar prestar un libro que ya tiene stock 0
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, PRESTAMO, 1\n");
        prestamos.close();
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();

        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.changeStock("001", 0); // forzamos stock a 0 antes del préstamo
        b.mostrarPrestamos(ARCHIVO_PRESTAMOS);
 
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("001")); // no debe bajar de 0
    }
 
    @Test
    public void testPrestamoExactoAgotaStock() throws IOException 
    {
        // prestamos exactamente los ejemplares disponibles
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, PRESTAMO, 5\n"); // hay exactamente 5
        prestamos.close();
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.mostrarPrestamos(ARCHIVO_PRESTAMOS);
 
        assertEquals((Integer) 0, (Integer) b.mostrarCantidadEjemplares("001")); // stock = 0
    }
 
    @Test
    public void testPrestamosCombinados() throws IOException 
    {
        // múltiples operaciones sobre el mismo libro
        FileWriter prestamos = new FileWriter(ARCHIVO_PRESTAMOS);
        prestamos.write("001, PRESTAMO, 2\n");    // 5 - 2 = 3
        prestamos.write("001, DEVOLUCION, 1\n");  // 3 + 1 = 4
        prestamos.write("001, PRESTAMO, 1\n");    // 4 - 1 = 3
        prestamos.close();
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.mostrarPrestamos(ARCHIVO_PRESTAMOS);
 
        assertEquals((Integer) 3, (Integer) b.mostrarCantidadEjemplares("001")); // stock final = 3
    }
 
    // ─────────────────────────────────────────────
    //  CONSULTAR EXISTENCIAS
    // ─────────────────────────────────────────────
 
    @Test
    public void testConsultarExistenciasExistente() throws IOException
    {
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.close();
        // debe ejecutarse sin lanzar excepción para un libro existente
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.consultarExistencias("001"); // no debe lanzar excepción
    }
 
    @Test
    public void testConsultarExistenciasNoExistente() throws IOException
    {
        // debe ejecutarse sin lanzar excepción para un código inexistente
        FileWriter adquisiciones = new FileWriter(ARCHIVO_ADQUISICIONES);
        adquisiciones.write("001, Java, 29.99, 5\n");
        adquisiciones.write("002, Python, 19.99, 3\n");
        adquisiciones.write("003, C++, 39.99, 2\n");
        adquisiciones.close();
        biblioteca b = new biblioteca(ARCHIVO_ADQUISICIONES);
        b.consultarExistencias("999"); // no debe lanzar excepción
    }
}