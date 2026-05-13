package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Criatura marina que resta vida al buzo cuando colisiona.
 */
public class Enemigo extends EntidadJuego {
    private final String tipo;
    private final int dano;

    /**
     * Crea un enemigo con tipo y dano.
     */
    public Enemigo(String tipo, int x, int y, int velocidad, Image imagen) {
        super(x, y, 52, 52, velocidad, imagen);
        this.tipo = tipo;
        this.dano = 1;
    }

    @Override
    public void actualizar() {
        actualizarMovimiento();
    }

    /**
     * Mueve el enemigo hacia arriba para simular descenso del buzo.
     */
    public void actualizarMovimiento() {
        y -= velocidad;
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
            return;
        }
        g.setColor("medusa".equals(tipo) ? new Color(216, 86, 170) : new Color(238, 142, 65));
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawString(tipo, x + 5, y + 30);
    }

    public String getTipo() {
        return tipo;
    }

    public int getDano() {
        return dano;
    }
}
