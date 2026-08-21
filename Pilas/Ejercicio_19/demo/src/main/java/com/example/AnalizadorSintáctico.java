import com.example.ListaEnlazada;
import com.example.Pila;

public class AnalizadorSintáctico
{

    public static boolean controlCorchetes(ListaEnlazada<Character> listaDeEntrada)
    {
        Pila<Character> pila = new Pila<Character>();
        for(int i = 0; i < listaDeEntrada.tamaño(); i++)
        {
            char caracter = listaDeEntrada.obtener(i);
            if(caracter == '{')
            {
                pila.mete(caracter);
            }
            else if (caracter == '}')
            {
                if(pila.esVacio())
                {
                    return false;
                }
                else
                {
                    pila.saca(caracter);
                }
            }
        }
        return pila.esVacio();
    }  
}