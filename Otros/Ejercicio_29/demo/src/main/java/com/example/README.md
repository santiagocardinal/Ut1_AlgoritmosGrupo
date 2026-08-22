## Precondiciones:
    - La lista jugadores no es nula.

## Poscondiciones:
    - Devuelve una lista con los convocados
    - Devuelve una lista con los suplentes
    - En caso de que no se alcancen los 20 convocados se devuelve el valor de personas faltantes (el deficit)

## Lenguaje Natural
    Para armar la convocatoria se recorre la lista de jugadores ingresados. Lo primero que se hace es revisar su estado, en caso de que el jugador está habilitado, se lo coloca en la lista de habilitados, en caso contrario, se lo coloca en la lista de no habilitados.
    Luego, se ordenan los jugadores habilitados según los criterios de prioridad (división, partidas jugadas y orden de registro). Una vez ordenados, se van agregando los primeros a la plantilla de titulares convocados. Si hay más de 20 habilitados, los 20 primeros forman los titulares y todos los demás pasan a integrar la lista de suplentes, completando el cuadro.
    Sin embargo, si hay menos de 20 habilitados en total, los que estén van a los titulares y, para completar los cupos faltantes, se usa la lista de no habilitados, que cuenta con los lesionados y los suspendidos. Esta lista se ordena con el mismo criterio de prioridad (división, partidas jugadas y orden de registro) y se van sumando jugadores a los convocados hasta llegar a 20 o hasta que no queden más disponibles. Si al final de todo no se alcanzaron los 20 convocados, se calcula y se reporta el déficit faltante.

## Estructuras de datos utilizadas

- **TDALista (ListaEnlazada)** para representar la entrada, los grupos intermedios
  (habilitados/no habilitados) y las salidas (convocados/suplentes). Se eligió por
  sobre un array porque la cantidad de jugadores no se conoce de antemano y no
  requerimos acceso aleatorio por índice, ya que solo se recorre secuencialmente y se
  agrega al final, operaciones para las que una lista enlazada es adecuada.

- **ordenar(Comparator)**, ya provisto por TDALista, para aplicar el criterio de
  prioridad de Jugador (Comparable) sin necesitar una estructura adicional
  (como una cola de prioridad) para este primer enfoque.

## pseudocódigo

```
función armarConvocatoria(jugadores) : ResultadoConvocatoria
    convocados <- crear ListaEnlazada
    suplentes <- crear ListaEnlazada
    habilitados <- crear ListaEnlazada
    noHabilitados <- crear ListaEnlazada
    
    n <- jugadores.tamaño()

    para i <- 0 hasta n - 1 hacer
        jugadorOriginal <- jugadores.obtener(i)
        
        jugadorActual <- crear Jugador(jugadorOriginal.nombre, jugadorOriginal.division, jugadorOriginal.partidasJugadas, jugadorOriginal.estado, i)

        si jugadorActual.estado = HABILITADO entonces
            habilitados.agregar(jugadorActual)
        sino
            noHabilitados.agregar(jugadorActual)
        fin si
    fin para

    habilitadosOrdenados <- habilitados.ordenar(criterioPrioridadJugador)
    cantHabilitados <- habilitadosOrdenados.tamaño()

    para i <- 0 hasta cantHabilitados - 1 hacer
        jugadorActual <- habilitadosOrdenados.obtener(i)
        
        si convocados.tamaño() < 20 entonces
            convocados.agregar(jugadorActual)
        sino
            suplentes.agregar(jugadorActual)
        fin si
    fin para

    si convocados.tamaño() < 20 y no noHabilitados.esVacio() entonces
        noHabilitadosOrdenados <- noHabilitados.ordenar(criterioPrioridadJugador)
        cantNoHabilitados <- noHabilitadosOrdenados.tamaño()
        idx <- 0

        mientras convocados.tamaño() < 20 y idx < cantNoHabilitados hacer
            convocados.agregar(noHabilitadosOrdenados.obtener(idx))
            idx <- idx + 1
        fin mientras
    fin si

    deficit <- 0
    si convocados.tamaño() < 20 entonces
        deficit <- 20 - convocados.tamaño()
    fin si

    resultado <- crear ResultadoConvocatoria(convocados, suplentes, deficit)
    retornar resultado
fin función
```

