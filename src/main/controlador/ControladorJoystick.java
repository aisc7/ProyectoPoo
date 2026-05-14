package controlador;

import modelo.Buzo;
import modelo.Juego;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Controlador principal para joystick o gamepad usando JInput.
 */
public class ControladorJoystick implements ControladorEntrada {
    private static final float ZONA_MUERTA = 0.25f;
    private Controller joystick;
    private Component ejeX;
    private Component ejeY;
    private Component pov;
    private boolean conectado;

    /**
     * Busca un joystick compatible al crear el controlador.
     */
    public ControladorJoystick() {
        buscarJoystickCompatible();
    }

    /**
     * Indica si se selecciono un joystick compatible.
     */
    public boolean estaConectado() {
        return conectado;
    }

    /**
     * Lee el joystick en cada tick y mueve el buzo si hay entrada valida.
     */
    @Override
    public void actualizarMovimiento(Buzo buzo) {
        if (!conectado || buzo == null) {
            return;
        }

        try {
            if (!joystick.poll()) {
                conectado = false;
                System.out.println("No se detectó joystick. Se usará teclado.");
                return;
            }

            int dx = calcularMovimientoEje(ejeX, buzo.getVelocidad());
            int dy = calcularMovimientoEje(ejeY, buzo.getVelocidad());
            int[] movimientoPov = calcularMovimientoPov(buzo.getVelocidad());

            if (movimientoPov[0] != 0 || movimientoPov[1] != 0) {
                dx = movimientoPov[0];
                dy = movimientoPov[1];
            }

            if (dx != 0 || dy != 0) {
                buzo.mover(dx, dy, Juego.ANCHO_PANEL, Juego.ALTO_PANEL);
            }
        } catch (RuntimeException e) {
            conectado = false;
            System.out.println("No se detectó joystick. Se usará teclado.");
        }
    }

    private void buscarJoystickCompatible() {
        try {
            Controller[] controles = ControllerEnvironment.getDefaultEnvironment().getControllers();
            if (seleccionarPorTipo(controles, Controller.Type.GAMEPAD)
                    || seleccionarPorTipo(controles, Controller.Type.STICK)
                    || seleccionarUnknownUtil(controles)) {
                return;
            }
            System.out.println("No se detectó joystick. Se usará teclado.");
        } catch (Throwable e) {
            System.out.println("No se detectó joystick. Se usará teclado.");
        }
    }

    private boolean seleccionarPorTipo(Controller[] controles, Controller.Type tipoBuscado) {
        for (Controller control : controles) {
            if (esDispositivoNoJuego(control)) {
                continue;
            }
            if (control.getType() == tipoBuscado && seleccionarSiTieneMovimiento(control)) {
                return true;
            }
        }
        return false;
    }

    private boolean seleccionarUnknownUtil(Controller[] controles) {
        for (Controller control : controles) {
            if (esDispositivoNoJuego(control)) {
                continue;
            }
            if (control.getType() == Controller.Type.UNKNOWN && seleccionarSiTieneMovimiento(control)) {
                return true;
            }
        }
        return false;
    }

    private boolean seleccionarSiTieneMovimiento(Controller control) {
        ComponentesMovimiento componentesMovimiento = buscarComponentesMovimiento(control.getComponents());
        if (!componentesMovimiento.tieneMovimiento()) {
            return false;
        }
        joystick = control;
        ejeX = componentesMovimiento.ejeX;
        ejeY = componentesMovimiento.ejeY;
        pov = componentesMovimiento.pov;
        conectado = true;
        System.out.println("Joystick conectado: " + control.getName());
        return true;
    }

    private ComponentesMovimiento buscarComponentesMovimiento(Component[] componentes) {
        ComponentesMovimiento resultado = new ComponentesMovimiento();
        for (Component componente : componentes) {
            Component.Identifier identificador = componente.getIdentifier();
            if (identificador == Component.Identifier.Axis.X && resultado.ejeX == null) {
                resultado.ejeX = componente;
            } else if (identificador == Component.Identifier.Axis.Y && resultado.ejeY == null) {
                resultado.ejeY = componente;
            } else if (identificador == Component.Identifier.Axis.POV && resultado.pov == null) {
                resultado.pov = componente;
            }
        }
        return resultado;
    }

    private int calcularMovimientoEje(Component componente, int velocidad) {
        if (componente == null) {
            return 0;
        }
        float valor = componente.getPollData();
        if (Math.abs(valor) < ZONA_MUERTA) {
            return 0;
        }
        return valor < 0 ? -velocidad : velocidad;
    }

    private int[] calcularMovimientoPov(int velocidad) {
        int[] movimiento = {0, 0};
        if (pov == null) {
            return movimiento;
        }

        float valor = pov.getPollData();
        if (valor == Component.POV.OFF || valor == Component.POV.CENTER) {
            return movimiento;
        }

        if (cerca(valor, Component.POV.UP)
                || cerca(valor, Component.POV.UP_RIGHT)
                || cerca(valor, Component.POV.UP_LEFT)) {
            movimiento[1] = -velocidad;
        }
        if (cerca(valor, Component.POV.DOWN)
                || cerca(valor, Component.POV.DOWN_RIGHT)
                || cerca(valor, Component.POV.DOWN_LEFT)) {
            movimiento[1] = velocidad;
        }
        if (cerca(valor, Component.POV.LEFT)
                || cerca(valor, Component.POV.DOWN_LEFT)
                || cerca(valor, Component.POV.UP_LEFT)) {
            movimiento[0] = -velocidad;
        }
        if (cerca(valor, Component.POV.RIGHT)
                || cerca(valor, Component.POV.UP_RIGHT)
                || cerca(valor, Component.POV.DOWN_RIGHT)) {
            movimiento[0] = velocidad;
        }
        return movimiento;
    }

    private boolean esDispositivoNoJuego(Controller control) {
        String tipo = texto(control.getType());
        String nombre = texto(control.getName());
        return control.getType() == Controller.Type.MOUSE
                || control.getType() == Controller.Type.KEYBOARD
                || tipo.contains("MOUSE")
                || tipo.contains("KEYBOARD")
                || nombre.contains("MOUSE")
                || nombre.contains("KEYBOARD")
                || nombre.contains("TOUCHPAD")
                || nombre.contains("TRACKPAD");
    }

    private boolean cerca(float valor, float esperado) {
        return Math.abs(valor - esperado) < 0.03f;
    }

    private String texto(Object valor) {
        return String.valueOf(valor).trim().toUpperCase();
    }

    private static class ComponentesMovimiento {
        private Component ejeX;
        private Component ejeY;
        private Component pov;

        private boolean tieneMovimiento() {
            return (ejeX != null && ejeY != null) || pov != null;
        }
    }
}
