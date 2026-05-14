# Tesoros del Abismo

Videojuego 2D desarrollado en Java Swing con Programacion Orientada a Objetos y arquitectura MVC.

## Descripcion

El jugador controla un buzo que desciende por el oceano para recolectar tesoros. Durante la partida aparecen criaturas marinas que deben evitarse y burbujas de oxigeno que permiten recuperar vida.

La profundidad aumenta con el tiempo. Cada 100 metros sube el nivel y la velocidad de los enemigos incrementa.

## Objetivo del juego

Recolectar la mayor cantidad de perlas y cofres posible, conservar vidas y alcanzar la mayor profundidad antes de terminar la partida.

## Controles

Joystick o gamepad:

- Stick izquierdo: mover el buzo.
- Cruceta/D-pad: mover el buzo.

Teclado de respaldo:

- Flechas: mover el buzo.
- WASD: mover el buzo.

## Reglas

- El buzo inicia con 3 vidas.
- La perla suma 10 puntos.
- El cofre suma 30 puntos.
- La medusa quita 1 vida.
- El pez globo quita 1 vida.
- La burbuja de oxigeno recupera 1 vida, hasta un maximo de 5.
- Cada segundo aumenta la profundidad en 10 metros.
- Cada 100 metros aumenta el nivel.
- Si las vidas llegan a 0, termina la partida.
- Se puede volver a jugar sin cerrar la aplicacion.

## Requisitos

- JDK 17.
- Maven.
- Joystick o gamepad compatible con JInput.
- IntelliJ IDEA o un entorno compatible con proyectos Maven.

## Como ejecutar

Compilar el proyecto:

```bash
mvn clean compile
```

Ejecutar el juego:

```bash
mvn exec:java
```

Tambien se puede abrir el proyecto en IntelliJ IDEA como proyecto Maven y ejecutar la clase `Main`.

Al iniciar correctamente, la consola muestra:

```text
Juego iniciado correctamente.
```

Si hay un joystick compatible:

```text
Joystick conectado: <nombre>
```

Si no hay joystick conectado:

```text
No se detectó joystick. Se usará teclado.
```

## Estructura MVC

- `modelo`: contiene entidades, reglas, colisiones, puntaje, tiempo, profundidad, nivel y ranking.
- `vista`: contiene las pantallas Swing y el dibujo del juego.
- `controlador`: conecta teclado, joystick, botones, temporizador y flujo de pantallas.
- `util`: contiene carga de recursos y reproduccion de sonidos.

## Recursos

Las imagenes deben ubicarse en:

```text
resources/images
```

Los sonidos deben ubicarse en:

```text
resources/sounds
```

Si falta una imagen, el juego dibuja una figura simple de respaldo. Si falta un sonido, la reproduccion se omite sin cerrar el juego.

## Archivos de recursos esperados

Imagenes:

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

Sonidos:

- `inicio.wav`
- `juego.wav`
- `tesoro.wav`
- `colision.wav`
- `oxigeno.wav`
- `gameover.wav`
