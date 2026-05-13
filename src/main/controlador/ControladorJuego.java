package controlador;

import modelo.Buzo;
import modelo.Juego;
import util.ReproductorSonido;
import vista.PanelGameOver;
import vista.PanelJuego;
import vista.VentanaPrincipal;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Conecta el modelo Juego con el panel de dibujo y el timer principal.
 */
public class ControladorJuego {
    private final Juego juego;
    private final PanelJuego panelJuego;
    private final PanelGameOver panelGameOver;
    private final VentanaPrincipal ventana;
    private final ControladorTeclado controladorTeclado;
    private final ReproductorSonido musica;
    private final ReproductorSonido efectos;
    private Timer timer;
    private Thread hiloAnimacion;
    private volatile boolean animacionActiva;

    /**
     * Crea el controlador del ciclo de juego.
     */
    public ControladorJuego(Juego juego, VentanaPrincipal ventana) {
        this.juego = juego;
        this.ventana = ventana;
        this.panelJuego = ventana.getPanelJuego();
        this.panelGameOver = ventana.getPanelGameOver();
        this.controladorTeclado = new ControladorTeclado();
        this.musica = new ReproductorSonido();
        this.efectos = new ReproductorSonido();
        panelJuego.setJuego(juego);
        panelJuego.addKeyListener(controladorTeclado);
    }

    /**
     * Inicia timer, sonido y thread secundario de animacion.
     */
    public void iniciar(String nombreJugador) {
        detener();
        juego.iniciarPartida(nombreJugador);
        musica.reproducirLoop("/sounds/juego.wav");
        iniciarHiloAnimacion();

        timer = new Timer(16, e -> {
            controladorTeclado.actualizarMovimiento(juego.getBuzo());
            juego.actualizar();
            panelJuego.repaint();
            if (juego.isTerminado()) {
                finalizarPartida();
            }
        });
        timer.start();
        panelJuego.requestFocusInWindow();
    }

    /**
     * Detiene timer, musica e hilo secundario.
     */
    public void detener() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        animacionActiva = false;
        if (hiloAnimacion != null) {
            hiloAnimacion.interrupt();
            hiloAnimacion = null;
        }
        musica.detener();
    }

    private void finalizarPartida() {
        detener();
        efectos.reproducir("/sounds/gameover.wav");
        Buzo buzo = juego.getBuzo();
        panelGameOver.mostrarResultados(
                buzo.getNombreJugador(),
                buzo.getPuntaje(),
                juego.getTiempoSegundos(),
                juego.getProfundidad(),
                juego.getRanking());
        ventana.mostrarPanel(VentanaPrincipal.GAME_OVER);
    }

    private void iniciarHiloAnimacion() {
        animacionActiva = true;
        hiloAnimacion = new Thread(() -> {
            boolean visible = true;
            while (animacionActiva) {
                Buzo buzo = juego.getBuzo();
                if (buzo != null && buzo.isEfectoPowerActivo()) {
                    boolean estado = visible;
                    SwingUtilities.invokeLater(() -> {
                        buzo.setEfectoPowerActivo(estado);
                        panelJuego.repaint();
                    });
                    visible = !visible;
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "AnimacionBuzoPowerUp");
        hiloAnimacion.start();
    }
}
