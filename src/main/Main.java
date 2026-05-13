import controlador.ControladorVentanas;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de Tesoros del Abismo.
 */
public class Main {

    /**
     * Inicia la aplicacion Swing.
     *
     * @param args argumentos de consola no usados.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            new ControladorVentanas(ventana);
            ventana.setVisible(true);
        });
    }
}
