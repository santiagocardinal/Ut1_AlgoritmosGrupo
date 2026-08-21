#  Biblioteca Central UCU — Catálogo y Existencias

> Proyecto de Estructuras Lineales — Ejercicios 17 y 18
> Gestión de catálogo, existencias y préstamos usando un TDA Lista propio.

---

##  Escenario

La Biblioteca Central UCU necesita gestionar su **catálogo** y sus
**existencias** (ejemplares disponibles para préstamo). Por cada libro se
almacena:

* Título del libro.
* Código de identificación (ISBN o código interno).
* Precio de reposición.
* Cantidad de ejemplares disponibles.

## Funcionalidades requeridas

El sistema debe permitir:

1. Incorporar un nuevo libro al catálogo.
2. Agregar ejemplares a un libro existente.
3. Registrar préstamo o devolución de un libro.
4. Retirar del catálogo libros que ya no circulan.
5. Consultar existencias de un libro por su código.
6. Listar todos los libros, ordenados por título, con su stock.

---

##  Paso 1 — Clase en común

1. Completar los métodos de **inserción** y **búsqueda** en el TDA `Lista`.
2. Diseñar las estructuras de datos del sistema utilizando el TDA `Lista`.
3. Declarar las clases `Biblioteca` y `Libro` según las funcionalidades
   dadas arriba.

> El TDA `Lista` construido acá es la base de todo lo que sigue: tanto
> `Biblioteca` como los dos sub-equipos dependen de que **insertar** y
> **buscar** funcionen antes de repartir el trabajo.

---

##  Paso 2 — Implementación en sub-equipos

### Sub-equipo A — Adquisiciones

Registra compra o donación de ejemplares, alta de un libro nuevo, e indica
el **valor total agregado al stock**.

**Formato de `adquisiciones.txt`:**

| Campo             | Descripción                          |
|-------------------|---------------------------------------|
| `CODIGO_LIBRO`    | ISBN o código interno                 |
| `TITULO_LIBRO`    | Título del libro                      |
| `PRECIO_REPOSICION` | Precio unitario de reposición       |
| `CANTIDAD`        | Ejemplares que se incorporan          |



### Sub-equipo B — Préstamos

Registra préstamos (reducen stock) y devoluciones (aumentan stock), e
indica la **variación total de ejemplares prestados**.

**Formato de `prestamos.txt`:**

| Campo          | Descripción                          |
|----------------|----------------------------------------|
| `CODIGO_LIBRO` | ISBN o código interno                  |
| `TIPO`         | `PRESTAMO` o `DEVOLUCION`              |
| `CANTIDAD`     | Ejemplares afectados                   |



> **Nota:** si la cantidad solicitada en un préstamo excede el stock
> disponible, se presta **hasta donde alcance** (no se rechaza todo el
> pedido).

---

##  Paso 3 — Integración y verificación

Integrar el código de todos los sub-equipos en un único programa que
procese, en orden:

1. `adquisiciones.txt`
2. `prestamos.txt`

>  Los archivos deben estar ubicados bajo `/src/main/resources`.

---

## Ejercicio 18 — Quitar/Eliminar en el TDA Lista

Además de insertar y buscar, el TDA `Lista` necesita poder **retirar** un
elemento. Comportamientos posibles a discutir:

1. **Quitar**: sacar un elemento de la lista (si existe) y dejarlo
   disponible para su posterior uso.
2. **Eliminar**: sacar y destruir un elemento.
3. Otros comportamientos a definir por el equipo.

### Consigna

* Definir qué forma deberían tener (qué parámetros reciben).
* Definir qué valor o resultado deberían devolver.
* Implementar el comportamiento acordado como método del `TDALista` del
  Ejercicio 17.
* Cubrir con JUnit: elemento existente, elemento inexistente, lista vacía.
* Analizar qué pasa con el campo `siguiente` de un nodo retirado, y qué
  precauciones tomar para evitar referencias inválidas.

