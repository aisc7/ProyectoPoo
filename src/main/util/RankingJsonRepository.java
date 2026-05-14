package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import modelo.JugadorRegistro;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Repositorio encargado de guardar y leer el ranking local del juego.
 * El archivo se almacena en resources/ranking/ranking.json para facilitar
 * la revision academica del historial de partidas.
 *
 * <p>JSON es el formato del archivo. Gson es la libreria que convierte entre
 * objetos Java, como JugadorRegistro, y texto JSON.</p>
 *
 * <p>Si la carpeta o el archivo no existen, se crean automaticamente.</p>
 */
public class RankingJsonRepository {
    private static final Path RUTA_RANKING = Paths.get("resources", "ranking", "ranking.json");
    private static final Type TIPO_LISTA = new TypeToken<List<JugadorRegistro>>() {
    }.getType();

    private final Gson conversorJson;

    /**
     * Crea un repositorio con salida JSON indentada para facilitar su lectura.
     */
    public RankingJsonRepository() {
        // Gson no es el archivo JSON: es la herramienta que transforma
        // listas y objetos Java en texto JSON, y tambien lee ese texto.
        conversorJson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Crea la carpeta y el archivo del ranking si todavia no existen.
     *
     * <p>Este archivo se guarda de forma local durante la ejecucion del
     * proyecto en IntelliJ/Maven. Para una version empaquetada en .jar,
     * convendria moverlo a una carpeta externa como data/.</p>
     */
    public void inicializarArchivo() {
        try {
            Path carpeta = RUTA_RANKING.getParent();
            if (carpeta != null && !Files.exists(carpeta)) {
                Files.createDirectories(carpeta);
            }
            if (!Files.exists(RUTA_RANKING)) {
                Files.writeString(RUTA_RANKING, "[]", StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el ranking local.");
        }
    }

    /**
     * Carga el historial completo desde el archivo JSON.
     *
     * @return lista vacia si el archivo esta vacio, no existe o tiene un error.
     */
    public List<JugadorRegistro> cargarHistorial() {
        inicializarArchivo();
        try {
            String contenido = Files.readString(RUTA_RANKING, StandardCharsets.UTF_8).trim();
            if (contenido.isEmpty()) {
                return new ArrayList<>();
            }
            // fromJson convierte el texto del archivo en una lista de JugadorRegistro.
            List<JugadorRegistro> historial = conversorJson.fromJson(contenido, TIPO_LISTA);
            return historial == null ? new ArrayList<>() : historial;
        } catch (IOException | JsonSyntaxException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Guarda el historial completo en formato JSON.
     */
    public void guardarHistorial(List<JugadorRegistro> historial) {
        inicializarArchivo();
        try {
            // toJson convierte la lista de objetos Java en texto JSON para guardarlo.
            String json = conversorJson.toJson(historial == null ? new ArrayList<>() : historial, TIPO_LISTA);
            Files.writeString(RUTA_RANKING, json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el ranking local.");
        }
    }

    /**
     * Agrega un registro nuevo al historial persistente.
     */
    public void agregarRegistro(JugadorRegistro registro) {
        if (registro == null) {
            return;
        }
        List<JugadorRegistro> historial = cargarHistorial();
        historial.add(registro);
        guardarHistorial(historial);
    }

    /**
     * Devuelve los tres mejores puntajes del historial local.
     *
     * <p>El ranking se ordena por puntaje porque ese es el resultado principal
     * de la partida. Solo se muestran tres registros para mantener clara la
     * pantalla de Game Over.</p>
     */
    public List<JugadorRegistro> obtenerTop3() {
        List<JugadorRegistro> historial = cargarHistorial();
        historial.sort(Comparator.comparingInt(JugadorRegistro::getPuntaje).reversed());
        return new ArrayList<>(historial.subList(0, Math.min(3, historial.size())));
    }
}
