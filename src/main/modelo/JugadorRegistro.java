package modelo;

/**
 * Registro simple de una partida terminada.
 */
public class JugadorRegistro {
    private final String nombre;
    private final int puntaje;
    private final int tiempoSegundos;
    private final int profundidad;

    /**
     * Crea un registro para el ranking.
     */
    public JugadorRegistro(String nombre, int puntaje, int tiempoSegundos, int profundidad) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.tiempoSegundos = tiempoSegundos;
        this.profundidad = profundidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public int getProfundidad() {
        return profundidad;
    }
}
