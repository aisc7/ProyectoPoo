package vista;

import modelo.Buzo;
import modelo.Enemigo;
import modelo.Juego;
import modelo.PowerUp;
import modelo.Tesoro;
import util.CargadorRecursos;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Panel encargado solo de dibujar el estado del juego.
 */
public class PanelJuego extends JPanel {
    private Juego juego;
    private final Image fondo;

    /**
     * Crea el panel de juego.
     */
    public PanelJuego() {
        setFocusable(true);
        fondo = CargadorRecursos.cargarImagen("/images/fondo_juego.png");
    }

    /**
     * Asocia el modelo que debe dibujar.
     */
    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarFondo(g);
        if (juego == null || juego.getBuzo() == null) {
            return;
        }
        dibujarHud(g);
        for (Tesoro tesoro : juego.getTesoros()) {
            tesoro.dibujar(g);
        }
        for (PowerUp powerUp : juego.getPowerUps()) {
            powerUp.dibujar(g);
        }
        for (Enemigo enemigo : juego.getEnemigos()) {
            enemigo.dibujar(g);
        }
        juego.getBuzo().dibujar(g);
    }

    private void dibujarFondo(Graphics g) {
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
            return;
        }
        g.setColor(new Color(6, 44, 79));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(15, 85, 130));
        for (int y = 0; y < getHeight(); y += 80) {
            g.drawLine(0, y, getWidth(), y + 30);
        }
    }

    private void dibujarHud(Graphics g) {
        Buzo buzo = juego.getBuzo();
        g.setColor(new Color(0, 0, 0, 140));
        g.fillRect(0, 0, getWidth(), 40);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Jugador: " + buzo.getNombreJugador(), 15, 25);
        g.drawString("Puntaje: " + buzo.getPuntaje(), 190, 25);
        g.drawString("Vidas: " + buzo.getVidas(), 310, 25);
        g.drawString("Tiempo: " + juego.getTiempoSegundos() + "s", 400, 25);
        g.drawString("Profundidad: " + juego.getProfundidad() + "m", 520, 25);
        g.drawString("Nivel: " + juego.getNivel(), 700, 25);
    }
}
