package vista;

import modelo.JugadorRegistro;
import modelo.Ranking;
import util.CargadorRecursos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

/**
 * Pantalla final con resultado y ranking Top 3.
 */
public class PanelGameOver extends JPanel {
    private final JButton botonVolverInicio;
    private final JLabel resumen;
    private final JPanel panelRanking;
    private final Image fondo;

    /**
     * Crea la pantalla de fin de juego.
     */
    public PanelGameOver() {
        setLayout(new BorderLayout());
        fondo = CargadorRecursos.cargarImagen("/images/fondo_gameover.png");

        JLabel titulo = new JLabel("Game Over", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 42));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(2, 1));
        centro.setOpaque(false);
        resumen = new JLabel("", JLabel.CENTER);
        resumen.setForeground(Color.WHITE);
        resumen.setFont(new Font("Arial", Font.BOLD, 18));
        panelRanking = new JPanel(new GridLayout(4, 1, 5, 5));
        panelRanking.setOpaque(false);
        centro.add(resumen);
        centro.add(panelRanking);
        add(centro, BorderLayout.CENTER);

        botonVolverInicio = new JButton("Volver al inicio");
        add(botonVolverInicio, BorderLayout.SOUTH);
    }

    /**
     * Actualiza datos finales de la partida.
     */
    public void mostrarResultados(String nombre, int puntaje, int tiempo, int profundidad, Ranking ranking) {
        resumen.setText("Jugador: " + nombre + " | Puntaje: " + puntaje
                + " | Tiempo: " + tiempo + "s | Profundidad: " + profundidad + "m");
        panelRanking.removeAll();
        panelRanking.add(etiqueta("Ranking Top 3"));
        List<JugadorRegistro> top = ranking.obtenerTop3();
        for (int i = 0; i < top.size(); i++) {
            JugadorRegistro registro = top.get(i);
            panelRanking.add(etiqueta((i + 1) + ". " + registro.getNombre()
                    + " - " + registro.getPuntaje() + " puntos"));
        }
        while (panelRanking.getComponentCount() < 4) {
            panelRanking.add(etiqueta("-"));
        }
        revalidate();
        repaint();
    }

    private JLabel etiqueta(String texto) {
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(8, 30, 55));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public JButton getBotonVolverInicio() {
        return botonVolverInicio;
    }
}
