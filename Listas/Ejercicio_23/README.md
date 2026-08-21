
## Ejercicio 23 — Directorio de Sucursales

### Escenario

La empresa AED, dedicada a la producción de software y distribución de
sus productos a nivel internacional, desea mantener un registro de las
ciudades en las cuales tiene oficinas de ventas. Para ello, se desea
cargar una estructura de datos con el contenido de un archivo
`sucursales.txt`, que contendrá, por cada línea, el nombre de la ciudad en
que se encuentra la sucursal.

### Funcionalidades requeridas

En todo momento, el programa debe permitir:

* Agregar una sucursal.
* Buscar una sucursal.
* Quitar una sucursal.
* Listar todas las sucursales.
* Indicar la cantidad de sucursales.
* Indicar si el directorio de sucursales está o no vacío.

### Implementación

> Basados en el TDA **Lista Simplemente Enlazada**, de acuerdo a las
> interfaces publicadas (con genéricos), se deben implementar los métodos
> necesarios para realizar las funcionalidades indicadas.

### Prueba del programa

Dado un archivo de entrada, el programa debe:

1. Leer el archivo `sucursales.txt`.
2. Mostrar en consola las ciudades cargadas.
3. Mostrar el total de elementos (ciudades) contenidas en la estructura.

### Tareas

**1.** Descargar el archivo `suc1.txt`, guardarlo como `sucursales.txt` y
ejecutar el programa. La salida es:

| a) | b) | c) | d) |
|----|----|----|----|
| 104 | 105 | 106 | 107 |
>Respuesta: D

**2.** Eliminar la ciudad `Chicago`, listar nuevamente el conjunto de
sucursales. Dada la ciudad `Hong Kong`, la que le sigue en la lista es:

| a) | b) | c) | d) |
|----|----|----|----|
| Buenos Aires | Tokio | Shenzhen | Cleveland |
>Respuesta: C

**3.** Levantar el archivo `suc2.txt` y eliminar las ciudades `Shenzen` y
`Tokio`. El resultado es:

* a) Quedan 5 ciudades
* b) Queda 1 ciudad y da error de ejecución
* c) Queda vacía y da error de ejecución
* d) Ninguna de las anteriores

>Respuesta: D. 

**4.** Levantar el archivo `suc3.txt` e invocar el método `Imprimir(";")`.
El resultado esperado es alguna de las siguientes secuencias:

* a) `Caracas;Tulsa;Mobile;Vancouver;Montreal;`
* b) `Montreal;Caracas;Tulsa;Mobile;Vancouver`
* c) `Montreal;Tulsa;Caracas;Mobile;Vancouver;`
* d) `Montreal;Caracas;Tulsa;Mobile;Vancouver;`

>Respuesta: B
