## Parte 2 — Cola circular con vector 

## primer pseudocódigo

```
función poneEnCola(vector, frente, fin, capacidad, unElemento) : entero
    si (fin + 1) mod capacidad = frente entonces
        error "cola llena"
    fin si
    vector[fin] <- unElemento
    fin <- (fin + 1) mod capacidad
    retornar fin
fin función
```

## segundo pseudocódigo

```
función quitaDeCola(vector, frente, fin, capacidad) : elemento
    si frente = fin entonces
        error "cola vacía"
    fin si
    elemento <- vector[frente]
    frente <- (frente + 1) mod capacidad
    retornar elemento
fin función
```

## Parte 3 — Comparación

La cola enlazada usa memoria justa (un nodo por elemento), pero cada nodo tiene overhead extra por la referencia al siguiente. La cola circular reserva el vector completo desde el inicio (tamaño fijo), y pierde un lugar por el hueco vacío que evita confundir cola vacía con llena.

Ambas tienen todas sus operaciones en O(1). La diferencia es el costo constante: el vector accede directo por índice (más rápido), mientras que la lista enlazada crea y destruye nodos en cada operación.

La cola circular conviene cuando se conoce de antemano el tamaño máximo del buffer, como en el caso del enunciado. La cola enlazada conviene cuando el tamaño es impredecible y no se quiere lidiar con "cola llena".
