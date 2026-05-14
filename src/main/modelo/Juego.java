package modelo;

import util.CargadorRecursos;

import java.awt.Image;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Modelo principal: contiene estado, reglas y actualizacion del juego.
 */
public class Juego {
    public static final int ANCHO_PANEL = 900;
    public static final int ALTO_PANEL = 650;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Random random;
    private final Ranking ranking;
    private Buzo buzo;
    private final List<Enemigo> enemigos;
    private final List<Tesoro> tesoros;
    private final List<PowerUp> powerUps;
    private int tiempoSegundos;
    private int profundidad;
    private int nivel;
    private int ticks;
    private boolean terminado;
    private boolean partidaRegistrada;

    /**
     * Crea el modelo con listas vacias y ranking en memoria.
     */
    public Juego(Ranking ranking) {
        this.ranking = ranking;
        this.random = new Random();
        this.enemigos = new ArrayList<>();
        this.tesoros = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        reiniciar();
    }

    /**
     * Inicia una partida nueva para el jugador indicado.
     */
    public void iniciarPartida(String nombreJugador) {
        String nombre = nombreJugador == null || nombreJugador.trim().isEmpty()
                ? "Jugador"
                : nombreJugador.trim();
        Image imagenBuzo = CargadorRecursos.cargarImagen("/images/buzo.png");
        buzo = new Buzo(nombre, ANCHO_PANEL / 2 - 29, ALTO_PANEL - 120, imagenBuzo);
        enemigos.clear();
        tesoros.clear();
        powerUps.clear();
        tiempoSegundos = 0;
        profundidad = 0;
        nivel = 1;
        ticks = 0;
        terminado = false;
        partidaRegistrada = false;
    }

    /**
     * Actualiza el estado completo del juego.
     */
    public void actualizar() {
        if (terminado || buzo == null) {
            return;
        }
        ticks++;
        if (ticks % 60 == 0) {
            tiempoSegundos++;
            profundidad += 10;
            nivel = profundidad / 100 + 1;
        }

        generarObjetos();
        actualizarEntidades();
        verificarColisiones();
        limpiarObjetosFueraPantalla();

        if (buzo.getVidas() <= 0) {
            terminarPartida();
        }
    }

    /**
     * Genera enemigos, tesoros y power-ups con probabilidades simples.
     */
    public void generarObjetos() {
        // Estos intervalos controlan la dificultad base del juego.
        if (ticks % 55 == 0) {
            String tipo = random.nextBoolean() ? "medusa" : "pez_globo";
            Image imagen = CargadorRecursos.cargarImagen("/images/" + tipo + ".png");
            enemigos.add(new Enemigo(tipo, randomX(52), ALTO_PANEL + 20, 2 + nivel, imagen));
        }
        if (ticks % 45 == 0) {
            String tipo = random.nextInt(4) == 0 ? "cofre" : "perla";
            Image imagen = CargadorRecursos.cargarImagen("/images/" + tipo + ".png");
            tesoros.add(new Tesoro(tipo, randomX(42), ALTO_PANEL + 20, imagen));
        }
        if (ticks % 260 == 0) {
            Image imagen = CargadorRecursos.cargarImagen("/images/burbuja_oxigeno.png");
            powerUps.add(new PowerUp("oxigeno", randomX(40), ALTO_PANEL + 20, imagen));
        }
    }

    /**
     * Detecta colisiones y aplica efectos.
     */
    public void verificarColisiones() {
        Iterator<Tesoro> iteradorTesoros = tesoros.iterator();
        while (iteradorTesoros.hasNext()) {
            Tesoro tesoro = iteradorTesoros.next();
            if (buzo.getBounds().intersects(tesoro.getBounds())) {
                buzo.sumarPuntos(tesoro.getValor());
                iteradorTesoros.remove();
            }
        }

        Iterator<PowerUp> iteradorPowerUps = powerUps.iterator();
        while (iteradorPowerUps.hasNext()) {
            PowerUp powerUp = iteradorPowerUps.next();
            if (buzo.getBounds().intersects(powerUp.getBounds())) {
                powerUp.aplicar(buzo);
                iteradorPowerUps.remove();
            }
        }

        Iterator<Enemigo> iteradorEnemigos = enemigos.iterator();
        while (iteradorEnemigos.hasNext()) {
            Enemigo enemigo = iteradorEnemigos.next();
            if (buzo.getBounds().intersects(enemigo.getBounds())) {
                buzo.perderVida(enemigo.getDano());
                iteradorEnemigos.remove();
            }
        }
    }

    /**
     * Reinicia el modelo sin iniciar una partida concreta.
     */
    public void reiniciar() {
        buzo = null;
        enemigos.clear();
        tesoros.clear();
        powerUps.clear();
        tiempoSegundos = 0;
        profundidad = 0;
        nivel = 1;
        ticks = 0;
        terminado = false;
        partidaRegistrada = false;
    }

    private void terminarPartida() {
        terminado = true;
        // Esta bandera evita que la misma partida se guarde varias veces
        // mientras el controlador muestra la pantalla de Game Over.
        if (!partidaRegistrada) {
            ranking.registrarPartida(new JugadorRegistro(
                    buzo.getNombreJugador(),
                    buzo.getPuntaje(),
                    tiempoSegundos,
                    profundidad,
                    nivel,
                    LocalDateTime.now().format(FORMATO_FECHA)));
            partidaRegistrada = true;
        }
    }

    private void actualizarEntidades() {
        for (Enemigo enemigo : enemigos) {
            enemigo.setVelocidad(2 + nivel);
            enemigo.actualizar();
        }
        for (Tesoro tesoro : tesoros) {
            tesoro.actualizar();
        }
        for (PowerUp powerUp : powerUps) {
            powerUp.actualizar();
        }
    }

    private void limpiarObjetosFueraPantalla() {
        enemigos.removeIf(enemigo -> enemigo.getY() + enemigo.getAlto() < 40);
        tesoros.removeIf(tesoro -> tesoro.getY() + tesoro.getAlto() < 40);
        powerUps.removeIf(powerUp -> powerUp.getY() + powerUp.getAlto() < 40);
    }

    private int randomX(int anchoObjeto) {
        return random.nextInt(Math.max(1, ANCHO_PANEL - anchoObjeto));
    }

    public Buzo getBuzo() {
        return buzo;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public List<Tesoro> getTesoros() {
        return tesoros;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public int getNivel() {
        return nivel;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public Ranking getRanking() {
        return ranking;
    }
}
