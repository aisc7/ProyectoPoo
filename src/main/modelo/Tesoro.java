package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Objeto recolectable que suma puntos.
 */
public class Tesoro extends EntidadJuego {
    private final String tipo;
    private final int valor;

    /**
     * Crea un tesoro de tipo perla o cofre.
     */
    public Tesoro(String tipo, int x, int y, Image imagen) {
        super(x, y, 42, 42, 3, imagen);
        this.tipo = tipo;
        this.valor = "cofre".equals(tipo) ? 30 : 10;
    }

    @Override
    public void actualizar() {
        y -= velocidad;
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
            return;
        }
        g.setColor("cofre".equals(tipo) ? new Color(154, 91, 44) : Color.WHITE);
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawString(tipo, x + 4, y + 25);
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }
}
