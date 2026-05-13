package util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Reproduce archivos .wav de forma segura.
 */
public class ReproductorSonido {
    private Clip clipActual;

    /**
     * Reproduce una vez un sonido .wav.
     */
    public void reproducir(String ruta) {
        detener();
        reproducirInterno(ruta, false);
    }

    /**
     * Reproduce un sonido en bucle.
     */
    public void reproducirLoop(String ruta) {
        detener();
        reproducirInterno(ruta, true);
    }

    /**
     * Detiene el sonido actual si existe.
     */
    public void detener() {
        if (clipActual != null) {
            clipActual.stop();
            clipActual.close();
            clipActual = null;
        }
    }

    private void reproducirInterno(String ruta, boolean loop) {
        try {
            AudioInputStream audio = CargadorRecursos.cargarSonido(ruta);
            if (audio == null) {
                return;
            }
            clipActual = AudioSystem.getClip();
            clipActual.open(audio);
            if (loop) {
                clipActual.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clipActual.start();
            }
        } catch (Exception e) {
            System.out.println("No se pudo reproducir el sonido: " + ruta);
        }
    }
}
