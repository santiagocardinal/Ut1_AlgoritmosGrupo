package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorConvocatoriaTest {

    private final GestorConvocatoria gestor = new GestorConvocatoria();

    private TDALista<Jugador> listaCon(Jugador... jugadores) {
        TDALista<Jugador> lista = new ListaEnlazada<>();
        for (Jugador j : jugadores) {
            lista.agregar(j);
        }
        return lista;
    }

    private Jugador jugador(String nombre, Division division, int partidas, Estado estado) {
        // el ordenDeRegistro real lo asigna GestorConvocatoria según la posición en la lista de entrada
        return new Jugador(nombre, division, partidas, estado, -1);
    }

    @Test
    void listaNulaDevuelveDeficitCompleto() {
        ResultadoConvocatoria resultado = gestor.armarConvocatoria(null);

        assertTrue(resultado.getConvocados().esVacio());
        assertTrue(resultado.getSuplentes().esVacio());
        assertEquals(20, resultado.getDeficit());
    }

    @Test
    void listaVaciaDevuelveDeficitCompleto() {
        ResultadoConvocatoria resultado = gestor.armarConvocatoria(new ListaEnlazada<>());

        assertTrue(resultado.getConvocados().esVacio());
        assertTrue(resultado.getSuplentes().esVacio());
        assertEquals(20, resultado.getDeficit());
    }

    @Test
    void exactamente20HabilitadosQuedanTodosConvocadosSinSuplentesNiDeficit() {
        TDALista<Jugador> jugadores = new ListaEnlazada<>();
        for (int i = 0; i < 20; i++) {
            jugadores.agregar(jugador("Jugador" + i, Division.ORO, 10, Estado.HABILITADO));
        }

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);

        assertEquals(20, resultado.getConvocados().tamaño());
        assertTrue(resultado.getSuplentes().esVacio());
        assertEquals(0, resultado.getDeficit());
    }

    @Test
    void masDe20HabilitadosLosSobrantesPasanASuplentes() {
        TDALista<Jugador> jugadores = new ListaEnlazada<>();
        for (int i = 0; i < 25; i++) {
            jugadores.agregar(jugador("Jugador" + i, Division.PLATA, 5, Estado.HABILITADO));
        }

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);

        assertEquals(20, resultado.getConvocados().tamaño());
        assertEquals(5, resultado.getSuplentes().tamaño());
        assertEquals(0, resultado.getDeficit());
    }

    @Test
    void seSeleccionaPorMayorDivisionPrimero() {
        Jugador bronce = jugador("Bronce1", Division.BRONCE, 0, Estado.HABILITADO);
        Jugador diamante = jugador("Diamante1", Division.DIAMANTE, 0, Estado.HABILITADO);

        TDALista<Jugador> jugadores = listaCon(bronce, diamante);

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);
        TDALista<Jugador> convocados = resultado.getConvocados();

        // el primer convocado (mejor prioridad) debe ser el de mayor división
        assertEquals(Division.DIAMANTE, convocados.obtener(0).getDivision());
        assertEquals(Division.BRONCE, convocados.obtener(1).getDivision());
    }

    @Test
    void empateDeDivisionSeDesempataPorMenosPartidasJugadas() {
        Jugador conMuchasPartidas = jugador("Veterano", Division.ORO, 100, Estado.HABILITADO);
        Jugador conPocasPartidas = jugador("Novato", Division.ORO, 5, Estado.HABILITADO);

        TDALista<Jugador> jugadores = listaCon(conMuchasPartidas, conPocasPartidas);

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);
        TDALista<Jugador> convocados = resultado.getConvocados();

        assertEquals("Novato", convocados.obtener(0).getNombre());
        assertEquals("Veterano", convocados.obtener(1).getNombre());
    }

    @Test
    void empateDeDivisionYPartidasSeDesempataPorOrdenDeRegistro() {
        // ambos con la misma división y partidas jugadas; el orden de registro
        // lo determina la posición en la lista de entrada
        Jugador primero = jugador("Primero", Division.PLATINO, 10, Estado.HABILITADO);
        Jugador segundo = jugador("Segundo", Division.PLATINO, 10, Estado.HABILITADO);

        TDALista<Jugador> jugadores = listaCon(primero, segundo);

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);
        TDALista<Jugador> convocados = resultado.getConvocados();

        assertEquals("Primero", convocados.obtener(0).getNombre());
        assertEquals("Segundo", convocados.obtener(1).getNombre());
    }

    @Test
    void jugadoresLesionadosYSuspendidosNoEntranComoHabilitados() {
        Jugador habilitado = jugador("Habilitado", Division.BRONCE, 0, Estado.HABILITADO);
        Jugador lesionado = jugador("Lesionado", Division.DIAMANTE, 0, Estado.LESIONADO);
        Jugador suspendido = jugador("Suspendido", Division.DIAMANTE, 0, Estado.SUSPENDIDO);

        TDALista<Jugador> jugadores = listaCon(habilitado, lesionado, suspendido);

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);
        TDALista<Jugador> convocados = resultado.getConvocados();

        // aunque lesionado/suspendido sean de mejor división, el habilitado
        // tiene prioridad absoluta por estar habilitado
        assertEquals("Habilitado", convocados.obtener(0).getNombre());
        assertEquals(3, convocados.tamaño()); // solo hay 3 jugadores en total
        assertEquals(17, resultado.getDeficit()); // faltan 17 para llegar a 20
    }

    @Test
    void seCompletaConNoHabilitadosDeMejorDivisionSiFaltanHabilitados() {
        TDALista<Jugador> jugadores = new ListaEnlazada<>();
        // solo 2 habilitados
        jugadores.agregar(jugador("Hab1", Division.ORO, 5, Estado.HABILITADO));
        jugadores.agregar(jugador("Hab2", Division.PLATA, 5, Estado.HABILITADO));
        // no habilitados de distinta división
        jugadores.agregar(jugador("Lesionado_Bronce", Division.BRONCE, 5, Estado.LESIONADO));
        jugadores.agregar(jugador("Suspendido_Diamante", Division.DIAMANTE, 5, Estado.SUSPENDIDO));

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);
        TDALista<Jugador> convocados = resultado.getConvocados();

        assertEquals(4, convocados.tamaño());
        assertEquals(0, resultado.getDeficit());
        // el no habilitado de mejor división (Diamante) debe entrar antes que el de Bronce
        assertTrue(convocados.indiceDe(buscarPorNombre(convocados, "Suspendido_Diamante"))
                < convocados.indiceDe(buscarPorNombre(convocados, "Lesionado_Bronce")));
    }

    private Jugador buscarPorNombre(TDALista<Jugador> lista, String nombre) {
        return lista.buscar(j -> j.getNombre().equals(nombre));
    }

    @Test
    void seInformaDeficitCuandoNoAlcanzanLos20Convocados() {
        TDALista<Jugador> jugadores = new ListaEnlazada<>();
        jugadores.agregar(jugador("Unico", Division.ORO, 5, Estado.HABILITADO));

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);

        assertEquals(1, resultado.getConvocados().tamaño());
        assertEquals(19, resultado.getDeficit());
    }

    @Test
    void suplentesSonSoloHabilitadosQueNoEntraronEnLos20() {
        TDALista<Jugador> jugadores = new ListaEnlazada<>();
        for (int i = 0; i < 22; i++) {
            jugadores.agregar(jugador("Hab" + i, Division.ORO, i, Estado.HABILITADO));
        }
        jugadores.agregar(jugador("NoHab", Division.DIAMANTE, 0, Estado.LESIONADO));

        ResultadoConvocatoria resultado = gestor.armarConvocatoria(jugadores);

        assertEquals(20, resultado.getConvocados().tamaño());
        assertEquals(2, resultado.getSuplentes().tamaño());
        // el no habilitado no debería figurar en suplentes: no llegó a necesitarse
        assertNull(buscarPorNombre(resultado.getSuplentes(), "NoHab"));
    }
}
