package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Clase base abstracta para todos los objetos visibles del juego.
 */
public abstract class EntidadJuego {
    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;
    protected int velocidad;
    protected Image imagen;

    /**
     * Crea una entidad con posicion, tamano, velocidad e imagen opcional.
     */
    public EntidadJuego(int x, int y, int ancho, int alto, int velocidad, Image imagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.imagen = imagen;
    }

    /**
     * Devuelve el rectangulo usado para detectar colisiones.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    /**
     * Dibuja la entidad. Si no hay imagen, usa una figura simple como respaldo.
     */
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
            return;
        }
        g.setColor(Color.CYAN);
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);
    }

    /**
     * Actualiza el estado de la entidad en cada tick del juego.
     */
    public abstract void actualizar();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
