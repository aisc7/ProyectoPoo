package controlador;

import modelo.Juego;
import modelo.Ranking;
import util.ReproductorSonido;
import vista.VentanaPrincipal;

/**
 * Controla la navegacion entre pantallas de la aplicacion.
 */
public class ControladorVentanas {
    private final VentanaPrincipal ventana;
    private final Juego juego;
    private final ControladorJuego controladorJuego;
    private final ReproductorSonido reproductorSonido;

    /**
     * Registra eventos de botones y crea el modelo compartido.
     */
    public ControladorVentanas(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.juego = new Juego(new Ranking());
        this.controladorJuego = new ControladorJuego(juego, ventana);
        this.reproductorSonido = new ReproductorSonido();
        registrarEventos();
        ventana.mostrarPanel(VentanaPrincipal.BIENVENIDA);
        reproductorSonido.reproducir("/sounds/inicio.wav");
    }

    private void registrarEventos() {
        ventana.getPanelBienvenida().getBotonIniciar().addActionListener(e -> {
            ventana.getPanelNombreJugador().limpiar();
            ventana.mostrarPanel(VentanaPrincipal.NOMBRE);
            ventana.getPanelNombreJugador().getCampoNombre().requestFocusInWindow();
        });

        ventana.getPanelBienvenida().getBotonInstrucciones()
                .addActionListener(e -> ventana.mostrarPanel(VentanaPrincipal.INSTRUCCIONES));

        ventana.getPanelInstrucciones().getBotonVolver()
                .addActionListener(e -> ventana.mostrarPanel(VentanaPrincipal.BIENVENIDA));

        ventana.getPanelNombreJugador().getBotonEmpezar().addActionListener(e -> empezarPartida());
        ventana.getPanelNombreJugador().getCampoNombre().addActionListener(e -> empezarPartida());

        ventana.getPanelGameOver().getBotonVolverInicio().addActionListener(e -> {
            controladorJuego.detener();
            juego.reiniciar();
            ventana.mostrarPanel(VentanaPrincipal.BIENVENIDA);
        });
    }

    private void empezarPartida() {
        reproductorSonido.detener();
        controladorJuego.iniciar(ventana.getPanelNombreJugador().getNombreJugador());
        ventana.mostrarPanel(VentanaPrincipal.JUEGO);
        ventana.getPanelJuego().requestFocusInWindow();
    }
}
