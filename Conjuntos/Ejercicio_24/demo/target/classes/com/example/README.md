### EJERCICIO 24
## Parte 1 

## Precondiciones:
    - Se necesitan dos listas que no sean nulas
    - Ninguna de las dos listas contiene objetos repetidos
    - Ambas listas (según la letra) se tienen que encontrar ordenadas
    - Ambas listas pueden estar vacías


## Poscondiciones:
    - Devuelve una nueva lista sin modificar a las otras dos con las que se realizan las operaciones
    - La lista a devolver mantiene el criterio de orden de las otras dos listas
    - la lista a devolver no contiene elementos repetidos


## Lenguaje natural
    Para realizar tanto la unión como la intersección de dos conjuntos se necesitan por lo menos dos listas. Si suponemos que tenemos dos listas que es lo mínimo para representar los conjuntos debería comprobar que ambas listas no estan vacias (principalmente para el caso de intersección), una vez que se tengan las dos listas verificadas se tiene que hacer dos operaciones, una de ellas es la intersección, en donde se tiene que evaluar y comparar cada valor de cada una de las listas para ver si algunos de esos valores es igual, en caso de que sean iguales los voy agregando a una lista, la cuál después devuelvo con estos valores en común. Mientras que en el caso de la unión lo que se tiene que hacer es ir por cada una de esas listas, valor por valor, e ir agregandolas a otra lista, para que queden todos esos valores unidos en solo un lugar.

## pseudocódigo unión
```
función union(lista1, lista2) : lista3
    i = 0
    j = 0
    Mientras (i < lista1.tamaño) Y (j < lista2.tamaño) HACER
        si (lista1.obtener(i) == lista2.obtener(j)) ENTONCES
            lista3.agregar(lista1.obtener(i))
            i = i + 1
            j = j + 1
        SiNo SI (lista1.obtener(i) < lista2.obtener(j)) ENTONCES
            lista3.agregar(lista1.obtener(i))   
            i = i + 1
        SiNo
            lista3.agregar(lista2.obtener(j))  
            j = j + 1
        fin si
    fin mientras
    
    Mientras (i < lista1.tamaño()) HACER
        lista3.agregar(lista1.obtener(i))
        i = i + 1
    fin mientras

    Mientras (j < lista2.tamaño()) HACER
        lista3.agregar(lista2.obtener(j))
        j = j + 1
    fin mientras
    retornar lista3
fin función
```

## pseudocódigo intersección
```
función intersección(lista1, lista2) : lista3
    i = 0
    j = 0
    Mientras (i < lista1.tamaño) Y (j < lista2.tamaño) HACER
        si (lista1.obtener(i) == lista2.obtener(j)) ENTONCES
            lista3.agregar(lista1.obtener(i))
            i = i + 1
            j = j + 1
        SiNo Si (lista1.obtener(i) < lista2.obtener(j)) ENTONCES
            i = i + 1
        SiNo 
            j = j + 1
        fin si
    fin mientras
    retornar lista3
fin función
```


