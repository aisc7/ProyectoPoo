package controlador;

import modelo.Buzo;

/**
 * Controlador opcional para joystick o gamepad.
 *
 * <p>Esta version no depende de librerias externas para conservar la
 * compilacion simple del proyecto. Sirve como punto de extension seguro para
 * integrar JInput mas adelante.</p>
 */
public class ControladorJoystick implements ControladorEntrada {
    private final boolean conectado;

    /**
     * Crea un controlador de joystick seguro sin dependencia externa.
     */
    public ControladorJoystick() {
        // TODO: Integrar JInput aqui si el proyecto adopta Maven, Gradle o agrega el .jar manualmente.
        // TODO: Con JInput se deben buscar controles Controller.Type.GAMEPAD o Controller.Type.STICK.
        // TODO: Leer ejes X/Y con polling, aplicar zona muerta de 0.25 y mover el buzo.
        conectado = false;
        System.out.println("Joystick no configurado. Se usará teclado.");
    }

    /**
     * Indica si hay joystick real disponible para esta implementacion.
     *
     * @return false mientras no se integre una libreria como JInput.
     */
    public boolean estaConectado() {
        return conectado;
    }

    /**
     * No mueve el buzo si el joystick no esta configurado.
     *
     * @param buzo jugador que se moveria con joystick.
     */
    @Override
    public void actualizarMovimiento(Buzo buzo) {
        if (!estaConectado() || buzo == null) {
            return;
        }

        // TODO: Implementacion futura con JInput:
        // 1. joystick.poll()
        // 2. leer eje X y eje Y
        // 3. ignorar valores entre -0.25 y 0.25
        // 4. calcular dx/dy con buzo.getVelocidad()
        // 5. buzo.mover(dx, dy, Juego.ANCHO_PANEL, Juego.ALTO_PANEL)
    }
}
