## Precondiciones:
    - La lista jugadores no es nula.

## Poscondiciones:
    - Devuelve una lista con los convocados
    - Devuelve una lista con los suplentes
    - En caso de que no se alcancen los 20 convocados se devuelve el valor de personas faltantes (el deficit)

## Lenguaje Natural
    Para armar la convocatoria se recorre la lista de jugadores ingresados. Lo primero que se hace es revisar su estado, en caso de que el jugador está habilitado, se lo coloca en la lista de habilitados, en caso contrario, se lo coloca en la lista de no habilitados.
    Luego, se ordenan los jugadores habilitados según los criterios de prioridad (división, partidas jugadas y orden de registro). Una vez ordenados, se van agregando los primeros a la plantilla de titulares convocados. Si hay más de 20 habilitados, los 20 primeros forman los titulares y todos los demás pasan a integrar la lista de suplentes, completando el cuadro.
    Sin embargo, si hay menos de 20 habilitados en total, los que estén van a los titulares y, para completar los cupos faltantes, se usas la lista de no habilitados, que cuenta con los lesionados y los suspendidos. Esta lista se ordena por división y se van sumando jugadores a los convocados hasta llegar a 20 o hasta que no queden más disponibles. Si al final de todo no se alcanzaron los 20 convocados, se calcula y se reporta el déficit faltante.

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
        noHabilitadosOrdenados <- noHabilitados.ordenar(criterioPorDivision)
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



