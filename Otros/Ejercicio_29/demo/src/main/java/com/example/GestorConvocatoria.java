package com.example;

public class GestorConvocatoria {

    private static final int CUPOS_TITULARES = 20;

    public ResultadoConvocatoria armarConvocatoria(TDALista<Jugador> jugadores) {
        TDALista<Jugador> convocados = new ListaEnlazada<>();
        TDALista<Jugador> suplentes = new ListaEnlazada<>();
        TDALista<Jugador> habilitados = new ListaEnlazada<>();
        TDALista<Jugador> noHabilitados = new ListaEnlazada<>();

        if (jugadores == null || jugadores.esVacio()) {
            return new ResultadoConvocatoria(convocados, suplentes, CUPOS_TITULARES);
        }

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
                habilitados.agregar(j);
            } else {
                noHabilitados.agregar(j);
            }
        }

        TDALista<Jugador> habilitadosOrdenados = habilitados.ordenar(Jugador::compareTo);


        for (int i = 0; i < habilitadosOrdenados.tamaño(); i++) {
            Jugador j = habilitadosOrdenados.obtener(i);
            if (convocados.tamaño() < CUPOS_TITULARES) {
                convocados.agregar(j);
            } else {
                suplentes.agregar(j);
            }
        }

        if (convocados.tamaño() < CUPOS_TITULARES && !noHabilitados.esVacio()) {
            TDALista<Jugador> noHabilitadosOrdenados = noHabilitados.ordenar(Jugador::compareTo);

            for (int i = 0; i < noHabilitadosOrdenados.tamaño()
                    && convocados.tamaño() < CUPOS_TITULARES; i++) {
                convocados.agregar(noHabilitadosOrdenados.obtener(i));
            }
        }

        int deficit = 0;
        if (convocados.tamaño() < CUPOS_TITULARES) {
            deficit = CUPOS_TITULARES - convocados.tamaño();
        }

        return new ResultadoConvocatoria(convocados, suplentes, deficit);
    }
}