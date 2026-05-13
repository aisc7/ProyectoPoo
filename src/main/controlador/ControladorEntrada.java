package controlador;

import modelo.Buzo;

/**
 * Contrato comun para cualquier entrada que pueda mover al buzo.
 */
public interface ControladorEntrada {

    /**
     * Actualiza el movimiento del buzo segun el estado de la entrada.
     *
     * @param buzo jugador que se debe mover.
     */
    void actualizarMovimiento(Buzo buzo);
}
