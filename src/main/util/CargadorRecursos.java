package util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Centraliza la carga segura de imagenes y sonidos desde resources.
 */
public class CargadorRecursos {

    private CargadorRecursos() {
    }

    /**
     * Carga una imagen desde el classpath. Devuelve null si no existe.
     *
     * @param ruta ejemplo: /images/buzo.png
     */
    public static Image cargarImagen(String ruta) {
        URL recurso = CargadorRecursos.class.getResource(ruta);
        if (recurso == null) {
            System.out.println("Imagen no encontrada: " + ruta);
            return null;
        }
        return new ImageIcon(recurso).getImage();
    }

    /**
     * Carga un sonido .wav desde el classpath. Devuelve null si no existe o falla.
     *
     * @param ruta ejemplo: /sounds/inicio.wav
     */
    public static AudioInputStream cargarSonido(String ruta) {
        URL recurso = CargadorRecursos.class.getResource(ruta);
        if (recurso == null) {
            System.out.println("Sonido no encontrado: " + ruta);
            return null;
        }
        try {
            return AudioSystem.getAudioInputStream(recurso);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el sonido: " + ruta);
            return null;
        }
    }
}
