package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ranking en memoria de las mejores partidas.
 */
public class Ranking {
    private final List<JugadorRegistro> registros;

    /**
     * Crea un ranking vacio.
     */
    public Ranking() {
        registros = new ArrayList<>();
    }

    /**
     * Agrega una partida al historial en memoria.
     */
    public void agregarJugador(JugadorRegistro registro) {
        registros.add(registro);
    }

    /**
     * Devuelve los tres puntajes mas altos.
     */
    public List<JugadorRegistro> obtenerTop3() {
        List<JugadorRegistro> copia = new ArrayList<>(registros);
        copia.sort(Comparator.comparingInt(JugadorRegistro::getPuntaje).reversed());
        return copia.subList(0, Math.min(3, copia.size()));
    }

    /**
     * TODO: Preparar guardado en archivo .txt si el curso lo solicita.
     */
    public void guardarEnArchivo() {
        // Metodo reservado para una version posterior.
    }
}
