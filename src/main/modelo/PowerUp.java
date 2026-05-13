package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Power-up que aplica un beneficio al buzo.
 */
public class PowerUp extends EntidadJuego {
    private final String tipo;

    /**
     * Crea una burbuja de oxigeno.
     */
    public PowerUp(String tipo, int x, int y, Image imagen) {
        super(x, y, 40, 40, 3, imagen);
        this.tipo = tipo;
    }

    @Override
    public void actualizar() {
        y -= velocidad;
    }

    /**
     * Aplica el efecto del power-up al buzo.
     */
    public void aplicar(Buzo buzo) {
        if ("oxigeno".equals(tipo)) {
            buzo.recuperarVida();
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
            return;
        }
        g.setColor(new Color(160, 235, 255));
        g.fillOval(x, y, ancho, alto);
        g.setColor(Color.BLUE);
        g.drawString("O2", x + 12, y + 25);
    }

    public String getTipo() {
        return tipo;
    }
}
