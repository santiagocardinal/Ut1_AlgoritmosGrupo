package com.example;

public class ResultadoConvocatoria 
{
    private TDALista<Jugador> convocados;
    private TDALista<Jugador> suplentes;
    private int deficit;

    public ResultadoConvocatoria(TDALista<Jugador> convocados, TDALista<Jugador> suplentes, int deficit) 
    {
        this.convocados = convocados;
        this.suplentes = suplentes;
        this.deficit = deficit;
    }

    public TDALista<Jugador> getConvocados() 
    {
        return convocados;
    }

    public TDALista<Jugador> getSuplentes() 
    {
        return suplentes;
    }

    public int getDeficit() 
    {
        return deficit;
    }

    @Override
    public String toString() 
    {
        return "ResultadoConvocatoria{" + "convocados=" + convocados.tamaño() + ", suplentes=" + suplentes.tamaño() + ", deficit=" + deficit +'}';
    }
}