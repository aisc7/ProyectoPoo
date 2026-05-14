package modelo;

import java.util.List;
import util.RankingJsonRepository;


/**
 * Fachada del modelo para trabajar con el ranking del juego.
 *
 * <p>La vista y el controlador usan esta clase y no conocen detalles de Json
 * ni de archivos. Esto mantiene la separacion MVC y deja la persistencia
 * encapsulada en {@link RankingJsonRepository}.</p>
 */
public class Ranking {
    private final RankingJsonRepository repositorio;

    /**
     * Crea el ranking y asegura que el archivo JSON exista.
     */
    public Ranking() {
        repositorio = new RankingJsonRepository();
        repositorio.inicializarArchivo();
    }

    /**
     * Registra una partida terminada en el historial local.
     */
    public void registrarPartida(JugadorRegistro registro) {
        repositorio.agregarRegistro(registro);
    }

    /**
     * Mantiene compatibilidad con codigo previo que agregaba jugadores al ranking.
     */
    public void agregarJugador(JugadorRegistro registro) {
        registrarPartida(registro);
    }

    /**
     * Devuelve el historial completo de partidas guardadas.
     */
    public List<JugadorRegistro> obtenerHistorial() {
        return repositorio.cargarHistorial();
    }

    /**
     * Devuelve los tres mejores puntajes guardados.
     */
    public List<JugadorRegistro> obtenerTop3() {
        return repositorio.obtenerTop3();
    }
}
