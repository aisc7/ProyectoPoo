package vista;

import util.CargadorRecursos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.GridLayout;

/**
 * Pantalla inicial con datos del proyecto y accesos principales.
 */
public class PanelBienvenida extends JPanel {
    private final JButton botonIniciar;
    private final JButton botonInstrucciones;
    private final Image fondo;
    private final Image logo;

    /**
     * Crea la pantalla de bienvenida.
     */
    public PanelBienvenida() {
        setLayout(new BorderLayout());
        fondo = CargadorRecursos.cargarImagen("/images/fondo_bienvenida.png");
        logo = CargadorRecursos.cargarImagen("/images/logo_uam.png");

        JLabel titulo = new JLabel("Tesoros del Abismo", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 42));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(5, 1, 8, 8));
        panelCentro.setOpaque(false);
        panelCentro.add(etiqueta("Materia: Programacion Orientada a Objetos"));
        // TODO: Reemplazar por nombres reales de integrantes.
        panelCentro.add(etiqueta("Integrantes: Nombre 1 - Nombre 2 - Nombre 3"));
        panelCentro.add(etiqueta("Universidad Autonoma de Manizales"));
        panelCentro.add(etiqueta("Recolecta tesoros y sobrevive al descenso"));

        JPanel botones = new JPanel();
        botones.setOpaque(false);
        botonIniciar = new JButton("Iniciar");
        botonInstrucciones = new JButton("Instrucciones");
        botones.add(botonIniciar);
        botones.add(botonInstrucciones);
        panelCentro.add(botones);
        add(panelCentro, BorderLayout.CENTER);
    }

    private JLabel etiqueta(String texto) {
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(16, 75, 120));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.drawString("TODO: colocar fondo_bienvenida.png", 30, 580);
        }
        if (logo != null) {
            g.drawImage(logo, 30, 30, 100, 80, null);
        } else {
            g.setColor(Color.WHITE);
            g.drawRect(30, 30, 100, 80);
            g.drawString("Logo UAM", 48, 75);
        }
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonInstrucciones() {
        return botonInstrucciones;
    }
}
