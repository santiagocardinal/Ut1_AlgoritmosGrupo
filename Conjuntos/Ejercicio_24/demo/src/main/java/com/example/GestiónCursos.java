package ucu.edu.aed.tda;

/**
 * Parte 3 del Ejercicio 24.
 *
 * <p>Demuestra el uso del TDA Conjunto (implementado sobre TDALista)
 * para representar la matrícula de dos cursos y calcular la unión
 * y la intersección de alumnos entre ambos.</p>
 */
public class GestionCursos
{

    public static void main(String[] args) 
    {

        // 1. Dos instancias del TDA Conjunto, genérico en TAlumno
        Conjuntos<TAlumno> aed1 = new Conjuntos<>(); // Algoritmos y Estructuras de Datos I
        Conjuntos<TAlumno> pf = new Conjuntos<>();   // Programación Funcional

        // 2. Varias instancias de TAlumno (cédula de 4 dígitos, Nombre, Apellido)
        TAlumno a1 = new TAlumno("1001", "Ana", "Perez");
        TAlumno a2 = new TAlumno("1002", "Bruno", "Gomez");
        TAlumno a3 = new TAlumno("1003", "Carla", "Diaz");
        TAlumno a4 = new TAlumno("1004", "Diego", "Suarez");
        TAlumno a5 = new TAlumno("1005", "Elena", "Rios");
        TAlumno a6 = new TAlumno("1006", "Franco", "Lopez");

        // 3. Matriculación: a3 y a4 quedan matriculados en AMBOS cursos a propósito
        matricular(aed1, a1);
        matricular(aed1, a2);
        matricular(aed1, a3);
        matricular(aed1, a4);

        matricular(pf, a3);
        matricular(pf, a4);
        matricular(pf, a5);
        matricular(pf, a6);

        System.out.println("AED1 tiene " + aed1.tamaño() + " alumnos");
        System.out.println("PF tiene " + pf.tamaño() + " alumnos");
        System.out.println("¿a3 está en ambos cursos? " + (aed1.contiene(a3) && pf.contiene(a3)));
        System.out.println("¿a4 está en ambos cursos? " + (aed1.contiene(a4) && pf.contiene(a4)));

        // 4. Unión: alumnos matriculados en cualquiera de los dos cursos
        TDAConjunto<TAlumno> matriculadosEnCualquiera = aed1.union(pf);

        System.out.println("\n--- Alumnos matriculados en AED1 o en PF (union) ---");
        imprimir(matriculadosEnCualquiera);

        // 5. Intersección: alumnos matriculados simultáneamente en ambos cursos
        TDAConjunto<TAlumno> matriculadosEnAmbos = aed1.interseccion(pf);

        System.out.println("\n--- Alumnos matriculados simultáneamente en AED1 y PF (interseccion) ---");
        imprimir(matriculadosEnAmbos);
    }

    /**
     * Inserta un alumno en el conjunto manteniendo el orden por cédula
     * (precondición requerida por union/interseccion) y evitando duplicados.
     */
    private static void matricular(Conjuntos<TAlumno> curso, TAlumno alumno) {
        if (curso.contiene(alumno)) {
            return; // ya matriculado, un conjunto no admite duplicados
        }
        int i = 0;
        while (i < curso.tamaño() && curso.obtener(i).compareTo(alumno) < 0) {
            i++;
        }
        curso.agregar(i, alumno);
    }

    private static void imprimir(TDAConjunto<TAlumno> conjunto) {
        for (int i = 0; i < conjunto.tamaño(); i++) {
            System.out.println("  " + conjunto.obtener(i));
        }
    }
}
