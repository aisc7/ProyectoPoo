# Tesoros del Abismo

Base inicial de un videojuego 2D en Java con Swing, Programacion Orientada a Objetos y arquitectura MVC.

El jugador controla un buzo que desciende por el oceano, recolecta tesoros y evita criaturas marinas. La profundidad aumenta con el tiempo; cada 100 metros sube el nivel y los enemigos se vuelven mas rapidos.

## Como ejecutar en IntelliJ IDEA

1. Abrir esta carpeta como proyecto.
2. Marcar `src/main` como **Sources Root**.
3. Marcar `resources` como **Resources Root**.
4. Ejecutar la clase `Main`.

No se requieren dependencias externas ni motores de videojuegos.

## Controles

- Flecha izquierda: mover el buzo a la izquierda.
- Flecha derecha: mover el buzo a la derecha.
- Flecha arriba: mover el buzo hacia arriba.
- Flecha abajo: mover el buzo hacia abajo.

## Reglas

- El buzo inicia con 3 vidas.
- La perla suma 10 puntos.
- El cofre suma 30 puntos.
- La medusa quita 1 vida.
- El pez globo quita 1 vida.
- La burbuja de oxigeno recupera 1 vida, hasta un maximo de 5.
- Cada segundo aumenta la profundidad en 10 metros.
- Cada 100 metros aumenta el nivel.
- La velocidad de los enemigos aumenta segun el nivel.
- Si las vidas llegan a 0, la partida termina.
- Se puede volver a jugar sin cerrar la aplicacion.

## Estructura MVC

- `modelo`: contiene entidades, reglas, colisiones, puntaje, tiempo, profundidad, nivel y ranking.
- `vista`: contiene pantallas Swing y dibujo del juego.
- `controlador`: conecta botones, teclado, timer y flujo de pantallas.
- `util`: carga segura de recursos y reproduccion de sonidos `.wav`.

La vista no contiene la logica principal del juego. `Juego` es el modelo central y `ControladorJuego` ejecuta el ciclo con `javax.swing.Timer`.

## Recursos

Colocar imagenes en:

```text
resources/images/
```

Archivos esperados:

- `fondo_bienvenida.png`
- `fondo_juego.png`
- `fondo_gameover.png`
- `logo_uam.png`
- `buzo.png`
- `buzo_power.png`
- `perla.png`
- `cofre.png`
- `medusa.png`
- `pez_globo.png`
- `burbuja_oxigeno.png`
- `corazon.png`

Colocar sonidos en:

```text
resources/sounds/
```

Archivos esperados:

- `inicio.wav`
- `juego.wav`
- `tesoro.wav`
- `colision.wav`
- `oxigeno.wav`
- `gameover.wav`

Si falta una imagen, el juego dibuja figuras simples de respaldo. Si falta un sonido, el juego lo ignora y muestra un mensaje en consola.

## TODO para el equipo

- Reemplazar nombres de integrantes en `PanelBienvenida`.
- Agregar el logo definitivo de la UAM en `resources/images/logo_uam.png`.
- Agregar imagenes definitivas en `resources/images`.
- Agregar sonidos definitivos en `resources/sounds`.
- Ajustar dificultad en `Juego.generarObjetos()`.
- Opcional: implementar guardado del ranking en archivo `.txt`.
