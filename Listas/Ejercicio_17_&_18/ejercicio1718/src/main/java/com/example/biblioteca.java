package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class biblioteca 
{

    private ListaEnlazada<libro> libros;
    private ListaEnlazada<libro> librosquitados;

    public biblioteca(String adquisiciones) 
    {
        this.libros = new ListaEnlazada<>();
        this.librosquitados = new ListaEnlazada<>(); 
        archivoAdquisiciones(adquisiciones);
    }


    private void archivoAdquisiciones(String archivo) 
    {

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) //leo el archivo linea por linea
        {
            String cadena;//variable para almacenar cada linea del archivo

            while ((cadena = lector.readLine()) != null) //mientras que la siguiente linea no sea nula, se procesa la linea actual
            {
                String[] partes = cadena.split(",");//divido la linea guaradada en la variable cadena y la separo en partes utilizando la coma como delimitador. Esto me da un arreglo de cadenas, donde cada elemento corresponde a un campo del libro (código, título, precio, cantidad).

                String codigo   = partes[0].trim();
                String titulo   = partes[1].trim();
                Double precio   = Double.parseDouble(partes[2].trim());
                int cantidad    = Integer.parseInt(partes[3].trim());

                libro libroExistente = buscarPorCodigo(codigo); //pregunto si ya tengo un libro con el mismo codigo

                if (libroExistente != null) //si el libro ya existe, actualizo la cantidad de ejemplares sumando la cantidad del nuevo libro a la cantidad existente. Esto se hace utilizando el método setCantidadEjemplares() del objeto libroExistente
                {
                    libroExistente.setCantidadEjemplares(libroExistente.getCantidadEjemplares() + cantidad);
                } 
                else 
                {
                    libros.agregar(new libro(codigo, titulo, precio, cantidad));//si el libro no existe, creo un nuevo objeto libro con los datos proporcionados y lo agrego a la lista de libros utilizando el método agregar() de la clase ListaEnlazada.
                }

            }

        } catch (IOException e) 
        {
            System.out.println("Error al leer adquisiciones: " + e.getMessage());//tira error si no puede leer el archivo, mostrando un mensaje de error con la descripción del problema.
        }

    }


    public void mostrarPrestamos(String archivo) 
    {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo)))
        {
            String cadena;

            while ((cadena = lector.readLine()) != null)
            {
                String[] partes = cadena.split(",");

                String codigo   = partes[0].trim();
                String tipo     = partes[1].trim();
                int cantidad    = Integer.parseInt(partes[2].trim());

                libro libroEncontrado = buscarPorCodigo(codigo);
                if (libroEncontrado == null) 
                {
                    System.out.println("Libro con código " + codigo + " no encontrado.");
                    continue;
                }

                if (tipo.compareTo("PRESTAMO") == 0 || tipo.compareTo("prestamo") == 0)
                {
                    int stockActual = mostrarCantidadEjemplares(codigo);

                    if (stockActual <= 0)
                    {
                        System.out.println("No hay ejemplares disponibles para prestar del libro: " + libroEncontrado.getTitulo());
                    }
                    else if (stockActual >= cantidad)
                    {
                        int nuevoStock = stockActual - cantidad;
                        changeStock(codigo, nuevoStock);
                        System.out.println("Préstamo registrado: " + libroEncontrado.getTitulo()
                            + " | Prestados: " + cantidad
                            + " | Stock restante: " + nuevoStock);
                        if (nuevoStock == 0) 
                            {
                            System.out.println("El libro: " + libroEncontrado.getTitulo() + " se ha agotado.");
                            }
                    }
                    else // stockActual < cantidad (piden más de lo que hay)
                    {
                        int prestados = stockActual;
                        changeStock(codigo, 0);
                        System.out.println("Préstamo PARCIAL: " + libroEncontrado.getTitulo()
                            + " | Solicitados: " + cantidad
                            + " | Prestados: " + prestados
                            + " | Stock restante: 0");
                        System.out.println("El libro: " + libroEncontrado.getTitulo() + " se ha agotado.");
                    }
                }
                else if (tipo.compareTo("DEVOLUCION") == 0 || tipo.compareTo("devolucion") == 0)                
                {
                    int stockActual = mostrarCantidadEjemplares(codigo);
                    int nuevoStock = stockActual + cantidad;
                    changeStock(codigo, nuevoStock);

                    System.out.println("Devolución registrada: " + libroEncontrado.getTitulo()
                        + " | Devueltos: " + cantidad
                        + " | Stock actual: " + nuevoStock);
                }
            }

        } catch (IOException e)
        {
            System.out.println("Error al leer préstamos y devoluciones: " + e.getMessage());    
        }
    }
    public libro buscarPorCodigo(String codigo) 
    {
        int i = 0;
        while (i < libros.tamano()) 
        {
            libro l = libros.obtener(i);
            if (l.getCodigoIdentificacion().compareTo(codigo) == 0) 
            {
                return l;
            }
            i++;
        }
        return null;
    }

    public void consultarExistencias(String codigo) 
    {
        libro l = buscarPorCodigo(codigo);
        if (l != null) 
        {
            System.out.println("Libro: " + l.getTitulo() + " | Stock: " + l.getCantidadEjemplares());
        } 
        else 
        {
            System.out.println("No se encontró el libro con código: " + codigo);
        }
    }


    public int mostrarCantidadEjemplares(String codigo) 
    {
        int i = 0;
        while (i < libros.tamano()) 
        {
            libro l = libros.obtener(i);
            if (l.getCodigoIdentificacion().compareTo(codigo) == 0) 
            {
                return l.getCantidadEjemplares();
            }
            i++;
        }
        return 0;
    }

    public void changeStock(String codigo, int cantidad) 
    {
        int i = 0;
        while (i < libros.tamano()) 
        {
            libro l = libros.obtener(i);
            if (l.getCodigoIdentificacion().compareTo(codigo) == 0) 
            {
                l.setCantidadEjemplares(cantidad);
                return;
            }
            i++;
        }
        System.out.println("No se encontró el libro con código: " + codigo);
    }
    
    public void retirarLibro(String codigo) 
    {
    int i = 0;
    while (i < libros.tamano()) 
    {
        libro l = libros.obtener(i);
        if (l.getCodigoIdentificacion().compareTo(codigo) == 0) 
        {
            libros.remover(i);
            librosquitados.agregar(l);
            return;
        }
        i++;
    }
        System.out.println("No se encontró el libro con código: " + codigo);
    }
    public void ordenarLibrosretirados()
    {
    librosquitados.ordenar((a, b) -> a.getTitulo().compareTo(b.getTitulo()));
    }
    
    //Este metodo simplemente esta para saber si se quitaron bin los libros retirados y se guardaron bien en la lista de libros retirados.
    public ListaEnlazada<libro> getLibrosRetirados() 
    {
    for(int i =0; i <librosquitados.tamano(); i++)
        {
            libro l = librosquitados.obtener(i);
            System.out.println("Libro retirado: " + l.getTitulo() + " | Código: " + l.getCodigoIdentificacion());
        }
        return librosquitados;
    }

    public void mostrarLibros() 
    {

        int i = 0;
        while (i < libros.tamano()) 
        {
            libro l = libros.obtener(i);
            System.out.println("Título : " + l.getTitulo() + "| Stock: " + l.getCantidadEjemplares());
            System.out.println("───────────────────────────────────────");
            i++;
        }
    }
    public void ordenarPorTitulo()
    {
    libros.ordenar((a, b) -> a.getTitulo().compareTo(b.getTitulo()));
    }
}