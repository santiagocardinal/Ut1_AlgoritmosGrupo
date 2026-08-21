package com.example;

public class Jugador implements Comparable<Jugador> 
{
    private String nombre;
    private Division division;
    private int partidasJugadas;
    private Estado estado;
    private int ordenDeRegistro;

    public Jugador(String nombre, Division division, int partidasJugadas, Estado estado, int ordenDeRegistro) 
    {
        this.nombre = nombre;
        this.division = division;
        this.partidasJugadas = partidasJugadas;
        this.estado = estado;
        this.ordenDeRegistro = ordenDeRegistro;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public Division getDivision() 
    {
        return division;
    }

    public int getPartidasJugadas() 
    {
        return partidasJugadas;
    }

    public Estado getEstado() 
    {
        return estado;
    }

    public int getOrdenDeRegistro() 
    {
        return ordenDeRegistro;
    }

    @Override
    public int compareTo(Jugador otro) 
    {
        if (this.division != otro.division) 
        {
            return otro.division.ordinal() - this.division.ordinal();
        }

        if (this.partidasJugadas != otro.partidasJugadas) 
        {
            return Integer.compare(this.partidasJugadas, otro.partidasJugadas);
        }
        return Integer.compare(this.ordenDeRegistro, otro.ordenDeRegistro);
    }

    @Override
    public String toString() 
    {
        return "Jugador{" + "nombre='" + nombre + '\'' + ", division=" + division + ", partidasJugadas=" + partidasJugadas + ", estado=" + estado + ", ordenDeRegistro=" + ordenDeRegistro +'}';
    }
}
