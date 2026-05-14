package modelo;

/**
 * Representa una partida terminada dentro del historial del ranking.
 *
 * <p>El ranking se guarda en formato JSON. Gson usa el constructor vacio y
 * los setters para reconstruir estos objetos al leer ranking.json.</p>
 */
public class JugadorRegistro {
    private String nombre;
    private int puntaje;
    private int tiempoJugado;
    private int profundidadAlcanzada;
    private int nivelAlcanzado;
    private String fechaHora;

    /**
     * Constructor requerido por Gson al leer datos desde JSON.
     */
    public JugadorRegistro() {
    }

    /**
     * Crea un registro completo de una partida.
     */
    public JugadorRegistro(String nombre, int puntaje, int tiempoJugado,
                           int profundidadAlcanzada, int nivelAlcanzado, String fechaHora) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.tiempoJugado = tiempoJugado;
        this.profundidadAlcanzada = profundidadAlcanzada;
        this.nivelAlcanzado = nivelAlcanzado;
        this.fechaHora = fechaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(int tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public int getProfundidadAlcanzada() {
        return profundidadAlcanzada;
    }

    public void setProfundidadAlcanzada(int profundidadAlcanzada) {
        this.profundidadAlcanzada = profundidadAlcanzada;
    }

    public int getNivelAlcanzado() {
        return nivelAlcanzado;
    }

    public void setNivelAlcanzado(int nivelAlcanzado) {
        this.nivelAlcanzado = nivelAlcanzado;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return nombre + " - " + puntaje + " puntos";
    }
}
