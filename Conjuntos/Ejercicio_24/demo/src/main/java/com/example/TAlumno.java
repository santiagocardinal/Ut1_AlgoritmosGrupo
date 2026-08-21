package ucu.edu.aed.tda;

import java.util.Objects;

/**
 * Representa un alumno identificado por su cédula (4 dígitos).
 *
 * <p>El orden natural de {@code TAlumno} está dado por la cédula,
 * ya que las operaciones de {@link Conjuntos} (unión, intersección)
 * requieren que los elementos estén ordenados según {@link Comparable}.</p>
 */
public class TAlumno implements Comparable<TAlumno> 
{

    private final String cedula;
    private String nombre;
    private String apellido;

    public TAlumno(String cedula, String nombre, String apellido) 
    {
        if (cedula == null || cedula.isBlank()) 
        {
            throw new IllegalArgumentException("La cédula no puede ser nula ni vacía");
        }
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCedula() 
    {
        return cedula;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public String getApellido() 
    {
        return apellido;
    }

    /**
     * Orden natural por cédula (requerido por Conjuntos, que asume
     * elementos ordenados para union/interseccion en O(n+m)).
     */
    @Override
    public int compareTo(TAlumno otro) 
    {
        return this.cedula.compareTo(otro.cedula);
    }

    /**
     * Dos alumnos son iguales si tienen la misma cédula.
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) return true;
        if (!(obj instanceof TAlumno)) return false;
        TAlumno otro = (TAlumno) obj;
        return this.cedula.equals(otro.cedula);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(cedula);
    }

    @Override
    public String toString() 
    {
        return cedula + " - " + apellido + ", " + nombre;
    }
}