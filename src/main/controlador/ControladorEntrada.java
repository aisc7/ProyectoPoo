package controlador;

import modelo.Buzo;

/**
 * Contrato comun para entradas que pueden mover al buzo.
 */
public interface ControladorEntrada {

    /**
     * Actualiza el movimiento del buzo segun el dispositivo de entrada.
     *
     * @param buzo personaje principal del juego.
     */
    void actualizarMovimiento(Buzo buzo);
}
