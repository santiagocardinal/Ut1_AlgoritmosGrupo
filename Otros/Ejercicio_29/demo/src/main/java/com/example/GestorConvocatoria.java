package com.example;

import ucu.edu.aed.tda.ColaPrioridad;

public class GestorConvocatoria {

    public ResultadoConvocatoria armarConvocatoria(TDALista<Jugador> jugadores) {
        TDALista<Jugador> convocados = new ListaEnlazada<>();
        TDALista<Jugador> suplentes = new ListaEnlazada<>();
        TDALista<Jugador> noHabilitados = new ListaEnlazada<>();

        if (jugadores == null || jugadores.esVacio()) {
            return new ResultadoConvocatoria(convocados, suplentes, 20);
        }

        ColaPrioridad<Jugador> colaHabilitados = new ColaPrioridad<>();

        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador orig = jugadores.obtener(i);
            
            Jugador j = new Jugador(
                orig.getNombre(),
                orig.getDivision(),
                orig.getPartidasJugadas(),
                orig.getEstado(),
                i
            );

            if (j.getEstado() == Estado.HABILITADO) {
                int prioridadCalculada = (j.getDivision().ordinal() * 10000) 
                                       - (j.getPartidasJugadas() * 10) 
                                       - i;
                
                colaHabilitados.poneEnCola(j, prioridadCalculada);
            } else {
                noHabilitados.agregar(j);
            }
        }

        while (!colaHabilitados.vacia()) {
            Jugador mejor = colaHabilitados.quitaDeCola();
            
            if (convocados.tamaño() < 20) {
                convocados.agregar(mejor);
            } else {
                suplentes.agregar(mejor);
            }
        }

        if (convocados.tamaño() < 20 && !noHabilitados.esVacio()) {
            TDALista<Jugador> noHabOrdenados = noHabilitados.ordenar(
                (j1, j2) -> j2.getDivision().ordinal() - j1.getDivision().ordinal()
            );

            for (int i = 0; i < noHabOrdenados.tamaño() && convocados.tamaño() < 20; i++) {
                convocados.agregar(noHabOrdenados.obtener(i));
            }
        }

        int deficit = 0;
        if (convocados.tamaño() < 20) {
            deficit = 20 - convocados.tamaño();
        }

        return new ResultadoConvocatoria(convocados, suplentes, deficit);
    }
}