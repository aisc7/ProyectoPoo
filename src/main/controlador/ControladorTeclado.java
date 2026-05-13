package controlador;

import modelo.Buzo;
import modelo.Juego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controla las teclas presionadas para mover el buzo.
 */
public class ControladorTeclado extends KeyAdapter {
    private boolean arriba;
    private boolean abajo;
    private boolean izquierda;
    private boolean derecha;

    @Override
    public void keyPressed(KeyEvent e) {
        cambiarEstado(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        cambiarEstado(e.getKeyCode(), false);
    }

    /**
     * Aplica el movimiento acumulado al buzo.
     */
    public void actualizarMovimiento(Buzo buzo) {
        if (buzo == null) {
            return;
        }
        int dx = 0;
        int dy = 0;
        int velocidad = buzo.getVelocidad();
        if (izquierda) {
            dx -= velocidad;
        }
        if (derecha) {
            dx += velocidad;
        }
        if (arriba) {
            dy -= velocidad;
        }
        if (abajo) {
            dy += velocidad;
        }
        buzo.mover(dx, dy, Juego.ANCHO_PANEL, Juego.ALTO_PANEL);
    }

    private void cambiarEstado(int codigoTecla, boolean activo) {
        if (codigoTecla == KeyEvent.VK_UP) {
            arriba = activo;
        } else if (codigoTecla == KeyEvent.VK_DOWN) {
            abajo = activo;
        } else if (codigoTecla == KeyEvent.VK_LEFT) {
            izquierda = activo;
        } else if (codigoTecla == KeyEvent.VK_RIGHT) {
            derecha = activo;
        }
    }
}
