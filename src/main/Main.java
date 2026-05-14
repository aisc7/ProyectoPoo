import controlador.ControladorVentanas;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;
import java.io.File;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        configurarRutaNativaJInput();
        silenciarLogsJInput();
        System.out.println("Juego iniciado correctamente.");
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            new ControladorVentanas(ventana);
            ventana.setVisible(true);
        });
    }

    /**
     * Configura la ruta relativa donde Maven extrae los nativos de JInput.
     */
    private static void configurarRutaNativaJInput() {
        File carpetaNativos = new File("target/natives");
        if (carpetaNativos.isDirectory()) {
            String ruta = carpetaNativos.getAbsolutePath();
            System.setProperty("net.java.games.input.librarypath", ruta);
            System.setProperty("java.library.path", ruta);
        }
    }

    /**
     * Evita mensajes internos extensos de JInput en la consola.
     */
    private static void silenciarLogsJInput() {
        Logger loggerRaiz = Logger.getLogger("");
        loggerRaiz.setLevel(Level.SEVERE);
        for (Handler handler : loggerRaiz.getHandlers()) {
            handler.setLevel(Level.SEVERE);
        }

        Logger loggerJInput = Logger.getLogger("net.java.games.input");
        loggerJInput.setUseParentHandlers(false);
        loggerJInput.setLevel(Level.OFF);
    }
}
