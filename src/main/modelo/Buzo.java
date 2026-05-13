package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa al jugador controlado con el teclado.
 */
public class Buzo extends EntidadJuego {
    private static final int VIDA_MAXIMA = 5;
    private int vidas;
    private int puntaje;
    private final String nombreJugador;
    private boolean efectoPowerActivo;

    /**
     * Crea un buzo con 3 vidas y puntaje inicial en cero.
     */
    public Buzo(String nombreJugador, int x, int y, Image imagen) {
        super(x, y, 58, 58, 6, imagen);
        this.nombreJugador = nombreJugador;
        this.vidas = 3;
        this.puntaje = 0;
    }

    /**
     * Mueve el buzo dentro de los limites del panel.
     */
    public void mover(int dx, int dy, int limiteAncho, int limiteAlto) {
        x = Math.max(0, Math.min(limiteAncho - ancho, x + dx));
        y = Math.max(40, Math.min(limiteAlto - alto, y + dy));
    }

    public void sumarPuntos(int puntos) {
        puntaje += puntos;
    }

    public void perderVida(int cantidad) {
        vidas = Math.max(0, vidas - cantidad);
    }

    public void recuperarVida() {
        vidas = Math.min(VIDA_MAXIMA, vidas + 1);
        efectoPowerActivo = true;
    }

    @Override
    public void actualizar() {
        // El movimiento se controla desde ControladorTeclado.
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
            return;
        }
        g.setColor(efectoPowerActivo ? new Color(106, 255, 210) : new Color(35, 154, 225));
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.WHITE);
        g.drawString("Buzo", x + 12, y + 34);
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public boolean isEfectoPowerActivo() {
        return efectoPowerActivo;
    }

    public void setEfectoPowerActivo(boolean efectoPowerActivo) {
        this.efectoPowerActivo = efectoPowerActivo;
    }
}
