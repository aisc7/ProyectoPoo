package vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Pantalla de reglas del juego.
 */
public class PanelInstrucciones extends JPanel {
    private final JButton botonVolver;

    /**
     * Crea la pantalla de instrucciones.
     */
    public PanelInstrucciones() {
        setLayout(new BorderLayout());
        setBackground(new Color(12, 87, 132));

        JLabel titulo = new JLabel("Instrucciones", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 34));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        JPanel reglas = new JPanel(new GridLayout(7, 1, 6, 6));
        reglas.setOpaque(false);
        reglas.add(etiqueta("- Mover al buzo con flechas"));
        reglas.add(etiqueta("- Recoger perlas y cofres para sumar puntos"));
        reglas.add(etiqueta("- Evitar medusas y peces globo"));
        reglas.add(etiqueta("- Recoger burbujas de oxigeno para recuperar vida"));
        reglas.add(etiqueta("- La profundidad aumenta con el tiempo"));
        reglas.add(etiqueta("- Cada 100 metros sube el nivel"));
        reglas.add(etiqueta("- Si las vidas llegan a 0, termina la partida"));
        add(reglas, BorderLayout.CENTER);

        botonVolver = new JButton("Volver");
        add(botonVolver, BorderLayout.SOUTH);
    }

    private JLabel etiqueta(String texto) {
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }
}
