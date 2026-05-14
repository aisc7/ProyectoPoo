package controlador;

import modelo.Buzo;

/**
 * Contrato comun para entradas que pueden mover al buzo.
 */
public interface ControladorEntrada {

    /**
     * Actualiza el movimiento del buzo segun el dispositivo de entrada.
     */
    void actualizarMovimiento(Buzo buzo);
}
