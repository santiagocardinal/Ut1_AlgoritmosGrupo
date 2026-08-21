## EJERCICIO 18 - README
### Parte 1 - Funcionalidad a implementar

<!--quiero que retorne un valos booleano para saber si es correcto la expreión o no (true si es correcto y false si no es correcto)-->
```
función controlCorchetes(listaDeEntrada) : booleano
    pila <- nueva Pila vacía
    para cada caracter en listaDeEntrada hacer
        si caracter = '{' entonces
            pila.apilar(caracter)
        sino si caracter = '}' entonces
            si pila.esVacia() entonces
                retornar falso
            sino
                pila.desapilar()
             fin si
        fin si
    fin para
    retornar pila.esVacia()
fin función
```

### Parte 2 - Análisis de complejidad
**Complejidad temporal:** el algoritmo recorre la lista de entrada una sola vez, carácter por carácter. Por cada carácter se hace como mucho una operación de pila (apilar, o estaVacia + desapilar), y estas operaciones tardan siempre lo mismo sin importar cuántos elementos tenga la pila en ese momento. Entonces, si la entrada tiene n caracteres, el algoritmo hace aproximadamente n operaciones en total, por lo tanto, el tiempo crece de forma proporcional al tamaño de la entrada. Por ejemplo, si n pasa de 100 a 200, el trabajo también pasa de 100 a 200 operaciones.

**Complejidad según el espacio:** en el peor caso, cuando la entrada tiene muchas aperturas seguidas sin ningún cierre (por ejemplo, una secuencia con n corchetes de apertura), la pila va acumulando un elemento por cada apertura, sin desapilar nada. Entonces la pila puede llegar a tener como máximo n elementos, la misma cantidad que el tamaño de la entrada.

