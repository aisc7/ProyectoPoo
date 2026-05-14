# Tesoros del Abismo

Videojuego 2D desarrollado en Java Swing con Programacion Orientada a Objetos y arquitectura MVC.

## Descripcion del juego

El jugador controla un buzo que desciende por el oceano para recolectar tesoros. Durante la partida aparecen criaturas marinas que deben evitarse y burbujas de oxigeno que permiten recuperar vida.

La profundidad aumenta con el tiempo. Cada 100 metros sube el nivel y la velocidad de los enemigos incrementa.

## Objetivo

Recolectar la mayor cantidad de perlas y cofres posible, conservar vidas y alcanzar la mayor profundidad antes de terminar la partida.

## Controles

Joystick o gamepad:

- Stick izquierdo: mover el buzo.
- Cruceta/D-pad: mover el buzo.

Teclado de respaldo:

- Flechas: mover el buzo.
- WASD: mover el buzo.

## Reglas

- Las perlas suman 10 puntos.
- Los cofres suman 30 puntos.
- Las criaturas marinas quitan vidas.
- La burbuja de oxigeno recupera 1 vida, hasta un maximo de 5.
- La profundidad aumenta con el tiempo.
- Cada 100 metros aumenta el nivel.
- Si las vidas llegan a 0, termina la partida.

## Ranking local

Las partidas terminadas se guardan en:

```text
resources/ranking/ranking.json
```

Si la carpeta o el archivo no existen, el juego los crea automaticamente. El ranking se guarda en JSON para que el historial sea facil de revisar y editar durante la entrega academica.

La pantalla de Game Over muestra el Top 3 ordenado por puntaje de mayor a menor.

El archivo `resources/ranking/ejemplo-ranking.json` contiene un registro de ejemplo. El juego usa solamente `ranking.json`.

## Requisitos

- JDK 17.
- Maven.
- Joystick o gamepad compatible con JInput.
- IntelliJ IDEA o un entorno compatible con proyectos Maven.

## Ejecucion

Compilar el proyecto:

```bash
mvn clean compile
```

Ejecutar el juego:

```bash
mvn exec:java
```

Tambien se puede abrir el proyecto en IntelliJ IDEA como proyecto Maven y ejecutar la clase `Main`.

## Estructura MVC

- `modelo`: entidades, reglas, colisiones, puntaje, tiempo, profundidad, nivel y ranking.
- `vista`: pantallas Swing y dibujo del juego.
- `controlador`: teclado, joystick, botones, temporizador y flujo de pantallas.
- `util`: carga de recursos, reproduccion de sonidos y persistencia JSON del ranking.

## Recursos

Imagenes:

```text
resources/images
```

Sonidos:

```text
resources/sounds
```

Ranking:

```text
resources/ranking
```

Si falta una imagen, el juego dibuja una figura simple de respaldo. Si falta un sonido, la reproduccion se omite sin cerrar el juego.
