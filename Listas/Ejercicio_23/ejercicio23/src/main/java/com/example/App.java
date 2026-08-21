package com.example;

import java.io.IOException;

/**
 * Misma lógica que tu App.java, con dos correcciones de referencia:
 * 1. El bloque "agregar Miami y quitar Chicago" ahora hace de verdad esas
 *    dos llamadas antes de imprimir (antes solo imprimía dos veces sin
 *    tocar nada).
 * 2. El bloque "\n=== suc1.txt \n===" opera sobre sucursales2 (cargada desde
 *    suc1.txt) en vez de sucursales1, y el bloque "\n=== suc3.txt \n==="
 *    imprime sucursales4 (cargada desde suc3.txt) en vez de sucursales3.
 */
public class App {

    public static void main(String[] args) throws IOException {

        sucursales sucursales1 = new sucursales();
        sucursales1.cargarDesdeArchivo("Listas/Ejercicio_23/ejercicio23/src/main/java/com/example/Lugares/sucursales.txt");

        System.out.println("\n=== sucursales.txt ===\n");

        System.out.println("sucursales cargadas:");
        System.out.println(sucursales1.imprimir(";"));
        System.out.println("Total de sucursales: " + sucursales1.cantidad());

        System.out.println("¿Esta vacio el directorio? " + sucursales1.esVacio());

        sucursales1.agregar("Miami");
        sucursales1.quitar("Chicago");
        System.out.println("sucursales luego de agregar Miami y quitar Chicago:");
        System.out.println(sucursales1.imprimir(";"));
        System.out.println("Total de sucursales: " + sucursales1.cantidad());

        System.out.println("\n=== suc1.txt ===\n");

        sucursales sucursales2 = new sucursales();
        sucursales2.cargarDesdeArchivo("Listas/Ejercicio_23/ejercicio23/src/main/java/com/example/Lugares/suc1.txt");
        System.out.println(sucursales2.imprimir(";"));

        System.out.println("Total de sucursales: " + sucursales2.cantidad());
        boolean quitadaChicago = sucursales2.quitar("Chicago");
        System.out.println("¿Se quito Chicago? " + quitadaChicago);
        boolean quitadaHongKong = sucursales2.quitar("Hong Kong");
        System.out.println("¿Se quito Hong Kong? " + quitadaHongKong);
        System.out.println(sucursales2.imprimir(";"));

        System.out.println("\n=== suc2.txt ===\n");

        sucursales sucursales3 = new sucursales();
        sucursales3.cargarDesdeArchivo("Listas/Ejercicio_23/ejercicio23/src/main/java/com/example/Lugares/suc2.txt");

        System.out.println("¿Esta vacio el directorio? " + sucursales3.esVacio());
        System.out.println("¿Existe la sucursal Shenzen? " + sucursales3.buscar("Shenzen"));
        System.out.println("¿Existe la sucursal Tokio? " + sucursales3.buscar("Tokio"));

        boolean quitadaShenzen = sucursales3.quitar("Shenzen");
        boolean quitadaTokio = sucursales3.quitar("Tokio");
        System.out.println("¿Se quito Shenzen? " + quitadaShenzen);
        System.out.println("¿Se quito Tokio? " + quitadaTokio);

        System.out.println("sucursales luego de quitar Shenzen y Tokio:");
        System.out.println(sucursales3.imprimir(";"));
        System.out.println("Total de sucursales: " + sucursales3.cantidad());

        System.out.println("\n=== suc3.txt ===\n");

        sucursales sucursales4 = new sucursales();
        sucursales4.cargarDesdeArchivo("Listas/Ejercicio_23/ejercicio23/src/main/java/com/example/Lugares/suc3.txt");
        System.out.println(sucursales4.imprimir(";"));
        System.out.println("Total de sucursales: " + sucursales4.cantidad());
    }
}