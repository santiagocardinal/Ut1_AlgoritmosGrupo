import com.example.ListaEnlazada;
import java.util.Stack; 

public class AnalizadorSintactico 
{

    public static boolean controlCorchetes(ListaEnlazada<Character> listaDeEntrada) 
    {
        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < listaDeEntrada.tamaño(); i++) 
        {
            char caracter = listaDeEntrada.obtener(i);

            if (caracter == '{') 
            {
                pila.push(caracter);
            } 
            else if (caracter == '}') 
            {
                if (pila.isEmpty()) 
                {
                    return false;
                } 
                else 
                {
                    pila.pop();
                }
            }
        }
        return pila.isEmpty();
    }
}

