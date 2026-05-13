package vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Pantalla para ingresar el nombre del jugador.
 */
public class PanelNombreJugador extends JPanel {
    private final JTextField campoNombre;
    private final JButton botonEmpezar;

    /**
     * Crea el formulario de nombre.
     */
    public PanelNombreJugador() {
        setLayout(new GridBagLayout());
        setBackground(new Color(10, 70, 110));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titulo = new JLabel("Nombre del jugador");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        add(titulo, gbc);

        gbc.gridy++;
        campoNombre = new JTextField(22);
        add(campoNombre, gbc);

        gbc.gridy++;
        botonEmpezar = new JButton("Empezar partida");
        add(botonEmpezar, gbc);
    }

    public String getNombreJugador() {
        return campoNombre.getText();
    }

    public void limpiar() {
        campoNombre.setText("");
    }

    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JButton getBotonEmpezar() {
        return botonEmpezar;
    }
}
