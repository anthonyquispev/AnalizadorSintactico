package configuracion;

import colecciones.ArregloComponente;

public class Sistema {
    public static ArregloComponente componentes = new ArregloComponente();
    
    //Número de filas de la tabla
    public static final int N_FILAS = 58;
    
    //Número de columnas de la tabla
    public static final int N_COLUMNAS = 53;

    //Número máximo de elementos de la pila
    public static final int TOTAL_PILA = 400;
    
    //Primer No terminal considerado en la gramática
    public static final String PRIMER_NOTERMINAL = "S";
    
}
