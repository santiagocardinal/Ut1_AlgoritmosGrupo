package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.example.TDA.ListaEnlazada;
import com.example.TDA.TDALista;

/**
 * Directorio de sucursales de la empresa AED. Envuelve un TDALista&lt;String&gt;
 * y resuelve las funcionalidades pedidas por el enunciado (Ejercicio 23),
 * incluida la lectura del archivo sucursales.txt.
 */
class sucursales {

    private TDALista<String> ciudades = new ListaEnlazada<>();

    // Lee el archivo (una ciudad por linea) y agrega cada ciudad al
    // directorio. rutaArchivo es una ruta de sistema de archivos, relativa
    // a la carpeta desde donde se ejecuta el programa (ej: "src/main/resources/sucursales.txt")
    public void cargarDesdeArchivo(String rutaArchivo) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(rutaArchivo), StandardCharsets.UTF_8));
        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (!linea.isEmpty()) {
                agregar(linea);
            }
        }
        br.close();
    }

    public void agregar(String ciudad) {
        ciudades.agregar(ciudad);
    }

    public boolean buscar(String ciudad) {
        return ciudades.contiene(ciudad);
    }

    public boolean quitar(String ciudad) {
        return ciudades.remover(ciudad);
    }

    // Concatena todas las ciudades separadas por "separador"
    public String imprimir(String separador) {
        String resultado = "";
        for (int i = 0; i < ciudades.tamano(); i++) {
            resultado += ciudades.obtener(i) + separador;
        }
        return resultado;
    }

    public int cantidad() {
        return ciudades.tamano();
    }

    public boolean esVacio() {
        return ciudades.esVacio();
    }
}