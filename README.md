# Mini Scrabble

Proyecto académico desarrollado en **Java** como práctica final de la asignatura **Programación I**. El objetivo del proyecto es implementar una versión simplificada de **Scrabble** en consola, trabajando conceptos básicos de **programación orientada a objetos**, **lectura y escritura de ficheros**, **validación de entrada** y **gestión de estructuras de datos**.

## Descripción

En esta práctica, el jugador recibe **11 fichas aleatorias** por turno y debe formar una palabra válida usando únicamente esas letras. Si la palabra es correcta, se suman los puntos correspondientes según el valor de cada ficha; si no lo es, se penaliza la puntuación.

Además del modo de juego normal, el proyecto incluye un modo contra una **supermáquina**, capaz de buscar en el diccionario la palabra de mayor puntuación posible con las letras disponibles.

## Funcionalidades principales

- **Modo de juego normal**
  - Introducción del nombre del jugador.
  - Selección del número de turnos.
  - Sorteo de 11 fichas por turno.
  - Validación de palabras contra el diccionario.
  - Cálculo de puntuación.

- **Historial de partidas**
  - Guarda el nombre del jugador, el número de turnos, la puntuación y la fecha.
  - Permite consultar partidas anteriores desde el menú principal.

- **Modo vs supermáquina**
  - La máquina analiza el diccionario y elige la palabra con mayor puntuación posible a partir de las letras sorteadas.

- **Visualización de fichas**
  - Muestra todas las letras disponibles, su cantidad y su puntuación.

- **Validación de entradas**
  - Control de errores al introducir opciones, nombre de usuario y número de turnos.

## Tecnologías utilizadas

- **Java 11**
- Programación orientada a objetos
- Lectura y escritura de ficheros
- Proyecto gestionado con **NetBeans / Ant**

## Estructura del proyecto

```bash
Scrabble/
├── src/
│   └── scrabble/
│       ├── Scrabble.java
│       ├── Paraula.java
│       ├── Fitxes.java
│       ├── FI.java
│       ├── FO.java
│       └── LT.java
├── alfabet.txt
├── cat.dic
├── historial.txt
├── build.xml
└── dist/
    └── Scrabble.jar
```

## Clases principales

- **Scrabble**: clase principal y control del flujo del juego.
- **Paraula**: gestión y comparación de palabras.
- **Fitxes**: representación de letras, cantidades y puntuaciones.
- **FI**: lectura de ficheros.
- **FO**: escritura de ficheros.
- **LT**: utilidades para la lectura de datos por teclado.

## Cómo ejecutar el proyecto

### Opción 1: desde NetBeans
Abrir el proyecto en NetBeans y ejecutar la clase principal:

```java
scrabble.Scrabble
```

### Opción 2: desde terminal
Compilar y ejecutar desde la carpeta raíz del proyecto:

```bash
javac -d out src/scrabble/*.java
java -cp out scrabble.Scrabble
```

## Notas importantes

- El proyecto utiliza ficheros auxiliares como `alfabet.txt`, `cat.dic` e `historial.txt`, por lo que conviene ejecutar el programa **desde la raíz del proyecto** para que pueda encontrarlos correctamente.
- En esta versión del repositorio, el diccionario incluido es `cat.dic`.
- Se trata de una práctica académica, por lo que el objetivo principal es demostrar el aprendizaje de conceptos fundamentales de programación.

## Aprendizajes del proyecto

Este trabajo permitió practicar:

- Diseño básico orientado a objetos.
- Gestión de arrays y cadenas.
- Lectura y escritura de archivos.
- Validación de entradas del usuario.
- Desarrollo de lógica de juego en consola.

## Autoría

Proyecto realizado por:

- **Josep Gabriel Fornés Reynés**
- **Antoni Cruz Carrió**

## Estado

Proyecto finalizado como práctica universitaria. Actualmente se conserva como parte del portfolio académico.