## Análisis de orden de tiempo de ejecución de armarConvocatoria

Sea n la cantidad total de jugadores en la lista de entrada.

### Bloque 1 — Separar habilitados / no habilitados
    para i <- 0 hasta n - 1 hacer
        jugadorOriginal <- jugadores.obtener(i)
        ...
    fin para

Cada llamada a obtener(i) recorre la ListaEnlazada desde el primer nodo,
por lo que cuesta O(i) en el peor caso. Sumando el costo de las n llamadas:

    obtener(0) + obtener(1) + ... + obtener(n-1) = n(n-1)/2 = O(n²)

**Costo: O(n²)**

### Bloque 2 — Ordenar habilitados
    habilitadosOrdenados <- habilitados.ordenar(criterioPrioridadJugador)

ordenar() está implementado con selection sort (bucle anidado: por cada
elemento se recorre el resto buscando el mínimo). Con m ≤ n elementos:

**Costo: O(m²) ⊆ O(n²)** en el peor caso (m = n, todos habilitados)

### Bloque 3 — Repartir entre convocados y suplentes
    para i <- 0 hasta cantHabilitados - 1 hacer
        jugadorActual <- habilitadosOrdenados.obtener(i)
        ...
    fin para

Mismo patrón que el Bloque 1: un for que llama a obtener(i) en cada
vuelta sobre una ListaEnlazada.

**Costo: O(n²)**

### Bloque 4 — Completar con no habilitados
    noHabilitadosOrdenados <- noHabilitados.ordenar(criterioPrioridadJugador)  -> O(n²)
    mientras convocados.tamaño() < 20 y idx < cantNoHabilitados hacer          -> O(1)
        convocados.agregar(noHabilitadosOrdenados.obtener(idx))
        idx <- idx + 1
    fin mientras

- El ordenar() vuelve a costar O(n²) (selection sort).
- El mientras está acotado por la condición convocados.tamaño() < 20:
  da como máximo 20 vueltas, sin importar cuán grande sea n → O(1).
- Al estar en secuencia (uno después del otro, no anidados), el costo
  total del bloque es la suma: O(n²) + O(1) = O(n²) (el término O(1)
  queda absorbido por el O(n²) dominante).

**Costo: O(n²)**

### Costo total

Los cuatro bloques se ejecutan en secuencia (uno después del otro, no
anidados entre sí), por lo que el costo total es la suma de los cuatro:

    O(n²) + O(n²) + O(n²) + O(n²) = O(n²)

**armarConvocatoria(jugadores) es O(n²)**, dominado principalmente por
las llamadas a obtener(index) sobre una lista enlazada dentro de bucles
(Bloques 1 y 3) y por el algoritmo de ordenamiento por selección
utilizado en ordenar() (Bloques 2 y 4).

## solución alternativa:
    En esta solución alternativa decidimos utilizar cola con prioridad, eligiendo este los mejores de los 20 jugadores.
    Un posible problema que puede tener a futuro este codigo es que lo hicimos especifico para 20 jugadores, lo cual no se ajusta todos los casos que peudan existir. Pero nos queríamos apegar a lo que decía en la letra.

```
colaTop20 <- crear ColaPrioridad acotada a 20 (según Jugador.compareTo)

para cada jugadorActual en habilitados hacer          
    si colaTop20.tamaño() < 20 entonces
        colaTop20.agregar(jugadorActual)               
    sino
        peor <- colaTop20.obtenerPeor()                 
        si jugadorActual es mejor que peor entonces
            colaTop20.sacarPeor()
            colaTop20.agregar(jugadorActual)
            suplentes.agregar(peor)
        sino
            suplentes.agregar(jugadorActual)
        fin si
    fin si
fin para

convocados <- colaTop20.vaciarComoLista()               
```


## PARTE 7:
    Alcanza con modificar un único método, el cual es Jugador.compareTo(), invirtiendo el orden de los dos primeros criterios (primero partidas jugadas, después división. El desempate por orden de registro queda igual). No hay que tocar GestorConvocatoria, ListaEnlazada.ordenar ni el pseudocódigo, porque todos delegan el criterio de prioridad en compareTo en vez de volver a implementarlo. El orden de tiempo de ejecución no cambia (sigue siendo O(n²)). Lo únio que sí hay que hacer es actualizar los tests, ya que estos verifican el orden anterior.

