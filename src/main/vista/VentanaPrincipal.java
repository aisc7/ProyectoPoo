package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 * Ventana principal que navega entre pantallas usando CardLayout.
 */
public class VentanaPrincipal extends JFrame {
    public static final String BIENVENIDA = "bienvenida";
    public static final String INSTRUCCIONES = "instrucciones";
    public static final String NOMBRE = "nombre";
    public static final String JUEGO = "juego";
    public static final String GAME_OVER = "gameOver";

    private final CardLayout cardLayout;
    private final JPanel contenedor;
    private final PanelBienvenida panelBienvenida;
    private final PanelInstrucciones panelInstrucciones;
    private final PanelNombreJugador panelNombreJugador;
    private final PanelJuego panelJuego;
    private final PanelGameOver panelGameOver;

    /**
     * Configura la ventana y registra sus paneles.
     */
    public VentanaPrincipal() {
        setTitle("Tesoros del Abismo");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        panelBienvenida = new PanelBienvenida();
        panelInstrucciones = new PanelInstrucciones();
        panelNombreJugador = new PanelNombreJugador();
        panelJuego = new PanelJuego();
        panelGameOver = new PanelGameOver();

        contenedor.add(panelBienvenida, BIENVENIDA);
        contenedor.add(panelInstrucciones, INSTRUCCIONES);
        contenedor.add(panelNombreJugador, NOMBRE);
        contenedor.add(panelJuego, JUEGO);
        contenedor.add(panelGameOver, GAME_OVER);
        add(contenedor);
    }

    /**
     * Muestra una pantalla por nombre.
     */
    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(contenedor, nombrePanel);
    }

    public PanelBienvenida getPanelBienvenida() {
        return panelBienvenida;
    }

    public PanelInstrucciones getPanelInstrucciones() {
        return panelInstrucciones;
    }

    public PanelNombreJugador getPanelNombreJugador() {
        return panelNombreJugador;
    }

    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    public PanelGameOver getPanelGameOver() {
        return panelGameOver;
    }
}
